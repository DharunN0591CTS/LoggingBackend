package com.dharun.vps.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.dharun.vps.entity.entryDetails;
import com.dharun.vps.entity.exitDetails;
import com.dharun.vps.service.loggingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@WebMvcTest(loggingController.class)
public class loggingControllerTest {
	
	@MockBean
	loggingService logServiceObj;
	
	@Autowired
	MockMvc mockMvc;
	
	
	@Test
	public void testGetEntryDetails() throws Exception{
		entryDetails entObj = new entryDetails(1,"Normal","2W","TN01AA1000",LocalDate.parse("2025-09-19"),LocalTime.parse("07:36"),"EP001","A12");
		List<entryDetails> entryList = Collections.singletonList(entObj);
		ResponseEntity<List<entryDetails>> resEntObj = new ResponseEntity<>(entryList,HttpStatus.OK);
		when(logServiceObj.getEntryDetails()).thenReturn(resEntObj);
		mockMvc.perform(get("/getEntryDetails"))
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$[0].vehicleNumber").value("TN01AA1000"));		
		
	}
	
	@Test
	public void testGetExitDetails() throws Exception{
		exitDetails exitObj = new exitDetails(1,"TN01AA1000","A12",LocalDate.parse("2025-09-19"),LocalTime.parse("13:50"));
		List<exitDetails> exitList = Collections.singletonList(exitObj);
		ResponseEntity<List<exitDetails>> resExtObj = new ResponseEntity<>(exitList,HttpStatus.OK);
		when(logServiceObj.getExitDetails()).thenReturn(resExtObj);
		mockMvc.perform(get("/getExitDetails"))
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$[0].vehicleNumber").value("TN01AA1000"));
	}
	
	@Test
	public void testPostEntryDetails() throws Exception{
		entryDetails entObj = new entryDetails(2,"Normal","2W","TN59BZ3000",LocalDate.parse("2025-10-23"),LocalTime.parse("17:07"),"EP001","A2");
		ResponseEntity<entryDetails> entResEnt = new ResponseEntity<>(entObj,HttpStatus.OK);
		Mockito.when(logServiceObj.vehicleEntryDetails(Mockito.any(entryDetails.class))).thenReturn(entResEnt);
		ObjectMapper objMap = new ObjectMapper();
		objMap.registerModule(new JavaTimeModule());
		objMap.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mockMvc.perform(post("/vehicleEntryDetails")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objMap.writeValueAsString(entObj)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.vehicleNumber").value("TN59BZ3000"));
	}
	
	@Test
	public void testPostExitDetails() throws Exception{
		exitDetails extObj = new exitDetails(2,"TN59BZ3000","A2",LocalDate.parse("2025-10-23"),LocalTime.parse("18:05"));
		ResponseEntity<exitDetails> extResEnt = new ResponseEntity<>(extObj,HttpStatus.OK);
		Mockito.when(logServiceObj.vehicleExitDetails(Mockito.any(exitDetails.class))).thenReturn(extResEnt);
		ObjectMapper objMap = new ObjectMapper();
		objMap.registerModule(new JavaTimeModule());
		objMap.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mockMvc.perform(post("/vehicleExitDetails")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objMap.writeValueAsString(extObj)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.vehicleNumber").value("TN59BZ3000"));
		
	}
}
