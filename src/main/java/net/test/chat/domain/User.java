package net.test.chat.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

/**
 * Класс описывает пользователя
 * @author Eugene
 *
 */
@Entity
@Table(name = "USERS")
public class User {

	/**
	 * id пользователя
	 */
	@Id
    @Column(name = "ID")
    @GeneratedValue
    protected Integer id;
	
	/**
	 * имя
	 */
    @Column(name = "FIRSTNAME")
    protected String firstName;
    
    /**
	 * отчество
	 */
    @Column(name = "MIDDLENAME")
    protected String middleName;
    
    /**
	 * фамилия
	 */
    @Column(name = "LASTNAME")
    protected String lastName;
    
    /**
	 * email почта
	 */
    @Column(name = "EMAIL")
    protected String email;
    
    /**
	 * nickname ник
	 */
    @Index(name = "nickNameIndex")
    @Column(name = "NICKNAME")
    protected String nickName;
    
    /**
	 * login логин
	 */
    @Index(name = "loginIndex")
    @Column(name = "LOGIN")
    protected String login;
    
    /**
	 * пароль
	 */
    @Column(name = "PASSWORD")
    protected String password;
    
    // Getters and setters
    
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
