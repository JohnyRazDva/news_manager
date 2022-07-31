package by.htp.ex.util;

public class Constant {
	public static final String SQL_USER_INSERT = "INSERT INTO user (login, password, access) VALUES (?,?,?)";
	public static final String SQL_USER_SELECT_BY_LOGIN_PASSWORD = "SELECT * FROM user WHERE login=? AND password=?";

}
