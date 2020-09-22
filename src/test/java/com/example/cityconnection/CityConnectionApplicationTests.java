package com.example.cityconnection;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@WebMvcTest(CityConnectionApplication.class)
class CityConnectionApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	void testIfCityConnectedWithNoDestination() {
		
		try {
			mockMvc.perform(get("/connected")
					.queryParam("origin", "Boston").queryParam("destination", ""))
					.andExpect(status().isOk()).andExpect(jsonPath("$").value("no"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	void testIfCityConnectedWithNoOrigin() {
		
		try {
			mockMvc.perform(get("/connected")
					.queryParam("origin", "").queryParam("destination", "Boston"))
					.andExpect(status().isOk()).andExpect(jsonPath("$").value("no"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	void testIfCityConnectedExample1() {
		
		try {
			mockMvc.perform(get("/connected")
					.queryParam("origin", "Boston").queryParam("destination", "Newark"))
					.andExpect(status().isOk()).andExpect(jsonPath("$").value("yes"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testIfCityConnectedExample2() {
		
		try {
			mockMvc.perform(get("/connected")
					.queryParam("origin", "Boston").queryParam("destination", "Philadelphia"))
					.andExpect(status().isOk()).andExpect(jsonPath("$").value("yes"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testIfCityConnectedExample3() {
		
		try {
			mockMvc.perform(get("/connected")
					.queryParam("origin", "Philadelphia").queryParam("destination", "Albany"))
					.andExpect(status().isOk()).andExpect(jsonPath("$").value("no"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testIfCityConnectedNewCity() {
		
		try {
			mockMvc.perform(get("/connected")
					.queryParam("origin", "Philadelphia").queryParam("destination", "New Jersey"))
					.andExpect(status().isOk()).andExpect(jsonPath("$").value("no"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
