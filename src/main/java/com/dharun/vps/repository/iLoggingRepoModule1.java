package com.dharun.vps.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dharun.vps.entity.exitDetails;

public interface iLoggingRepoModule1 extends JpaRepository<exitDetails,Integer>{
	Optional<exitDetails> findByVehicleNumberAndExitTimeAndExitDate(String vehicleNumber, LocalTime exitTime, LocalDate exitDate);
}
