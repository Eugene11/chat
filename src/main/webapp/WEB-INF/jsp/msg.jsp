<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


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
				            			
				            			<!--  -->
				            			<%
				            				if( !(year > 0) && !(day > 0) && !(hour > 0) && !(minute > 0) && !(second > 0) && (diff>0) ){
				            			%>
				            				менее секунды/
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