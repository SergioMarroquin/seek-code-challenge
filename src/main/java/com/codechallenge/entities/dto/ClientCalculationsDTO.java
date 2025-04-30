package com.codechallenge.entities.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ClientCalculationsDTO extends ClientDTO {
	
	public ClientCalculationsDTO(ClientDTO clientDTO) {
		super(clientDTO.getId(), clientDTO.getName(), clientDTO.getLastName(), clientDTO.getAge(), clientDTO.getBirthdate(), clientDTO.getContactInformationDTO());
	}
	
	private Long daysToBirthday;
	private Integer lifeNumber;
	
}