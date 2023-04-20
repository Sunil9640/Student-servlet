package com.employee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.employee.model.user;

public class userdao {
	private String jdbcUrl="jdbc:mysql://localhost:3306/demo?useSSL=false";
	private String jdbcUserName="root";
	private String Password="Sunil@9640";
	
	private static final String Insert_users="INSER INTO employee" + "(name,email,country) VALUES" + "(?,?,?);";
	
	
	private static final String SELECT_USER_BY_ID ="select id,name,email,country from employee where id=?";
	private static final String SELECT_ALL_USERS ="select * from employee";
	private static final String DELETE_USERS_SQL ="delete from employee where id=?";
	private static final String UPDATE_USERS_SQL ="update employee set name=?,country=?,country=? where id=?";
	
	protected Connection getConnection() throws ClassNotFoundException {
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.driver");
			conn=DriverManager.getConnection(jdbcUrl,jdbcUserName,Password);
			
			}catch(SQLException e) {
				e.printStackTrace();
				
			}
		      return conn;
		      
	}
	// create or insert user
	public void insertuser(user User )throws SQLException {
		try(Connection connection =getConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(Insert_users)){
			preparedStatement.setString(1, User.getName());
			preparedStatement.setString(2,User.getEmail());
			preparedStatement.setString(3, User.getCountry());
			preparedStatement.executeUpdate();
			
	}catch (Exception e) {
		e.printStackTrace();
	}
	
}
	// update user
	
	public boolean updateuser (user User )throws SQLException, ClassNotFoundException {
		boolean rowUpdated;
		try(Connection connection =getConnection();
				PreparedStatement Statement=connection.prepareStatement(UPDATE_USERS_SQL)){
			Statement.setString(1, User.getName());
			Statement.setString(2,User.getEmail());
			Statement.setString(3, User.getCountry());
			Statement.setInt(4,User.getUserid());
			rowUpdated = Statement.executeUpdate()>0;
			
	}
return rowUpdated;
	
}
	
	// select user by id
	public user selectUser(int id) throws ClassNotFoundException, SQLException {
		user User=null;
       try(Connection connection=getConnection();
    		   PreparedStatement preparedStatement=connection.prepareStatement(SELECT_USER_BY_ID);){
    	   preparedStatement.setInt(1, id);
    	   System.out.println(preparedStatement);
    	   ResultSet rs=preparedStatement.executeQuery();
    	   while(rs.next()) {
    		   String name=rs.getString("name");
    		   String email=rs.getString("email");
    		   String country=rs.getString("country");
    		   User=new user(id,name,email,country);
    		   
    	   }
    	   
    	   }catch (SQLException e) {
    		   e.printStackTrace();
    	   }
          return User;	
	}
	
	
	// select users
	
//	public List<user> selectAllUsers(){
//       List<user> Users = new ArrayList<>();
//        try(Connection connection=getConnection();
//    		   PreparedStatement preparedStatement=connection.prepareStatement(SELECT_USER_BY_ID);){
//    	   System.out.println(preparedStatement);
//    	   ResultSet rs=preparedStatement.executeQuery();
//    	   while(rs.next()) {
//    		   int id=rs.getInt("id");
//    		   String name=rs.getString("name");
//    		   String email=rs.getString("email");
//    		   String country=rs.getString("country");
//    		   Users.add(new user(id,name,email,country));
//    		   
//    	   }
//    	   
//    	   }catch (SQLException e) {
//    		   e.printStackTrace();
//    	   }
//          return Users;	
//	}
//	
	// delete user method 
	
	public boolean deleteUser(int id) throws SQLException, ClassNotFoundException {
		boolean rowDeleted;
		try(Connection connection=getConnection();
				PreparedStatement statement=connection.prepareStatement(DELETE_USERS_SQL);){
			statement.setInt(1, id);
			rowDeleted =statement.executeUpdate()> 0;
		}
	        return rowDeleted;
	}
	
	
	
	
}
