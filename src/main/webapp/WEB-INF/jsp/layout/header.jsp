 <%@page contentType="text/html" pageEncoding="UTF-8"%>
 
 <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
 
<div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="#">Чат-Project</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li class="active"><a href="/chat/">Главная</a></li>
              <c:if test="${reg  != null }"  >
              	<li><a href="/chat/jsp/register.html">Регистрация</a></li>
              </c:if>
              
              <c:if test="${auth  != null }"  >
              <li><a href="/chat/jsp/auth.html">Авторизация</a></li>
              </c:if>
              
              <c:if test="${cont  != null }"  >
              	<li><a href="/chat/jsp/cont.html">Основная страница чата</a></li>
              </c:if>
              
              <c:if test="${profile  != null }"  >
              	<li><a href="/chat/jsp/profile.html">Личный кабинет</a></li>
              </c:if>
              
              <c:if test="${userLogged  != null }"  > 
              	<li><a href="<c:url value="/j_spring_security_logout" />"  style="color:red;" >Выход</a></li>
              </c:if>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
</div>