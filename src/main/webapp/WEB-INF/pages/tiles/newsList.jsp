<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.news_list_title.name"
	var="news_list_title" />
<fmt:message bundle="${loc}" key="local.news_list_edit_link.name"
	var="list_edit_link" />
<fmt:message bundle="${loc}" key="local.news_list_view_link.name"
	var="list_view_link" />
<fmt:message bundle="${loc}" key="local.news_list_delete_button.name"
	var="delete_button" />

<script type="text/javascript" src="script/checkboxHide.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<div class="body-title">
	${news_list_title}
</div>

<form action="controller" method="post" class="news-list-form">
	<c:forEach var="news" items="${requestScope.news}">
		<div class="single-news-wrapper">
			<div class="news-container">
				<div class="news-date">
					<c:out value="${news.newsDate}" />
				</div>
				<div class="single-news-header-wrapper">
					<div class="news-wrapper">
						<div class="news-title">
							<c:out value="${news.title}" />
						</div>
									
						<div class="news-content">
							<c:out value="${news.briefNews}" />
						</div>
					</div>			
				</div>
			</div>
				<div class="news-link-to-wrapper">
					<div class="link-position">
						<div class="link-container">
							<c:if test="${sessionScope.role eq 'admin'}">
								<a href="controller?command=go_to_edit_news&id=${news.idNews}">${list_edit_link}<br> </a> 
							</c:if>
							<a href="controller?command=go_to_view_news&id=${news.idNews}">${list_view_link}</a> 
						</div>
	   					<c:if test="${sessionScope.role eq 'admin'}">
	   						<input type="checkbox" name="idNews[]" value="${news.idNews}" class="checklist"/>
	   					</c:if>
				   </div>
			  </div>
		</div>
	</c:forEach>
	<div class="no-news">
		<c:if test="${requestScope.news eq null}">
       		 No news.
		</c:if>
	</div>
	<c:if test="${sessionScope.role eq 'admin'}">
		<input type="hidden" name="command" value="delete_news"/>
		<div class="news-list-form-button">
			<script type="text/javascript" src="script/checkboxHide.js"></script>
			<button type="submit" hidden class="button">${delete_button}</button>
		</div>
	</c:if>
</form>
