package com.codechallenge.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactInformationDTO {
	
	private Long id;
	private String phoneNumber;
	private String email;

}