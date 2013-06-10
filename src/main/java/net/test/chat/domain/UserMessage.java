package net.test.chat.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.taglibs.standard.lang.jpath.example.Person;

/**
 * Класс сообщений пользователей
 * @author Eugene
 *
 */
@Entity
@Table(name = "USERMESSAGE")
public class UserMessage  {
	
	/**
	 * id идентификатор сообщения
	 */
	@Id
	@GeneratedValue
    @Column(name = "ID")
	protected Integer id;
	
	/**
	 * Содержимое сообщения
	 */
    @Column(name = "MESCONT")
    protected String messageContent;
    
    /**
	 * Пользователь
	 */
    @ManyToOne
    protected User user;
    
    /**
     * поле когда было добавленно сообщение
     */
    protected Date addedTime;
    

	public UserMessage(){
		this.addedTime = new Date();
    }
    
    public UserMessage(User user, String content){
    	this.user = user;
    	this.messageContent = content;
    	this.addedTime = new Date();
    }
    
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getMessageContent() {
		return messageContent;
	}
	
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Date getAddedTime() {
		return addedTime;
	}

	public void setAddedTime(Date addedTime) {
		this.addedTime = addedTime;
	}
	
	
}
