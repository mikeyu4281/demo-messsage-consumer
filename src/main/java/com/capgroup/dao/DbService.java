package com.capgroup.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface DbService {

	public void storeMessage(String message) throws SQLException;
	
	public List<String> getRatioData(String id) throws SQLException;
	
	public void openConnection();
	
	public void deleteCommand() throws SQLException;
	
	public boolean isConnectionClosed();
	
}
