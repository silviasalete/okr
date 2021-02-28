package com.okr.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class TestConnectionOracke {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		 
//        String connectionUrl = "jdbc:mysql://localhost/okr?userTimezone=true&serverTimezone=UTC";
//        String userName = "root";
//        String password = "170292sa";
//        String table = "user";
		
      String connectionUrl = "jdbc:oracle:thin:@localhost:1521:xe";
      String userName = "system";
      String password = "admin"; 
        String table = "teste";

		Connection connection  = DriverManager.getConnection(connectionUrl,userName,password);
		
		Statement statement = connection.createStatement();
		String 			sql = "SELECT * FROM "+table+" ";	
		
		ResultSet resultSet = statement.executeQuery(sql);
		
		while(resultSet.next()) {
			System.out.println(resultSet.getString(3));
//			System.out.println("id: "+resultSet.getInt("id")+", name:"+resultSet.getString("name")+", email:"+resultSet.getString("email")+"password: "+resultSet.getString("password"));
		}
		
		
		System.out.println("Conexão feita");
		
		connection.close();
		
//		try{
//			
//			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");  
//			
//			String sql = "select * from TB_USER";
//			  
//			Statement stmt=con.createStatement();
//			
//			ResultSet rs= stmt.executeQuery(sql);  
//
//			int count = 0;
//			while(rs.next())  {
//
//				count++;
//				String name = rs.getString("name");
//				System.out.println("name "+count+" : "+name);
//			}
//			   
//			con.close();  
//			  
//			}catch(Exception e){ System.out.println(e);}  
//			  
//			}   

}}
