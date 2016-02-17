package dbConnectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.User;

public class DbHandler {

	private Connection cn;
	private final String username;
	private final String password;
	private final String dbName;
	private final static String USER_TABLE_NAME="USERS";
	private final static String USER_ID_FIELD="ID";

	static{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("MySQL driver loading failed..");
		}
		System.out.println("Driver loaded");
	}

	public DbHandler(String dbName, String username, String password) {
		// TODO Auto-generated constructor stub
		this.username = username;
		this.password = password;
		this.dbName=dbName;		
	}

	private boolean initConnection() {
			try {
				cn = DriverManager.getConnection("jdbc:mysql://localhost/"+dbName, "root", "root");
				System.out.println("Connection created");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error in db connection");
			}

		return true;
	}
	
	private boolean closeConnection() {
		
		try {
			cn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
	
	public User searchUser(String userId) {
		
		if(!initConnection())
			return null;
		boolean foundFlag = false;
		String query = "select * from "+USER_TABLE_NAME+" where "+USER_ID_FIELD+" = "+userId;
		User user = new User();
		try(ResultSet rs = cn.createStatement().executeQuery(query)){
			while(rs.next()){
				foundFlag=true;
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setSalary(rs.getInt(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		closeConnection();
		if(foundFlag)
			return user;
		return null;
	}
	
	public ArrayList<User> getAllUsers() {
		if(!initConnection())
			return null;
		String query = "select * from "+USER_TABLE_NAME;
		ArrayList<User>users=new ArrayList<User>();
		User user;
		try(ResultSet rs = cn.createStatement().executeQuery(query)){
			while(rs.next()){
				user=new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setSalary(rs.getInt(3));
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		closeConnection();
		return users;
	}


}
