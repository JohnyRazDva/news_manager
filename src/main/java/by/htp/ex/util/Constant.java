package by.htp.ex.util;

public final class Constant {
	private Constant() {
	};

	// DAO
	public static final String SQL_USER_INSERT = "INSERT INTO user (login, password, email, access) VALUES (?,?,?,?)";
	public static final String SQL_USER_SELECT_BY_LOGIN_PASSWORD = "SELECT * FROM user WHERE login=? AND password=?";
	public static final String SQL_USER_SELECT_BY_LOGIN = "SELECT * FROM user WHERE login=?";
	public static final String SQL_USER_SELECT_BY_EMAIL = "SELECT * FROM user WHERE email=?";

	// Controller
	public static final String JSP_LOGIN_PARAM = "login";
	public static final String JSP_PASSWORD_PARAM = "password";

}
