package com.dharun.vps.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="exitDetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class exitDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int exitId;
	String vehicleNumber;
    String slotID;
    LocalDate exitDate;
    LocalTime exitTime;
	

}
