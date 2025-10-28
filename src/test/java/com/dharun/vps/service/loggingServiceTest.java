package com.dharun.vps.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.dharun.vps.entity.entryDetails;
import com.dharun.vps.entity.exitDetails;
import com.dharun.vps.exception.alreadyRecordFoundException;
import com.dharun.vps.exception.noDataFoundException;
import com.dharun.vps.repository.iLoggingRepoModule;
import com.dharun.vps.repository.iLoggingRepoModule1;

@ExtendWith(MockitoExtension.class)
public class loggingServiceTest {

	@Mock
	private iLoggingRepoModule entDetRepo;

	@Mock
	private iLoggingRepoModule1 exitDetRepo;

	@InjectMocks
	private loggingService logService;

	private entryDetails sampleEntry;
	private exitDetails sampleExit;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		sampleEntry = new entryDetails(1, "Normal", "2W", "TN01AB1234", LocalDate.now(), LocalTime.now(), "EP001",
				"A1");
		sampleExit = new exitDetails(1, "TN01AB1234", "A1", LocalDate.now(), LocalTime.now());
	}

	@SuppressWarnings("deprecation")
	//Test case to test the service returns entry details of the vehicle when data is found on repo.
	@Test
	public void testGetEntryDetails_Success() throws noDataFoundException {
		when(entDetRepo.findAll()).thenReturn(List.of(sampleEntry));
		ResponseEntity<List<entryDetails>> response = logService.getEntryDetails();
		assertEquals(200, response.getStatusCodeValue());
		assertFalse(response.getBody().isEmpty());
	}

	//Test case to test the service throws noDataFoundException when no records are found.
	@Test
	public void testGetEntryDetails_NoDataFound() {
		when(entDetRepo.findAll()).thenReturn(null);
		assertThrows(noDataFoundException.class, () -> logService.getEntryDetails());
	}

	@SuppressWarnings("deprecation")
	//Test case to confirm that new vehicle entry is saved successfully when no duplicate record is exist.
	@Test
	public void testVehicleEntryDetails_Success() throws alreadyRecordFoundException {
		when(entDetRepo.findByVehicleNumberAndEntryTimeAndEntryDate(anyString(), any(), any()))
				.thenReturn(Optional.empty());
		when(entDetRepo.save(any(entryDetails.class))).thenReturn(sampleEntry);
		ResponseEntity<entryDetails> response = logService.vehicleEntryDetails(sampleEntry);
		assertEquals(200, response.getStatusCodeValue());
		assertEquals(sampleEntry, response.getBody());
	}

	//Test case to verify service throws alreadyRecordFoundException when a duplicate entry is detected.
	@Test
	public void testVehicleEntryDetails_AlreadyExists() {
		when(entDetRepo.findByVehicleNumberAndEntryTimeAndEntryDate(anyString(), any(), any()))
				.thenReturn(Optional.of(sampleEntry));
		assertThrows(alreadyRecordFoundException.class, () -> logService.vehicleEntryDetails(sampleEntry));
	}

	@SuppressWarnings("deprecation")
	//Test case to verify the service that returns exit details when data is on repo.
	@Test
	public void testGetExitDetails_Success() throws noDataFoundException {
		when(exitDetRepo.findAll()).thenReturn(List.of(sampleExit));
		ResponseEntity<List<exitDetails>> response = logService.getExitDetails();
		assertEquals(200, response.getStatusCodeValue());
		assertFalse(response.getBody().isEmpty());
	}

	//Test cast to test service returns noDataFoundException when no exit records is found on repo.
	@Test
	public void testGetExitDetails_NoDataFound() {
		when(exitDetRepo.findAll()).thenReturn(null);
		assertThrows(noDataFoundException.class, () -> logService.getExitDetails());
	}

	@SuppressWarnings("deprecation")
	//Test case to verify new vehicle exit record is saved successfully without duplicate record
	@Test
	public void testVehicleExitDetails_Success() throws alreadyRecordFoundException {
		when(exitDetRepo.findByVehicleNumberAndExitTimeAndExitDate(anyString(), any(), any()))
				.thenReturn(Optional.empty());
		when(exitDetRepo.save(any(exitDetails.class))).thenReturn(sampleExit);
		ResponseEntity<exitDetails> response = logService.vehicleExitDetails(sampleExit);
		assertEquals(200, response.getStatusCodeValue());
		assertEquals(sampleExit, response.getBody());
	}

	//Test case to test service throws alreadyRecordFoundException when duplicate vehicle exit record is detected.
	@Test
	public void testVehicleExitDetails_AlreadyExists() {
		when(exitDetRepo.findByVehicleNumberAndExitTimeAndExitDate(anyString(), any(), any()))
				.thenReturn(Optional.of(sampleExit));
		assertThrows(alreadyRecordFoundException.class, () -> logService.vehicleExitDetails(sampleExit));
	}
}
