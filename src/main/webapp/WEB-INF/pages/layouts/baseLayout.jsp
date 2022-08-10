<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="script/validation.js"></script>
<title>locale.linkname.headertitle <!-- <bean:message key="locale.linkname.headertitle" />
 -->
</title>
<style>
 html {
 background-image: url(images/back.webp);
 }
</style>

<link rel="stylesheet" type="text/css" href="styles/newsStyle.css">

</head>
<body>
	<div class="page">
		<div class="header">
			<c:import url="/WEB-INF/pages/tiles/header.jsp" />
		</div>

		<div class="base-layout-wrapper">
			<div class="menu">

				<c:if test="${not (sessionScope.user eq 'active')}">
				    
					
				</c:if>
				<c:if test="${sessionScope.user eq 'active'}">
					<c:import url="/WEB-INF/pages/tiles/menu.jsp" />
				</c:if>
			</div>

		<div class="content">

				<c:if test="${not (sessionScope.user eq 'active')}">
					<c:if test="${ (sessionScope.registration_status eq null)}">
					<c:import url="/WEB-INF/pages/tiles/guestInfo.jsp" />
					</c:if>
				<c:if test="${ (sessionScope.registration_status eq true)}">
					<c:import url="/WEB-INF/jsp/registration.jsp" />
				</c:if>
				<c:if test="${ (sessionScope.registration_status eq 'success')}">
					<c:import url="/WEB-INF/jsp/registrationSuccess.jsp" />
				</c:if>
				</c:if>
				<c:if test="${sessionScope.user eq 'active'}">
					<c:import url="/WEB-INF/pages/tiles/body.jsp" />
				</c:if>


			</div>
		</div>

		<div class="footer">

			<c:import url="/WEB-INF/pages/tiles/footer.jsp" />
		</div>
	</div>
</body>
</html>