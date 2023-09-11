package com.fetch.receipt.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.AbstractMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fetch.receipt.entities.Item;
import com.fetch.receipt.entities.Receipt;
import com.fetch.receipt.models.dto.ItemDto;
import com.fetch.receipt.models.dto.ReceiptDto;
import com.fetch.receipt.service.ReceiptServiceImp;

@WebAppConfiguration
@SpringBootTest
public class ReceiptControllerTest {
	
	

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private ReceiptServiceImp receiptServiceImp;
	
	@BeforeEach
	public void setUp() {

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

	}

	@Test
	public void testSaveRecipe() throws Exception {
		ReceiptDto receipt = getReipte();
		MvcResult result = (MvcResult) mockMvc.perform(post("/receipt/process")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapToJson(receipt)))
				.andReturn();
		assertEquals(201,result.getResponse().getStatus());
	
		
	}
	
	@Test
	public void testSaveRecipeWithError() throws Exception {
		ReceiptDto receipt = getReipteWhithoutInfo();
		MvcResult result = (MvcResult) mockMvc.perform(post("/receipt/process")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapToJson(receipt)))
				.andReturn();
		assertEquals(400,result.getResponse().getStatus());
	
		
	}

	@Test
	public void testSaveRecipeWithErrorPrice() throws Exception {
		ReceiptDto receipt = getReipteWhithoutInfoPrice();
		MvcResult result = (MvcResult) mockMvc.perform(post("/receipt/process")
						.accept(MediaType.APPLICATION_JSON_VALUE)
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapToJson(receipt)))
				.andReturn();
		assertEquals(400,result.getResponse().getStatus());


	}

	private String mapToJson(ReceiptDto receipt) throws JsonProcessingException {
		return objectMapper.writeValueAsString(receipt);
	}

	private ReceiptDto getReipte() {
		ReceiptDto receipt = new ReceiptDto();
		receipt.setCreatedDate(null);
		receipt.setItems(getListItem());
		receipt.setPurchaseDate("2023-03-20");
		receipt.setPurchaseTime("13:30");
		receipt.setRetailer("Walmart");
		receipt.setTotal("25.12");
		
		return receipt;
	}
	
	private List<ItemDto> getListItem() {
		List<ItemDto> list = new ArrayList<>();
		ItemDto item1 = new ItemDto();
		item1.setPrice("12.56");
		item1.setShortDescription("Pizza");
		ItemDto item2 = new ItemDto();
		item1.setPrice("12.56");
		item1.setShortDescription("Pizza");
		
		list.add(item1);
		list.add(item2);
		return list;
	}
	
	private ReceiptDto getReipteWhithoutInfo() {
		ReceiptDto receipt = new ReceiptDto();
		receipt.setCreatedDate(null);
		receipt.setItems(getListItem());
		receipt.setPurchaseDate(null);
		receipt.setPurchaseTime("13:30");
		receipt.setRetailer("Walmart");
		receipt.setTotal("25.12");
		
		return receipt;
	}

	private ReceiptDto getReipteWhithoutInfoPrice() {
		ReceiptDto receipt = new ReceiptDto();
		receipt.setCreatedDate(null);
		receipt.setItems(getListItemPriceError());
		receipt.setPurchaseDate(null);
		receipt.setPurchaseTime("13:30");
		receipt.setRetailer("Walmart");
		receipt.setTotal("25.12");

		return receipt;
	}
	private List<ItemDto> getListItemPriceError() {
		List<ItemDto> list = new ArrayList<>();
		ItemDto item1 = new ItemDto();
		item1.setPrice("12.5a");
		item1.setShortDescription("Pizza");
		ItemDto item2 = new ItemDto();
		item1.setPrice("12.56");
		item1.setShortDescription("Pizza");

		list.add(item1);
		list.add(item2);
		return list;
	}

}
