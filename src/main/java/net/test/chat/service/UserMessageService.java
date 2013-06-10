package net.test.chat.service;

import java.util.List;
import net.test.chat.domain.User;
import net.test.chat.domain.UserMessage;

/**
 * Интерфейс работы с сообщениями пользователей слоя сервисов
 * @author Eugene
 *
 */
public interface UserMessageService {

	/**
	 * добавить сообщение
	 * @param userMessage
	 */
	public void addUserMessage(UserMessage userMessage);
	
	/**
	 * Получить последние n сообщений
	 * @param quantity - n сообщений  
	 * @return список сообщений
	 */
	public List<UserMessage> getListRecentUserMessages(int quantity);
}
