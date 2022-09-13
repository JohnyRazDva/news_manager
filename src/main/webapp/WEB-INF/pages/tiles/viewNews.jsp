<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.view_news_label_news_title.name"
	var="label_news_title" />
<fmt:message bundle="${loc}" key="local.view_news_label_news_date.name"
	var="label_news_date" />
<fmt:message bundle="${loc}" key="local.view_news_label_news_brief.name"
	var="label_news_brief" />
<fmt:message bundle="${loc}" key="local.view_news_label_news_content.name"
	var="label_news_content" />
<fmt:message bundle="${loc}" key="local.view_news_button_edit.name"
	var="button_edit" />
<fmt:message bundle="${loc}" key="local.view_news_button_delete.name"
	var="button_delete" />


<div class="view-news">
	<div class="add-table-margin">
		<table class="news_text_format">
			<tr>
				<td class="space_around_title">${label_news_title}</td>
	
				<td class="space_around_view_text">
					<div class="word-breaker">
						<c:out value="${sessionScope.news.title }" />
					</div>
				</td>
			</tr>
			<tr>
				<td class="space_around_title">${label_news_date}</td>
	
				<td class="space_around_view">
					<div class="word-breaker">
						<c:out value="${sessionScope.news.newsDate }" />
					</div>
				</td>
			</tr>
			<tr>
				<td class="space_around_title">${label_news_brief}</td>
				<td class="space_around_view_text">
					<div class="word-breaker">
						<c:out value="${sessionScope.news.briefNews }" />
					</div>
				</td>
			</tr>
			<tr>
				<td class="space_around_title">${label_news_content}</td>
				<td class="space_around_view_text">
					<div class="word-breaker">
						<c:out value="${sessionScope.news.content }" />
					</div>
				</td>
			</tr>
		</table>
	</div>
	
	
	<c:if test="${sessionScope.role eq 'admin'}">
		<div class="view-button">
			<div class="first-view-button">
				<form action="controller" method="post">
					<input type="hidden" name="command" value="go_to_edit_news" /> <input
						type="hidden" name="id" value="${news.idNews}" /> <input
						type="submit" value="${button_edit}" class="button" />
				</form>
			</div>
			
			<div class="second-view-button">
				<form action="controller" method="post">
					<input type="hidden" name="command" value="delete_news" /> <input
						type="hidden" name="id" value="${news.idNews}" /> <input
						type="submit" value="${button_delete}" class="button" />
				</form>
			</div>
		</div>
	</c:if>
</div>