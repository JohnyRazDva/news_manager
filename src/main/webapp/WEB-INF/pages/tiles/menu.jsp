<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.menu_title.name"
	var="menu_title" />
<fmt:message bundle="${loc}" key="local.menu_news_list_link.name"
	var="menu_news_list_link" />
<fmt:message bundle="${loc}" key="local.menu_add_news_link.name"
	var="menu_add_news_link" />


<div class="menu-wrapper">
	<div class="list-menu-invisible-wrapper">
		<div class="list-menu-wrapper">
			<ul>
				<li class="menu-item">
					<a href="?command=go_to_news_list" class="menu-item-link">${menu_news_list_link}</a>
				</li>
				<c:if test="${sessionScope.role eq 'admin'}">
					<li class="menu-item">
						<a href="?command=go_to_add_news" class="menu-item-link">${menu_add_news_link}</a>
					</li>
				</c:if>
			</ul>
		</div>
	</div>
</div>

