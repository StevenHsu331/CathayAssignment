package com.cathay.assignment;

import com.cathay.assignment.api.currency.CurrencyRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cathay.assignment.api.currency.Currency;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AssignmentApplicationTests {

	private Integer savedId;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private CurrencyRepo currencyRepo;

	@BeforeEach
	public void setup() {
		currencyRepo.deleteAll();

		Currency mockData = new Currency();
		mockData.setName("USD");
		mockData.setChName("美金");
		mockData.setRate(68180.0701);
		mockData.setUpdateTime(new Date());
		Currency savedData = currencyRepo.save(mockData);
		savedId = savedData.getId();

		System.out.println("Saved Currency ID: " + savedData.getId());
	}

	@Test
	public void testGetAll() throws Exception {
		mockMvc.perform(get("/api/currency/"))
				.andExpect(status().isOk());
	}

	@Test
	public void testGet() throws Exception {
		mockMvc.perform(get(String.format("/api/currency/%d", savedId)))
				.andExpect(status().isOk());
	}

	@Test
	public void testAdd() throws Exception {
		Currency mockData = new Currency();
		mockData.setName("TWD");
		mockData.setChName("台幣");
		mockData.setRate(1d);

		mockMvc.perform(post("/api/currency/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(mockData)))
                .andExpect(status().isOk());
	}

	@Test
	public void testUpdate() throws Exception {
		Currency mockData = currencyRepo.findById(savedId).orElse(null);
		mockData.setChName("美元");

		mockMvc.perform(put("/api/currency/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(mockData)))
				.andExpect(status().isOk());
	}

	@Test
	public void testDelete() throws Exception {
		mockMvc.perform(delete(String.format("/api/currency/%d", savedId)))
				.andExpect(status().isOk());
	}

}
