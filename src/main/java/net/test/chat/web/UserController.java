package net.test.chat.web;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.test.chat.domain.User;
import net.test.chat.domain.UserMessage;
import net.test.chat.service.UserMessageService;
import net.test.chat.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import org.apache.tiles.AttributeContext;
import org.apache.tiles.context.TilesRequestContext;
import org.apache.tiles.preparer.ViewPreparerSupport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.apache.log4j.Logger;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Основной контроллер приложения чата
 * @author Eugene
 *
 */

@Controller
public class UserController {
	
	/**
	 * энум который описывает как много сообщений показывать
	 * @author Eugene
	 *
	 */
	public enum FrequencyEnum{
		FIFTY,  HUNDRED,  TWOHUNDRED, ALL
	}
	
	/**
	 * константы
	 */
	public static final int FIFTY_CONST = 50;
	public static final int HUNDRED_CONST = 100;
	public static final int TWOHUNDRED_CONST = 200;
	public static final int ALL_CONST = -1;
	
	/**
	 * сервис работы с пользователями
	 */
	@Autowired
	private UserService userService;
	
	/**
	 * сервис работы с сообщениями
	 */
	@Autowired
	private UserMessageService userMessageService;
	
	/**
	 * Подключение логгера
	 */
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	/**
	 * Обработка запроса на главную страницу
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model modelRes, HttpSession session, Principal principal) {
		logger.info(":home");
		if (session.getAttribute("exitUserSucReg") != null){
			modelRes.addAttribute("exitUserSucReg", new String("exitUserSucReg") );
			session.removeAttribute("exitUserSucReg");
		}
		
		if (principal != null){
			if(principal.getName() != null){
				modelRes.addAttribute("cont", "true");
				modelRes.addAttribute("profile", "true");
			}
		}
		else{
			modelRes.addAttribute("auth", "true");
			modelRes.addAttribute("reg", "true");
		}
		
		logger.info(modelRes);
		return "home";
	}
	
	/**
	 * Страница регистрации
	 */
	@RequestMapping(value = "/jsp/register", method = RequestMethod.GET)
	public String register(Model modelRes, HttpSession session) {
		logger.info(":register");
		if (session.getAttribute("exitUserErr") != null){
			modelRes.addAttribute("errUserExist", new String("exitUserErr"));
			session.removeAttribute("exitUserErr");
		}
		
		return "register";
	}
	
	/**
	 * добавление пользователя при регистрации
	 * @param user пользователь
	 * @param session сиссия
	 * @return
	 */
	@RequestMapping(value = "/jsp/addUser", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User user,  HttpSession session) {
		logger.info(":addUser");
		PasswordEncoder encoder = new Md5PasswordEncoder();
		String hashedPass = encoder.encodePassword(user.getPassword(), null);
		user.setPassword(hashedPass);
		
		boolean isExist = userService.checkUser(user.getLogin() );
		
		//если пользователь уже есть -> снова на страницу регитсрации
		if(isExist ){
			
			session.setAttribute("exitUserErr", new String() );
			return "redirect:/jsp/register";
		}
		
		else{
			//ModelAndView model = new ModelAndView();
			session.setAttribute("exitUserSucReg", new String() );
			
			userService.addUser(user);
			return "redirect:/";
		}
		
	}
	
	/**
	 * Страница авторизации
	 * 
	 * 	
	 */
	@RequestMapping(value = "/jsp/auth", method = RequestMethod.GET)
	public String auth(Model modelRes, HttpSession session) {
		logger.info(":auth");
		modelRes.addAttribute("reg", "true");
		
		return "auth";
	}
	
	/**
	 * ошибка авторизации
	 */
	@RequestMapping(value="/jsp/authfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
		logger.info(":loginerror");
		model.addAttribute("error", "true");
		return "auth";
 
	}
	
	/**
	 * страница профайла
	 * @param model модель параметры
	 * @param principal текущий пользователь
	 */
	@RequestMapping(value="/jsp/profile", method = RequestMethod.GET)
	public String profile(ModelMap model, Principal principal) {
		logger.info(":profile");
		logger.info(":principal name = " + principal.getName() );
		
		User user = userService.findUserByLogin(principal.getName() );
		
		if (user != null){
			model.addAttribute("user", user);
		} 
		
		return "profile";
 
	}
	
	/**
	 * логаут
	 */
	@RequestMapping(value="/jsp/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		logger.info(":logout");
		return "home";
		
	}
	
	/**
	 * основная страница
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/jsp/cont", method = RequestMethod.GET)
	public String cont(ModelMap model) {
		logger.info(":cont");
		
		model.addAttribute("userLogged", "true");
		
		List<UserMessage> listUserMessages = new ArrayList<UserMessage>();
		
		listUserMessages = (List<UserMessage>) userMessageService.getListRecentUserMessages(UserController.FIFTY_CONST);
		
		List<User> listAllUsers = userService.listUser();
		
		model.addAttribute("listUserMessages", listUserMessages);
		model.addAttribute("profile", "true");
		model.addAttribute("listAllUsers", listAllUsers);
		
		return "cont";
	}
	
	/**
	 * редактирование пользователя
	 * @param user пользователь которого редактируют
	 * @param principal текущий зарегистрированый пользователь
	 * @param modelRes параметры
	 */
	@RequestMapping(value = "/jsp/editUser", method = RequestMethod.POST)
	public String editUser(@ModelAttribute("user") User user, Principal principal, Model modelRes) {
		logger.info(":editUser");
		//получить сущлность пользьзователя из базы для текущего залогиненого 
		User userCur = userService.findUserByLogin(principal.getName() );
		
		//если нашли
		if (userCur != null){
			//надо проверить что текущий которого редактируют и тот что залогиненый один и тотже пользователь
			if (!userCur.getNickName().equalsIgnoreCase(user.getNickName() ) ){
				if( user.getNickName() != null ){ 
					//никнейм у того которого редактируем не пустой
					if ( !user.getNickName().isEmpty() ){
						User newUser = userService.findUserByNickName( user.getNickName() );
						//если уже такой пользователь имеется создаем ошибку
						if(newUser != null){
							modelRes.addAttribute("error", "existThisNickName");
						}
					}
				}
			}
		}
		//не введен пароль
		if (user.getPassword() == null){
			modelRes.addAttribute("error", "noPassword");
		}
		else{
			if (user.getPassword().isEmpty() ){
				 modelRes.addAttribute("error", "noPassword");
			}	
		}
		
		//если все нормально помечаем как редактирование удалось
		String errVal = (String) modelRes.asMap().get("error");
		if(errVal == null){
			modelRes.addAttribute("successEdited", "true");
			userCur.setNickName(user.getNickName() );
			//если пароль изменился - ставим новый
			if ( !userCur.getPassword().equalsIgnoreCase(user.getPassword())  ){
				PasswordEncoder encoder = new Md5PasswordEncoder();
				String hashedPass = encoder.encodePassword(user.getPassword(), null);
				
				userCur.setPassword(hashedPass);
			}
			
			userService.addUser(userCur);
		}
		return "profile";
	}
	
	
	
	/**
	 * если залогинелся
	 * @return
	 */
	@RequestMapping(value="/jsp/loggedon", method = RequestMethod.GET)
	public String loggedon(Model modelRes) {
		logger.info(":logeed");
		modelRes.addAttribute("cont", "true");
		modelRes.addAttribute("profile", "true");
		return "loggedon";
	}
	

	/**
	 * добавить сообщение, возвращает вью для аякс рендеринга
	 * @param request запрос
	 * @param principal текущий пользователь
	 * @return
	 */
	@RequestMapping(value = "/jsp/addmsg.htm", method = RequestMethod.POST) 
	public ModelAndView addMessage(HttpServletRequest request, Principal principal) {
		logger.info(":addMessage");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("valVal", request.getParameter("messageCont") );
		mav.setViewName("msg");
		
		logger.info(request.getParameter("messageCont") );
		
		//найти пользователя и добавить ему сообщение
		User user = userService.findUserByLogin(principal.getName() );
		UserMessage um = null;
		if(user != null){
			um = new UserMessage(user, request.getParameter("messageCont") );
			userMessageService.addUserMessage(um);
		}
		
		//date1.getSeconds();
		List<UserMessage> listUserMessages = new ArrayList<UserMessage>();
		listUserMessages.add(um);
		
		mav.addObject("listUserMessages", listUserMessages);
		return mav;
		
	}
	
	/**
	 * получить сообщения
	 * @param request запрос
	 * @param principal текущий пользователь
	 * @return
	 */
	@RequestMapping(value = "/jsp/getmsgs.htm", method = RequestMethod.GET) 
	public ModelAndView getMessages(HttpServletRequest request, Principal principal) {
		logger.info(":getMessages");
		ModelAndView mav = new ModelAndView();
		
		
		mav.addObject("valVal", request.getParameter("messageCont") );
		mav.setViewName("msg");
		
		String quantShowStr = request.getParameter("quantityShow");
		
		Integer quantShowInt = 0;
		try{
			quantShowInt = Integer.parseInt( quantShowStr );
		}
		catch(NumberFormatException ex){
			logger.info(ex.getMessage() );
		}
		
		FrequencyEnum freqVal = null;
		
		if ( quantShowStr != null){
			if (quantShowInt == FrequencyEnum.values()[0].ordinal() ){
				freqVal = FrequencyEnum.values()[0];
			}
			if (quantShowInt == FrequencyEnum.values()[1].ordinal() ){
				freqVal = FrequencyEnum.values()[1];
			}
			if (quantShowInt == FrequencyEnum.values()[2].ordinal() ){
				freqVal = FrequencyEnum.values()[2];
			}
			if (quantShowInt == FrequencyEnum.values()[3].ordinal() ){
				freqVal = FrequencyEnum.values()[3];
			}
		}
		logger.info("freqVal=" + freqVal);
		
		List<UserMessage> listUserMessages = new ArrayList<UserMessage>();
		
		
		//выбираем необходимое количество сообщений
		if (freqVal == FrequencyEnum.FIFTY){
			listUserMessages = (List<UserMessage>) userMessageService.getListRecentUserMessages( UserController.FIFTY_CONST  );
		}
		if (freqVal == FrequencyEnum.HUNDRED){
			listUserMessages = (List<UserMessage>) userMessageService.getListRecentUserMessages(UserController.HUNDRED_CONST );
		}
		if (freqVal == FrequencyEnum.TWOHUNDRED){
			listUserMessages = (List<UserMessage>) userMessageService.getListRecentUserMessages(UserController.TWOHUNDRED_CONST );
		}
		if (freqVal == FrequencyEnum.ALL){
			listUserMessages = (List<UserMessage>) userMessageService.getListRecentUserMessages(UserController.ALL_CONST );
		}
		
		
		mav.addObject("listUserMessages", listUserMessages);
		return mav;
	}
	
}

