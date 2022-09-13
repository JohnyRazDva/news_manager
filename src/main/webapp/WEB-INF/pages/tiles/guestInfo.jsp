<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.quest_news_title.name"
	var="quest_news_title" />

<div class="body-title">
	 ${quest_news_title}
</div>

<form action="command.do?method=delete" method="post">
	<c:forEach var="news" items="${requestScope.news}">
		<div class="single-news-wrapper">
			<div class="single-news-header-wrapper">
				<div class="news-wrapper">
					<div class="news-date">
						<c:out value="${news.newsDate}" />
					</div>
					<div class="news-title">
						<c:out value="${news.title}" />
					</div>
					<div class="news-content">
						<c:out value="${news.briefNews}" />
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
</form>
