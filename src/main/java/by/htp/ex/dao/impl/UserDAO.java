package by.htp.ex.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

 
import by.htp.ex.bean.User;
import by.htp.ex.connection.Connection;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.util.Constant;

public class UserDAO implements IUserDAO{
	java.sql.Connection connection = Connection.connection();

	@Override
	public boolean logination(String login, String password) throws DaoException {
		try (java.sql.Connection connection = Connection.connection();
	             PreparedStatement stmt = connection.prepareStatement(Constant.SQL_USER_SELECT_BY_LOGIN_PASSWORD)) {
	            stmt.setString(1, login);
	            stmt.setString(2,password);
	            final ResultSet resultSet = stmt.executeQuery();
	            resultSet.next();
	            
	            String accessLevel = resultSet.getString("access");
	            
	            
	           return accessLevel.equalsIgnoreCase("admin");
	        } catch (SQLException e) {
	        	return false;
	        }
		
	}
	
	public String getRole(String login, String password) {
		return "user";
	}

	@Override
	public boolean registration(User user) throws DaoException  {
		try(java.sql.Connection connection = Connection.connection();
				PreparedStatement stmt = connection.prepareStatement(Constant.SQL_USER_INSERT)){
			stmt.setString(1, user.getLogin());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getAccess());

			boolean result = stmt.execute();
			System.out.println(result + "  ZAEBOK");
			return result;
	
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
