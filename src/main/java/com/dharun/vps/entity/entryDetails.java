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
@Table(name="entryDetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class entryDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int entryId;
	String vehicleType;
    String wheelerType;
    String vehicleNumber;
    LocalDate entryDate;
    LocalTime entryTime;
    String custID;
    String slotID;
}
