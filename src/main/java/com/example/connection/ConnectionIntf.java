package com.example.connection;

import org.springframework.stereotype.Component;


public interface ConnectionIntf {
	
	public String checkCityConnected(String origin, String destination) throws Exception;
	
}
