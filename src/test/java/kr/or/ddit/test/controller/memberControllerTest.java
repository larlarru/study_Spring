package kr.or.ddit.test.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

import kr.or.ddit.test.config.WebTestConfig;

public class memberControllerTest extends WebTestConfig {

	@Test
	public void viewTest() throws Exception {
		mockMvc.perform(get("/member/view"))
		.andExpect(view().name("login"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	

}
