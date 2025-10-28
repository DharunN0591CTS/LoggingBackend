package com.dharun.vps.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dharun.vps.entity.entryDetails;

public interface iLoggingRepoModule extends JpaRepository<entryDetails,Integer>  {
	Optional<entryDetails> findByVehicleNumberAndEntryTimeAndEntryDate(String vehicleNumber, LocalTime entryTime, LocalDate entryDate);

}

