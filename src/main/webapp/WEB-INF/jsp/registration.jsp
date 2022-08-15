<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<style>
.form{
		display: flex;
        align-items: center;
        -webkit-align-items: center;
        justify-content: center;
        height: auto;
        min-height: 500px;
        border: 2px;
        margin: 15px;
    
}
</style>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="form">
		<form action="controller" method="post">
			<input type="hidden" name=command value="do_registration">
		
   			<p>Login</p>
   			<input type="text" name="login" placeholder="Enter your login"> 
   			<p>Password</p>
   			<input type="text" name="password" placeholder="Enter your password">
   			<p>Confirm password</p>
   			<input type="text" name="confirm_password" placeholder="Enter your password one more time">
   			<p>Email</p>
   			<input type="email" name="email" placeholder="Enter your email"><br><br>
   			
   			<c:if test="${not (sessionScope.invalidRegistrationData eq null)}">
				<c:forEach var="registrationData" items="${sessionScope.invalidRegistrationData}">
					<font color="red">
						<c:out value="${registrationData}"/><br>
					</font>
				</c:forEach>
			</c:if>
   			<br>
  			<input type="submit" value="Register">
		</form>
	</div>

</body>
</html> 