package com.dharun.vps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dharun.vps.entity.entryDetails;
import com.dharun.vps.entity.exitDetails;
import com.dharun.vps.exception.alreadyRecordFoundException;
import com.dharun.vps.exception.noDataFoundException;
import com.dharun.vps.service.loggingService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class loggingController {

	@Autowired
	loggingService logServiceObj;

	@GetMapping("/getEntryDetails")
	public ResponseEntity<List<entryDetails>> getEntryDetails() throws noDataFoundException {
		return logServiceObj.getEntryDetails();
	}

	@PostMapping("/vehicleEntryDetails")
	public ResponseEntity<entryDetails> vehicleEntryDetails(@RequestBody entryDetails entObj) throws alreadyRecordFoundException {
		return logServiceObj.vehicleEntryDetails(entObj);
	}

	@GetMapping("/getExitDetails")
	public ResponseEntity<List<exitDetails>> getExitDetails() throws noDataFoundException {
		return logServiceObj.getExitDetails();
	}

	@PostMapping("/vehicleExitDetails") 
	public ResponseEntity<exitDetails> vehicleExitDetails(@RequestBody exitDetails exitObj) throws alreadyRecordFoundException {
		return logServiceObj.vehicleExitDetails(exitObj);
	}

}
