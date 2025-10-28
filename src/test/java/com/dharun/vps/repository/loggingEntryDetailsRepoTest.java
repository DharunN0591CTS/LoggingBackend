package com.dharun.vps.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.dharun.vps.entity.entryDetails;
import com.dharun.vps.entity.exitDetails;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class loggingEntryDetailsRepoTest {
	
	@Autowired
	iLoggingRepoModule entRepo;
	
	@Autowired
	iLoggingRepoModule1 extRepo;
	
	@Test
	public void entryDetailsTest() {
		entryDetails entObj1 = new entryDetails(1,"Normal","2W","TN01AB1234",LocalDate.parse("2025-10-24"),LocalTime.parse("18:12"),"EP001","A3");
		entRepo.save(entObj1);
		
		Optional<entryDetails> entObjOp = entRepo.findById(1);
		
		if(!entObjOp.isEmpty()) {
			entryDetails entObj2 = entObjOp.get();
			String vehicleNumber = "TN01AB1234";
			assertEquals(entObj2.getVehicleNumber(),vehicleNumber);
		}
	}
	
	@Test
	public void exitDetailsTest() {
		exitDetails extObj = new exitDetails(1,"TN01AB1234","A3",LocalDate.parse("2025-10-24"),LocalTime.parse("19:12"));
		extRepo.save(extObj);
		
		Optional<exitDetails> extObjOp = extRepo.findById(1);
		
		if(!extObjOp.isEmpty()) {
			exitDetails extObj1 = extObjOp.get();
			String vehicleNumber = "TN01AB1234";
			assertEquals(extObj1.getVehicleNumber(),vehicleNumber);
		}
	}

}
