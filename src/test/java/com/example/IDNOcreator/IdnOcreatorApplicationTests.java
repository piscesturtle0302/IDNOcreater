package com.example.IDNOcreator;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.awt.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
class IdnOcreatorApplicationTests {
	@Autowired
	WebApplicationContext webApplicationContext;
	MockMvc mockMvc;

	@BeforeEach
	public void setup(){
		System.out.println("...... running setup");
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	void contextLoadsMock1() throws Exception {
		System.out.print("contextLoadsMock1 start");
		String url = "/IDNOcreator";

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();

		int status = result.getResponse().getStatus();
		System.out.println("contextLoadsMock1 : " + status);
		//Assert.assertEquals(200, status);

	}

}
