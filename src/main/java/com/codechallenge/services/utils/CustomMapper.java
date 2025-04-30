package com.codechallenge.services.utils;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.codechallenge.entities.Client;
import com.codechallenge.entities.ContactInformation;
import com.codechallenge.entities.dto.ClientDTO;
import com.codechallenge.entities.dto.ContactInformationDTO;

@Component
public class CustomMapper {
	
	private ContactInformationDTO mapToContactInformationDTO(ContactInformation contactInformation) {
		return new ModelMapper().map(contactInformation, ContactInformationDTO.class);
	}
	
	public ClientDTO mapToClientDTO(Client client) {
		ContactInformationDTO contactInformationDTO = mapToContactInformationDTO(client.getContactInformation());
		
		ModelMapper modelMapper = new ModelMapper();
		ClientDTO clientDTO = modelMapper.map(client, ClientDTO.class);
		
		clientDTO.setContactInformationDTO(contactInformationDTO);
		
		return clientDTO;
	}
	
	private ContactInformation mapToContactInformation(ContactInformationDTO contactInformationDTO) {
		return new ModelMapper().map(contactInformationDTO, ContactInformation.class);
	}
	
	public Client mapToClient(ClientDTO clientDTO) {
        ContactInformation contactInformation = mapToContactInformation(clientDTO.getContactInformationDTO());
		
        ModelMapper modelMapper = new ModelMapper();
		Client client = modelMapper.map(clientDTO, Client.class);
		
		client.setContactInformation(contactInformation);
		contactInformation.setClient(client);
		
		return client;
	}
}