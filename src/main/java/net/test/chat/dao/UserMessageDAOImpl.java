package net.test.chat.dao;

import java.util.ArrayList;
import java.util.List;

import net.test.chat.domain.User;
import net.test.chat.domain.UserMessage;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Имплементация работы с сообщениями пользователей слоя DAO
 * @author Eugene
 *
 */
@Repository
public class UserMessageDAOImpl implements UserMessageDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addUserMessage(UserMessage userMessage) {
		sessionFactory.getCurrentSession().saveOrUpdate(userMessage);
	}
	
	@Override
	public List<UserMessage> getListRecentUserMessages(int quantity){
		//public List<UserMessage> listRecentUserMessages(int quantity)
		Query query =  sessionFactory.getCurrentSession().createQuery("select um from UserMessage um order by um.addedTime desc");
		//query.setString("login", login); 
		//ArrayList<User> userList = (ArrayList<User>) query.list();
		if (quantity > 0){
			query.setMaxResults(quantity);
		}
		
		List<UserMessage> listUsers = (List<UserMessage>) query.list(); 
		return listUsers; 
	}
}
