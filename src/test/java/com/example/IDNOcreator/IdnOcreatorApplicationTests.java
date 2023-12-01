package com.example.IDNOcreator;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = IdnOcreatorApplication.class)
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
		System.out.println("contextLoadsMock1 start");
		String url = "/";

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.ALL))
				.andReturn();

		System.out.println(result);

		int status = result.getResponse().getStatus();
		System.out.println("contextLoadsMock1 : " + status);
		Assert.assertEquals(200, status);
		//E836095538
	}

	@Test
	void contextLoadsMock2() throws Exception {
		System.out.println("contextLoadsMock2 start");
		String url = "/IDNO/creatNewRecidenceIdNo";

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		int status = result.getResponse().getStatus();
		String resultbody = result.getResponse().getContentAsString();
		JSONObject resultObject = new JSONObject(resultbody);
		JSONObject resultData = resultObject.getJSONObject("data");

		System.out.println("結果1: " + status);
		System.out.println("結果2: " + resultbody);
		System.out.println("結果3: " + resultObject.get("code"));
		System.out.println("結果4: " + resultObject.get("data"));
		System.out.println("結果5: " + resultData.get("newResidenceIdNo"));

		Assert.assertEquals(200, status);
		Assert.assertTrue(StringUtils.isNotEmpty(resultData.get("newResidenceIdNo").toString()));
		//E836095538
	}

	@Test
	void contextLoadsMock3() throws Exception {
		System.out.println("contextLoadsMock3 start");
		String url = "/IDNO/checkNewResidenceIdNo";

		JSONObject request = new JSONObject();
		request.put("newResidenceIdNo", "E836095538");
		//request.put("newResidenceIdNo", "A123456789");

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(url)
						.contentType(MediaType.APPLICATION_JSON)
						.content(request.toString()))
				.andReturn();

		int status = result.getResponse().getStatus();
		String resultbody = result.getResponse().getContentAsString();
		JSONObject resultObject = new JSONObject(resultbody);
		JSONObject resultData = resultObject.getJSONObject("data");

		System.out.println("結果1: " + status);
		System.out.println("結果2: " + resultbody);
		System.out.println("結果3: " + resultObject.get("code"));
		System.out.println("結果4: " + resultObject.get("data"));
		System.out.println("結果5: " + resultData.get("result"));

		Assert.assertEquals(200, status);
		Assert.assertTrue(resultData.getBoolean("result"));
		//E836095538
	}
}
