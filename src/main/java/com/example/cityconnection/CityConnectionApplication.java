package com.example.cityconnection;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.connection.ConnectionImpl;
import com.example.connection.ConnectionIntf;

import javax.ws.rs.QueryParam;

@Controller
@SpringBootApplication
public class CityConnectionApplication {
	
	
	ConnectionIntf cI;
	
	@RequestMapping("/connected")
	@ResponseBody
	String cityConnected(@QueryParam("origin") String origin, @QueryParam("destination") String destination) {
		
		try {
			cI = new ConnectionImpl();
			return cI.checkCityConnected(origin, destination);
		} catch (Exception e) {
			// TODO Auto-generated catch block			
			e.printStackTrace();
			System.out.println("=======NOT CONNECTED");
			return "no";
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(CityConnectionApplication.class, args);
	}

}
