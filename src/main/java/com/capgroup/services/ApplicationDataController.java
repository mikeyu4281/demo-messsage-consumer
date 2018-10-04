package com.capgroup.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgroup.dao.DbService;
import com.capgroup.dao.DbServiceImpl;
import com.capgroup.dao.MockDb;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Applications;

@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 4800, allowCredentials = "false")
@RestController
public class ApplicationDataController {

	@Autowired
	private EurekaClient client;
	
	private DbService dbService;

	public ApplicationDataController() throws SQLException {
		
		dbService = new DbServiceImpl();
		System.out.println();
		numbers = new ArrayList<Integer>();
	}
	
	@CrossOrigin
	@GetMapping("/applications")
	public Applications getApps() {
		
		return client.getApplications();
	}
	
	private int counter=0;
	private List<Integer> numbers;
	int sum = 0;
	
	@CrossOrigin
	@RequestMapping("/calculateAvg")
    public double calculateAverage(@RequestParam(name="number", required=false, defaultValue="--") int number) {
       
		if(number==0) {
			return 0;
		}
		Random ran = new Random();
		
		System.out.println("Entered number : " + number);
		
		numbers.add(number);
		if(counter==0) {
			
			counter++;
			
			return number;
			
		}else {
			sum = numbers.stream().mapToInt(Integer::intValue).sum();

			 counter++;		
		}
		
		System.out.println("Average : " + sum/counter);
		return sum/counter;
    }
	
	@CrossOrigin
	@RequestMapping("/clear")
    public void clear() {
       
		
		System.out.println("clear number ");
		
		numbers.clear();
		counter=0;
		sum=0;
	
		
    }
	
	@CrossOrigin
	@RequestMapping("/getPrices")
    public List<String> getPrice(@RequestParam(name="name", required=false, defaultValue="Java Fan") String name) throws SQLException {
       
		
		Random ran = new Random();
		List<Double> list = new ArrayList<Double>();
		
		
		int sum = 0;
		for(int i = 0; i < 20;i++) {
			//sum += ran.doubles(10.00, 300.00).findAny().getAsDouble();
			System.out.println(sum);
			list.add(ran.doubles(1.00, 300.00).findAny().getAsDouble());
		}
		return dbService.getRatioData(name);
		//return list;
    }
	
	@CrossOrigin
	@RequestMapping("/deleteAll")
    public void deleteAllMessages(@RequestParam(name="command", required=false, defaultValue="Java Fan") String command) throws SQLException {
       
		dbService.deleteCommand();
		//return list;
    }
	
	
	
	@CrossOrigin
	@PostMapping("/priceQuery")
	public double getPrices(int number) throws SQLException {
		
		
		Random ran = new Random();
		
		List<Double> prices = new ArrayList<Double>();
		double randomPrice = 0;
		
		int sum = 0;
		for(int i = 0; i < number;i++) {
			sum += ran.nextDouble();
			System.out.println(ran.nextDouble());
		}
		
		return sum/number;
		
		
		//return dbClient.getPricesBySymbol(symbol);
	}
	
	
}
