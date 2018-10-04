package com.capgroup.services;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DataConsumerController {

	@RequestMapping("/sale/store")
    public void storeSaleTransaction(@RequestParam(name="name", required=false, defaultValue="Java Fan") double name) {
       
		System.out.println("Value:" + name);
		//user?name=Abhijit
		System.out.println("KK");
		
    }
	
	
}
