package by.htp.ex.controller.impl;

public final class ControllerConstant {
	private ControllerConstant() {
	}

	public static final String JSP_LOGIN_PARAM = "login";
	public static final String JSP_PASSWORD_PARAM = "password";
	public static final String JSP_PASSWORD_CONFIRM_PARAM = "confirm_password";
	public static final String JSP_EMAIL_PARAM = "email";
	public static final String JSP_INVALID_REGISTRATION_DATA_ATT = "invalidRegistrationData";
	public static final String JSP_INVALID_NEWS_DATA_ATT = "invalidNewsData";
	public static final String JSP_REGISTRATION_STATUS_ATT = "registration_status";
	public static final String JSP_REGISTRATION_STATUS_ATT_SUCCESS = "success_done";
	public static final String JSP_REGISTRATION_STATUS_ATT_IN_PROGRESS = "in_progress";
	public static final String REDIRECT_TO_BASE_PAGE_URL = "controller?command=go_to_base_page";
	public static final String GUEST_ROLE_VALUE = "guest";
	public static final String JSP_USER_ATT = "user";
	public static final String JSP_USER_ATT_ACTIVE = "active";
	public static final String JSP_USER_ATT_NOT_ACTIVE = "not active";
	public static final String JSP_ROLE_ATT = "role";
	public static final String JSP_AUTHENTICATION_ERROR = "AuthenticationError";
	public static final String JSP_AUTHENTICATION_ERROR_VALUE = "wrong login or password";
	public static final String REDIRECT_TO_NEWS_LIST_URL = "controller?command=go_to_news_list";
	public static final String REDIRECT_TO_INDEX_URL = "index.jsp";
	public static final String JSP_NEWS_ATT = "news";
	public static final String BASE_LAYOUT_PAGE_URL = "WEB-INF/pages/layouts/baseLayout.jsp";
	public static final String JSP_PRESENTATION_ATT = "presentation";
	public static final String JSP_PRESENTATION_ATT_VALUE_NEWS_LIST = "newsList";
	public static final String JSP_PRESENTATION_ATT_VALUE_VIEV_NEWS = "viewNews";
	public static final String JSP_PRESENTATION_ATT_VALUE_EDIT_NEWS = "editNews";
	public static final String JSP_PRESENTATION_ATT_VALUE_ADD_NEWS = "addNews";
	public static final String JSP_ID_PARAM = "id";
	public static final String JSP_NEWS_TITLE_PARAM = "title";
	public static final String JSP_NEWS_BRIEF_PARAM = "brief";
	public static final String JSP_NEWS_CONTENT_PARAM = "content";
	public static final String JSP_NEWS_DATE_PARAM = "date";
	public static final String JSP_ADD_NEWS_SUCCESS_PARAM = "ADD_NEWS_SUCCESS";
	public static final String JSP_NEWS_IDS_PARAM = "idNews[]";
	public static final String JSP_EDIT_NEWS_SUCCESS_PARAM = "edit_news_success";
	public static final String JSP_ADD_NEWS_STATUS_PARAM = "ADD_NEWS_STATUS";
	public static final String JSP_EDIT_NEWS_STATUS_PARAM = "edit_news_status";

}
