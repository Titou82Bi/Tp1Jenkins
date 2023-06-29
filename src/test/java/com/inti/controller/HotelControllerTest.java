package com.inti.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.inti.model.Hotel;
import com.inti.repository.IHotelRepository;


@WebMvcTest(controllers = HotelController.class)
public class HotelControllerTest {
		
		@Autowired
		private MockMvc mock;

		
		@MockBean
		private IHotelRepository ihr;
		
		@Test
		public void saveClient() throws Exception
		{
			mock.perform(get("/creerClient"))
			.andExpect(status().isOk())

			.andDo(print());
		}
		
		@Test
		public void listeClient() throws Exception
		{
			mock.perform(get("/listeClient"))
			.andExpect(status().isOk())
			.andDo(print());
		}
		
		@Test
		public void deleteClient() throws Exception
		{
			mock.perform(get("/deleteClient/1"))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/listeClient"))
			.andDo(print());
		}
		
		@Test
		public void saveClientPost() throws Exception
		{
			mock.perform(post("/creerClient").sessionAttr("client", new Hotel("Grand Duc", 4)))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/listeClient"))
			.andDo(print());
		}
		
		@Test
		public void updateClientPost() throws Exception
		{
			mock.perform(post("/modifierClient/1").sessionAttr("client", new Hotel("Grand Duc", 4)))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/listeClient"))
			.andDo(print());
		}

	
}
