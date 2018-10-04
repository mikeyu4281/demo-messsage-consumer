package com.capgroup.services;

import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;

import com.capgroup.dao.DbService;
import com.capgroup.dao.DbServiceImpl;

public class TopicListener {

	private CountDownLatch latch = new CountDownLatch(1);

	public CountDownLatch getLatch() {
		return latch;
	}
	
	private DbService dbService;
	
	public TopicListener() throws SQLException {
		dbService = new DbServiceImpl();
		System.out.println("Initialized Topic Listener");
	}
	

	@KafkaListener(topics = "test")
	public void listen(String message) throws SQLException {
		System.out.println("Received Messasge : " + message);
		
		dbService.storeMessage(message);
	}

}
