package net.test.chat.dao;

import java.util.ArrayList;
import java.util.List;

import net.test.chat.domain.User;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Имплементация работы с пользователем прослойки DAO
 * @author Eugene
 *
 */
@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addUser(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listUser() {

		return sessionFactory.getCurrentSession().createQuery("from User")
			.list();
	}
	
	@Override
	public boolean checkUser(String login) {
		boolean isExist = false;
		Query query =  sessionFactory.getCurrentSession().createQuery("select u from User u where u.login = :login ");
		query.setString("login", login); 
		//ArrayList<User> userList = (ArrayList<User>) query.list();
		User user = (User) query.uniqueResult();
		
		if(user != null){
			isExist = true;
		}
		return isExist;
	}
	
	@Override
	public void removeUser(Integer id) {
		User user = (User) sessionFactory.getCurrentSession().load(
				User.class, id);
		if (null != user) {
			sessionFactory.getCurrentSession().delete(user);
		}
	}
	
	@Override
	public User findUserByLogin(String login){
		Query query =  sessionFactory.getCurrentSession().createQuery("select u from User u where u.login = :login");
		query.setString("login", login);
		User user = (User) query.uniqueResult();
		return user;
	}
	
	@Override
	public User findUserByNickName(String nickName){
		Query query =  sessionFactory.getCurrentSession().createQuery("select u from User u where u.nickName = :nickName");
		query.setString("nickName", nickName);
		User user = (User) query.uniqueResult();
		return user;
	}
	
	
}
