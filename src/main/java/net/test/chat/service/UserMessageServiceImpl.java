package net.test.chat.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import net.test.chat.dao.UserMessageDAO;
import net.test.chat.domain.User;
import net.test.chat.domain.UserMessage;

/**
 * Интерфейс работы с сообщениями пользователей слоя DAO
 * @author Eugene
 *
 */
@Service
public class UserMessageServiceImpl implements UserMessageService {
 
    @Autowired
    private UserMessageDAO chatDAO;
    
    @Transactional
    @Override
	public void addUserMessage(UserMessage userMessage) {
		chatDAO.addUserMessage(userMessage);
	}
    
    @Transactional
    @Override
    public List<UserMessage> getListRecentUserMessages(int quantity){
    	return chatDAO.getListRecentUserMessages(quantity);
    }
}
