package net.test.chat.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import net.test.chat.dao.UserDAO;
import net.test.chat.domain.User;

/**
 * Имплементация работы с пользователем прослойки сервисов
 * @author Eugene
 *
 */
@Service
public class UserServiceImpl implements UserService {
 
    @Autowired
    private UserDAO chatDAO;
    
    @Transactional
    @Override
    public void addUser(User user) {
    	chatDAO.addUser(user);
    }
 
    @Transactional
    @Override
    public List<User> listUser() {
    	
        return chatDAO.listUser();
    }
 
    @Transactional
    @Override
    public void removeUser(Integer id) {
    	chatDAO.removeUser(id);
    }
    
    @Transactional
    @Override
    public boolean checkUser(String login) {
    	return chatDAO.checkUser(login);
    }
    
    @Transactional
    @Override
    public User findUserByLogin(String login){
    	return chatDAO.findUserByLogin(login); //null;
    }
    
    @Transactional
    @Override
    public User findUserByNickName(String nickName){
    	return chatDAO.findUserByNickName(nickName);
    }
}
