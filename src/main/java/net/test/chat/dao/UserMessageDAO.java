package net.test.chat.dao;

import java.util.List;

import net.test.chat.domain.UserMessage;

/**
 * Интерфейс работы с сообщениями пользователей слоя DAO
 * @author Eugene
 *
 */
public interface UserMessageDAO {
	
	/**
	 * добавить сообщение
	 * @param userMessage сущность сообщения
	 */
	public void addUserMessage(UserMessage userMessage);
	
	/**
	 * Получить последние n сообщений
	 * @param quantity - n сообщений  
	 * @return список сообщений
	 */
	public List<UserMessage> getListRecentUserMessages(int quantity); 
}