<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
	var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en"
	var="en_button" />
<fmt:message bundle="${loc}" key="local.sign_in_button.name"
	var="sign_in_button" />
<fmt:message bundle="${loc}" key="local.sign_out_button.name"
	var="sign_out_button" />
<fmt:message bundle="${loc}" key="local.registration_link.name"
	var="registration_link" />
<fmt:message bundle="${loc}" key="local.header_title.name"
	var="header_title" />
<fmt:message bundle="${loc}" key="local.login_label.name"
	var="login_label" />
<fmt:message bundle="${loc}" key="local.password_label.name"
	var="password_label" />
<fmt:message bundle="${loc}" key="local.wrong_login_password.name"
	var="wrong_login_password" />
		
<body>
	<div class="wrapper">
		<div class="newstitle">
			<font color="white">
				${header_title}
			</font>
		</div>
		<div class="local-link">
			<c:if test="${not (sessionScope.user eq 'active')}">
				<div align="right" >
					<form action="controller" method="post">
						<input type="hidden" name="command" value="do_sign_in" /> 
						${login_label} <input type="text" name="login" value="" /><br /> 
						${password_label} <input type="password" name="password" value="" /><br />
						<c:if test="${not (sessionScope.AuthenticationError eq null)}">
							<font color="red"> 
							   <c:out value="${wrong_login_password}" />
							</font> 
						</c:if>
						 <a href="controller?command=go_to_registration_page" class="registration-link">${registration_link}</a> <input type="submit" value="${sign_in_button}" class="button"/><br />
					</form>
				</div>
			</c:if>
			<c:if test="${not (sessionScope.user eq 'active')}">
				<div align="right" class="localization">
					<form action="controller" method="post">
						<input type="hidden" name="command" value="change_local" /> 
						<input type="hidden" name="local" value="ru" /> 
						<input type="submit" value="${ru_button}" class="button" /><br />
					</form>
					<form action="controller" method="post">
						<input type="hidden" name="command" value="change_local" /> 
						<input type="hidden" name="local" value="en" /> 
						<input type="submit" value="${en_button}" class="button" /><br />
					</form>
				</div>
			</c:if>
			<c:if test="${sessionScope.user eq 'active'}">
				<div align="right">
					<form action="controller" method="post">
						<input type="hidden" name="command" value="do_sign_out" /> 
						<input type="submit" value="${sign_out_button}" class="button" /><br />
					</form>
				</div>
			</c:if>
		</div>
	</div>
</body>
