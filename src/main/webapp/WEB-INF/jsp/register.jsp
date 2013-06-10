<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

 <style>
 body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

      .form-register {
        max-width: 500px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-register .form-register-heading,
      .form-register .checkbox {
        margin-bottom: 10px;
      }
      .form-register .inputField {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
        width: 250px;
		float: right;
      }
      .form-register .labelField {
      	  width: 250px;
		  float: left;
		  padding-top: 5px;
      }
 </style> 
 <div class="container">

      <form:form method="post" action="addUser" commandName="user" class="form-register">
        <form:errors />
        <h2 class="form-register-heading">Пожалуйста зарегистрируйтесь</h2>
        
        <form:errors path="errUserExist" cssClass="error"/>
	        
        <div>
	        <label class="labelField">
	          Имя
	        </label>
	        <input type="text" id="firstName" class="input-block-level inputField" name="firstName" placeholder="Имя">
			
	        <div style="clear:both">
	        </div>
        </div>
        
        
        <div>
	        <label class="labelField">
	          Отчество
	        </label>
	        <input type="text" id="middleName" class="input-block-level inputField" name="middleName" placeholder="Отчество">
	        <div style="clear:both">
	        </div>
        </div>
        
        <div>
	        <label class="labelField">
	          Фамилия
	        </label>
	        <input type="text" id="lastName" class="input-block-level inputField" name="lastName" placeholder="Фамилия">
	        <div style="clear:both">
	        </div>
        </div>
        
        <div>
	        <label class="labelField">
	          Email
	        </label>
	        <input type="text" id="email" class="input-block-level inputField" name="email" placeholder="email">
	        <div style="clear:both">
	        </div>
        </div>
        
        <div>
	        <label class="labelField">
	          Никнейм
	        </label>
	        <form:errors path="password" cssClass="label label-important" />
	        <input type="text" id="nickName"  class="input-block-level inputField" name="nickName" placeholder="Никнейм">
	        <div style="clear:both">
	        </div>
        </div>
        
        <div>
	        <label class="labelField">
	          Логин
	        </label>
	        <span style="color:red;">
	        ${not empty errUserExist ? 'Пользователь уже зарегистрирован' : ''}
	        </span>
	        <input type="text" id="login" class="input-block-level inputField" name="login" placeholder="Логин">
	        <div style="clear:both">
	        </div>
        </div>
        
        <div>
	        <label class="labelField">
	          Пароль
	        </label>
	        <input type="password" id="password" class="input-block-level inputField" name="password" placeholder="password">
	        <div style="clear:both">
	        </div>
        </div>
        
        <button class="btn btn-large btn-primary" type="submit">Регистрация</button>
        
      </form:form>

</div> <!-- /container -->