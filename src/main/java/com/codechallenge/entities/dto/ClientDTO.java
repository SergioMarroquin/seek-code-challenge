package com.codechallenge.entities.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
	
	private Long id;
	
	private String name;
	private String lastName;	
	private Integer age;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate birthdate;
	
	@JsonProperty("contactInformation")
	private ContactInformationDTO contactInformationDTO;

}