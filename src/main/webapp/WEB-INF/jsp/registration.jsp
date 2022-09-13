<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<!DOCTYPE html>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.registration_label_login.name"
	var="label_login" />
<fmt:message bundle="${loc}" key="local.registration_label_password.name"
	var="label_password" />
<fmt:message bundle="${loc}" key="local.registration_label_confirm_password.name"
	var="confirm_password" />
<fmt:message bundle="${loc}" key="local.registration_label_email.name"
	var="label_email" />
<fmt:message bundle="${loc}" key="local.registration_login_placeholder.name"
	var="login_placeholder" />
<fmt:message bundle="${loc}" key="local.registration_password_placeholder.name"
	var="password_placeholder" />
<fmt:message bundle="${loc}" key="local.registration_confirm_password_placeholder.name"
	var="confirm_password_placeholder" />
<fmt:message bundle="${loc}" key="local.registration_email_placeholder.name"
	var="email_placeholder" />
<fmt:message bundle="${loc}" key="local.registration_register_button.name"
	var="register_button" />
<fmt:message bundle="${loc}" key="local.registration_wrong_login.name"
	var="wrong_login" />
<fmt:message bundle="${loc}" key="local.registration_wrong_login_used.name"
	var="wrong_login_used" />
<fmt:message bundle="${loc}" key="local.registration_wrong_password.name"
	var="wrong_password" />
<fmt:message bundle="${loc}" key="local.registration_wrong_email.name"
	var="wrong_email" />
<fmt:message bundle="${loc}" key="local.registration_wrong_email_used.name"
	var="wrong_email_used" />
<fmt:message bundle="${loc}" key="local.registration_wrong_confirm_password.name"
	var="wrong_confirm_password" />
	
<div class="form">
	<form action="controller" method="post" class="registration">
		<input type="hidden" name=command value="do_registration">
		
		<p>${label_login}</p>
   		<input type="text" name="login" placeholder="${login_placeholder}">
   		<c:if test="${sessionScope.invalidRegistrationData['login'] eq 'login'}">
			<font color="red"><c:out value="${wrong_login}"/></font>
		</c:if>
		<c:if test="${sessionScope.invalidRegistrationData['loginUsed'] eq 'loginUsed'}">
			<font color="red"><c:out value="${wrong_login_used}"/></font>
		</c:if>
		
		<p>${label_password}</p>
   		<input type="text" name="password" placeholder="${password_placeholder}">
   		<c:if test="${sessionScope.invalidRegistrationData['password'] eq 'password'}">
   			<font color="red"><c:out value="${wrong_password}"/></font>
   		</c:if>
   		
   		<p>${confirm_password}</p>
   		<input type="text" name="confirm_password" placeholder="${confirm_password_placeholder}">
   		<c:if test="${sessionScope.invalidRegistrationData['confirmPassword'] eq 'confirmPassword'}">
   			<font color="red"><c:out value="${wrong_confirm_password}"/></font>
   		</c:if>
   			 
   		<p>${label_email}</p>
   		<input type="email" name="email" placeholder="${email_placeholder}">
   		<c:if test="${sessionScope.invalidRegistrationData['email'] eq 'email'}">
   			<font color="red"><c:out value="${wrong_email}"/></font>
   		</c:if>
   		
   		<c:if test="${sessionScope.invalidRegistrationData['emailUsed'] eq 'emailUsed'}">
   			<font color="red"><c:out value="${wrong_email_used}"/></font>
   		</c:if>
   		
		<br>
  		<input type="submit" value="${register_button}" class="button" >
  	</form>
</div>