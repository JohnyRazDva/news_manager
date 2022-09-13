<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>

<meta charset="UTF-8">
<title>Insert title here</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.add_news_title.name"
	var="add_news_title" />
<fmt:message bundle="${loc}" key="local.add_news_brief.name"
	var="add_news_brief" />
<fmt:message bundle="${loc}" key="local.add_news_content.name"
	var="add_news_content" />
<fmt:message bundle="${loc}" key="local.add_news_add_button.name"
	var="add_button" />
<fmt:message bundle="${loc}" key="local.edit_news_wrong_title.name"
	var="wrong_title" />
<fmt:message bundle="${loc}" key="local.edit_news_wrong_brief.name"
	var="wrong_brief" />
<fmt:message bundle="${loc}" key="local.edit_news_wrong_content.name"
	var="wrong_content" />


 

<form action="controller" method="post" class="add-form">
	<input type="hidden" name="command" value="add_news" /> 
		<div class="main">
			<div class="field">
				<label for="title">${add_news_title}</label>
				<textarea name="title" rows="5" cols="45" id="title" class="textarea"></textarea>
			</div>
			<div class="field">
				<label for="brief">${add_news_brief}</label>
				<textarea name="brief" rows="10" cols="45" id="brief" class="textarea"></textarea>
			</div>
			<div class="field">
				<label for="content">${add_news_content}</label>
				<textarea name="content" rows="10" cols="45" id="content" class="textarea"></textarea>
			</div>
			<input type="submit" value="${add_button}" class="button"/><br />
		</div>		
</form>
	 
<c:if test="${sessionScope.ADD_NEWS_SUCCESS eq 'true' }">
	<center>news added successfully</center>
</c:if>
<c:if test="${sessionScope.ADD_NEWS_SUCCESS eq 'false' }">
	<center>failed to add news</center>
</c:if> 
<div class="invalid-news-data">
	<c:if test="${not (sessionScope.invalidNewsData eq 'null')}">
		<c:forEach var="entry" items="${sessionScope.invalidNewsData}">
			<font color="red">
				<c:if test="${(entry.key eq 'title')}">
					<c:out value="${wrong_title}"/><br>
				</c:if>
				<c:if test="${(entry.key eq 'brief')}">
					<c:out value="${wrong_brief}"/><br>
				</c:if>
				<c:if test="${(entry.key eq 'content')}">
					<c:out value="${wrong_content}"/><br>
				</c:if>
			</font>
		</c:forEach>
	</c:if>
</div>
	
	