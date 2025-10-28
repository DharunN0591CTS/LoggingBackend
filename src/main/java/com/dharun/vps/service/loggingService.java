package com.dharun.vps.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dharun.vps.entity.entryDetails;
import com.dharun.vps.entity.exitDetails;
import com.dharun.vps.exception.alreadyRecordFoundException;
import com.dharun.vps.exception.noDataFoundException;
import com.dharun.vps.repository.iLoggingRepoModule;
import com.dharun.vps.repository.iLoggingRepoModule1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class loggingService {

	@Autowired
	iLoggingRepoModule entDetRepo;
	@Autowired
	iLoggingRepoModule1 exitDetRepo;

	// Get Vehicle EntryDetails from DB.
	public ResponseEntity<List<entryDetails>> getEntryDetails() throws noDataFoundException {
		log.info("getEntryDetails() Method");
		List<entryDetails> entList = entDetRepo.findAll();
		if (entList != null)
			return new ResponseEntity<>(entList, HttpStatus.OK);
		else
			throw new noDataFoundException("NO RECORD FOUND");
	}

	// Post Vehicle Entry Details into DB.
	public ResponseEntity<entryDetails> vehicleEntryDetails(entryDetails entObj) throws alreadyRecordFoundException{
		log.info("Post vehicle entryDetails into Database");
		Optional<entryDetails> entOpObj = entDetRepo.findByVehicleNumberAndEntryTimeAndEntryDate(entObj.getVehicleNumber(), entObj.getEntryTime(), entObj.getEntryDate());
		if(entOpObj.isPresent()) {
			throw new alreadyRecordFoundException("GIVEN VEHICLE RECORD IS ALREADY FOUND");
		}
		entDetRepo.save(entObj);
		return new ResponseEntity<>(entObj, HttpStatus.OK);
	}

	// Get Vehicle ExitDetails from DB.
	public ResponseEntity<List<exitDetails>> getExitDetails() throws noDataFoundException {
		log.info("getExitDetails() Method");
		List<exitDetails> extList = exitDetRepo.findAll();
		if (extList == null)
			throw new noDataFoundException("NO RECORD FOUND");
		else
			return new ResponseEntity<>(extList, HttpStatus.OK);
	}

	// Post Vehicle Exit Details into DB.
	public ResponseEntity<exitDetails> vehicleExitDetails(exitDetails extObj) throws alreadyRecordFoundException {
		log.info("Post vehicle exitDetails into Database");
		Optional<exitDetails> extOpObj = exitDetRepo.findByVehicleNumberAndExitTimeAndExitDate(extObj.getVehicleNumber(),extObj.getExitTime(),extObj.getExitDate());
		if(extOpObj.isPresent()) {
			throw new alreadyRecordFoundException("GIVEN VEHICLE RECORD IS ALREADY FOUND");
		}
		exitDetRepo.save(extObj);
		return new ResponseEntity<>(extObj, HttpStatus.OK);
	}

}
