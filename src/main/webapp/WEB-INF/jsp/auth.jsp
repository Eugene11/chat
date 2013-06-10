
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
      <form name='f' action="<c:url value='/j_spring_security_check' />"  method='POST' class="form-register">
        <form:errors />
        <c:if test="${error  != null }" > 
			<label style="color: red;">Ошибка авторизации</label>
		</c:if>
		
        <h2 class="form-register-heading">Пожалуйста авторизуйтесь</h2>
        
        <form:errors path="errUserExist" cssClass="error"/>
	    
	           
        <div>
	        <label class="labelField">
	          Логин
	        </label>
	        <input type="text" class="input-block-level inputField" name='j_username' placeholder="Логин">
	        <div style="clear:both">
	        </div>
        </div>
        
        <div>
	        <label class="labelField">
	          Пароль
	        </label>
	        <input type="password" class="input-block-level inputField" name='j_password' placeholder="password">
	        <div style="clear:both">
	        </div>
        </div>
        
        <button class="btn btn-large btn-primary" type="submit">Авторизация</button>
        
      </form>

</div> <!-- /container -->