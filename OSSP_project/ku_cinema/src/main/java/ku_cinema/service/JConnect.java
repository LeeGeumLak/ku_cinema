package ku_cinema.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Service;


@Service
public class JConnect{

	public static final String URL="jdbc:mysql://ec2-18-216-87-117.us-east-2.compute.amazonaws.com:3306/movie?useSSL=false&requireSSL=false";
	public static final String USR="root";
	public static final String PWD="13572468";

	
	public static Connection getConnection(){
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=(Connection)DriverManager.getConnection(URL,USR,PWD);
		return con;
		}catch(Exception exception) {
			exception.printStackTrace();
			return null;
		}
	}
	
	public static Statement getStatement() {
		try {
		return (Statement) getConnection().createStatement();
		}catch(SQLException exception){
			exception.printStackTrace();
			return null;
		}
		}
	
	public static PreparedStatement getPreparedStatement(String sql) {
		try {
			return (PreparedStatement) getConnection().prepareStatement(sql);
		}catch(SQLException exception) {
			exception.printStackTrace();
			return null;
		}
	}
	
}
