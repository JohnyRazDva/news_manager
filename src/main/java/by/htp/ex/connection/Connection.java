package by.htp.ex.connection;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.maven.wagon.ConnectionException;

public class Connection {
    private static final String URL = "jdbc:mysql://localhost:8889/news";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";


    public static java.sql.Connection connection() {
        java.sql.Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            
        } 
        return connection;
    }
}
