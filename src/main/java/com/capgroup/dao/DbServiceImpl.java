package com.capgroup.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class DbServiceImpl implements DbService {

	private MockDb dbConnection;
	
	public DbServiceImpl() throws SQLException {
	
		dbConnection = new MockDb();
		System.out.println("DbServiceImpl");
	}
	
	@Override
	public void storeMessage(String message) throws SQLException {
		// TODO Auto-generated method stub
		//Create an excel sheet and store messages , append 
		dbConnection.insertMessage(message);
	}

	@Override
	public List<String> getRatioData(String id) throws SQLException {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		
	//	dbConnection.getPricesBySymbol(id);
		System.out.println("Getting ratio data : " + id);
		return dbConnection.getPricesBySymbol(id);
	}

	@Override
	public void openConnection() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void deleteCommand() throws SQLException {
		// TODO Auto-generated method stub
		dbConnection.deleteCommand();
	}

	@Override
	public boolean isConnectionClosed() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
