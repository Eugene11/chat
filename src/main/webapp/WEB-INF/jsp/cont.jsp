
 <%@page contentType="text/html" pageEncoding="UTF-8"%>
 
 <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


 <style>
 body {
        padding-top: 60px;
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
 
 
<!--

//-->
</script> 
 <div class="container-fluid">
 	<div class="row-fluid">
 	</div>
 	<div class="span3">
          <div class="well sidebar-nav">
            <ul class="nav nav-list">
            
              <li class="nav-header">Все зарегистрированые пользователи</li>
              <c:forEach items="${listAllUsers}" var="user">
              	<li><a href="#">${user.nickName}</a></li>		
              </c:forEach>
              
            </ul>
          </div><!--/.well -->
    </div><!--/span-->
    
    <div class="span9">
          <div class="hero-unit">
          	
          	
            <div id="messages" style="height: 400px; overflow-y: auto;">
            	
            	<%
            		int ind = 0;
            		java.util.List<net.test.chat.domain.UserMessage> mesList = (java.util.ArrayList<net.test.chat.domain.UserMessage>) request.getAttribute("listUserMessages");
				%>
				
            	<c:if test="${!empty listUserMessages}">
            	
						<c:forEach items="${listUserMessages}" var="message">
								<%
				            		java.util.Date nowDate = new java.util.Date();
									net.test.chat.domain.UserMessage um = mesList.get(ind);
									java.util.Date mesDate = um.getAddedTime();
									
									
									long diff = nowDate.getTime() - mesDate.getTime();
									//java.util.Date mesageDate = request.getParameter("listUserMessages"); //.get(count).getAddedTime();
									//java.util.Date nowDate1 = message.getAddedTime();
									
									
									java.util.Calendar cal = java.util.Calendar.getInstance();
									
									java.util.Date dateDiff = new java.util.Date();
									
									cal.setTimeInMillis(diff);
									
									int year = cal.get(java.util.Calendar.YEAR)-1970;
									int day = cal.get(java.util.Calendar.DAY_OF_MONTH)-1;
									
									int hour = cal.get(java.util.Calendar.HOUR_OF_DAY)-3;
									int minute = cal.get(java.util.Calendar.MINUTE);
									int second = cal.get(java.util.Calendar.SECOND);
									
									
									//cal.setTime(dateDiff);
									
									ind++;
				            	%>
								<div id = "message" style="background-color: #0088cc;">
				            		<div>
				            			<%
				            				if(second > 0){
				            			%>
				            				<%= second %>
				            				секунд(ы)/
				            			<%
				            				}
				            			%>
				            			<!--  -->
				            			<%
				            				if(minute > 0){
				            			%>
				            				<%= minute %>
				            				минут(ы)/
				            			<%
				            				}
				            			%>
				            			<!--  -->
				            			<%
				            				if(hour > 0){
				            			%>
				            				<%= hour %>
				            				часов/
				            			<%
				            				}
				            			%>
				            			
				            			<!--  -->
				            			<%
				            				if(day > 0){
				            			%>
				            				<%= hour %>
				            				дней/
				            			<%
				            				}
				            			%>
				            			
				            			
				            			<!--  -->
				            			<%
				            				if(year > 0){
				            			%>
				            				<%= year %>
				            				лет(годов)/
				            			<%
				            				}
				            			%>
				            			назад
				            			#( ${message.getUser().getNickName()} )
				            			<span style="color:red;">#${message.getMessageContent()}</span>
				            		</div>
				            	</div>
						</c:forEach>
				</c:if>
				
				<c:if test="${empty listUserMessages}">
					<div id = "message" style="background-color: #0088cc;">
				            		<div>
				            			Сообщений пока нет
				            		</div>
				            	</div>
				</c:if>
				
            </div>
            
            <div class="fieldsCont">
            	<!-- <form id="messageFieldsCont" > -->
            	
            		
	            	<div>
		            	<textarea id="messageCont" name="messageCont" class="messageCont"></textarea>
	            	</div>
	            
		            <div class="buttonSendCont">
		            	 <p><a href="javascript:void(0)" id="sendButton" class="btn btn-primary btn-large">Послать</a></p>
		            </div>
		            
		            <div class="freqShowCont">
		            	<select id="quantityShow" class="quantityShow">
						    <option value="0" selected="selected">50</option>
						    <option value="1">100</option>
						    <option value="2">200</option>
						    <option value="3">Все</option>
						</select>
		            </div>
		            <div style="clear:both"></div>
	            
            	 <!-- </form> -->
            </div>
          </div>
    </div><!--/span-->
</div> <!-- /container -->
