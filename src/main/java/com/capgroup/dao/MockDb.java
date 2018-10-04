package com.capgroup.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MockDb {

	
	MockDb() throws SQLException {
		String dbUrl = "jdbc:derby:c:\\TestDB\\demo;create=true";
		try {
			conn = DriverManager.getConnection(dbUrl);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initializeDb();
	}

	private void initializeDb() throws SQLException {
		Statement stmt = conn.createStatement();
		DatabaseMetaData metaData = conn.getMetaData();

		try {
			
			ResultSet tables = metaData.getTables(null, null, "messages", null);
			if (tables.next()) {
			  // Table exists
				
				System.out.println("Table exists");
				System.out.println("Number of rows : "+ tables.getRow());
			}
			else {
			  // Table does not exist
				
				System.out.println("DNE");
			}
			stmt.execute("drop table messages");

		} catch (SQLException e) {

		}

		// System.out.println(stmt.getResultSet().getMetaData().
		stmt.executeUpdate("Create table messages (id  INT not null primary key\r\n" + 
				"        GENERATED ALWAYS AS IDENTITY\r\n" + 
				"        (START WITH 1, INCREMENT BY 1), symbol varchar(5), message varchar(30), entryTime TIMESTAMP not null)");
		// + " entryTime TIMESTAMP not null, primary key(id))");
	//	insertMessage("Hello");
		
//		testSelect();
		
	
	}

	public void testSelect() throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM messages");
        
		// print out query result
		while (rs.next()) {
			System.out.printf("%d\t%s\n", rs.getInt("id"), rs.getString("message"), rs.getTimestamp("entryTime"));
		}
	}
	
	
	
	public void insertMessage(String message) throws SQLException {
		Calendar calendar = Calendar.getInstance();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(calendar.getTime().getTime());
		Statement stmt = conn.createStatement();
		System.out.println(timestamp.toLocalDateTime());
		
		if(message.contains(":")) {
			String symbol = message.substring(0, message.indexOf(":"));
			System.out.println("Symbol : " + symbol);
			stmt.executeUpdate("insert into messages (symbol,message,entryTime) values ('" + symbol + "','" + message + "','" + timestamp + "')");

		}else {
			
		}
		
	}

	public List<String> getPricesBySymbol(String symbol) throws SQLException{
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT message FROM messages");
        List<String> list = new ArrayList<String>();
        
     
		while (rs.next()) {
			System.out.println("**" + rs.getString("message"));
			list.add(rs.getString("message"));
		}
		
		return list;
	}
	
	public void deleteCommand() throws SQLException{
		Statement stmt = conn.createStatement();
		
		boolean flag = stmt.execute("Delete from messages where id >2");
		
		System.out.println("delete flag "+ flag);
	//	ResultSet rs = stmt.executeQuery("DELETE FROM messages WHERE 1=1");
       
	}
	
	Connection conn;

	public static void main(String[] args) throws SQLException {
		MockDb app = new MockDb();

		// app.connectionToDerby();
		// app.normalDbUsage();
	}

	public void normalDbUsage() throws SQLException {
		Statement stmt = conn.createStatement();

		// drop table
		// stmt.executeUpdate("Drop Table users");

		// create table
		stmt.executeUpdate("Create table users (id int primary key, name varchar(30))");

		// insert 2 rows
		stmt.executeUpdate("insert into users values (1,'tom')");
		stmt.executeUpdate("insert into users values (2,'peter')");

		// query
		ResultSet rs = stmt.executeQuery("SELECT * FROM users");

		// print out query result
		while (rs.next()) {
			System.out.printf("%d\t%s\n", rs.getInt("id"), rs.getString("name"));
		}
	}

}
