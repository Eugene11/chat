package net.test.chat.dao;

import java.util.List;
import net.test.chat.domain.User;

/**
 * Интерфейс для работы с пользователем прослойки DAO
 * @author Eugene
 *
 */
public interface UserDAO {
	
	/**
	 * добавить пользователя
	 * @param user
	 */
	public void addUser(User user);
	
	/**
	 * достать всех пользоватлей
	 */
	public List<User> listUser();

	/**
	 * Удалить пользователя
	 * @param id пользователя 
	 */
	public void removeUser(Integer id);
	
	/**
	 * проверить наличие пользователя в базе
	 * @param login логин
	 * @return есть или нет
	 */
	public boolean checkUser(String login);
	
	/**
	 * достать пользователя по логину
	 * @param login логин
	 * @return пользователь
	 */
	public User findUserByLogin(String login);
	
	/**
	 * достать пользователя по nickname
	 */
	public User findUserByNickName(String nickName);
	
}