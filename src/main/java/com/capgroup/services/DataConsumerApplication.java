package com.capgroup.services;

import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.capgroup.dao.DbService;

//@EnableEurekaClient
@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoConfiguration(exclude = {
        SecurityAutoConfiguration.class
})
public class DataConsumerApplication implements CommandLineRunner{
//	@EnableEurekaClient
	public static void main(String[] args) {
		SpringApplication.run(DataConsumerApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
	    return new WebMvcConfigurerAdapter() {
	        public void addCorsMappings(CorsRegistry registry) {
	            registry.addMapping("/**").allowedOrigins("http://localhost:4200");
	        }
	    };
	}
	
	
	@Autowired
	private TopicListener listen;
	
	

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		ExecutorService executor = Executors.newScheduledThreadPool(1);
		System.out.println("--------------------------");
		Runnable task = () -> {
			try {
				new TopicListener();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		executor.execute(task);
		
	}
}
