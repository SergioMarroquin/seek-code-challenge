package com.codechallenge.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codechallenge.entities.Client;
import com.codechallenge.entities.dto.ClientCalculationsDTO;
import com.codechallenge.entities.dto.ClientDTO;
import com.codechallenge.entities.dto.ClientMetricsDTO;
import com.codechallenge.exceptions.ClientNotFoundException;
import com.codechallenge.repositories.ClientRepository;
import com.codechallenge.services.utils.CalculationsUtils;
import com.codechallenge.services.utils.CustomMapper;
import com.codechallenge.services.utils.MetricsUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {
	
	private final ClientRepository clientRepository;
	
	private final CustomMapper customMapper;
	private final MetricsUtils metricsUtils;
	private final CalculationsUtils calculationsUtils;
	
	private ClientCalculationsDTO buildClientCalculationsDTO(ClientDTO clientDTO) {
		long daysToBirthday = calculationsUtils.calculateDaysToBirthday(clientDTO.getBirthdate());
		int lifeNumber = calculationsUtils.calculateLifeNumber(clientDTO.getBirthdate());
		
		ClientCalculationsDTO clientCalculationsDTO = new ClientCalculationsDTO(clientDTO);
		clientCalculationsDTO.setDaysToBirthday(daysToBirthday);
		clientCalculationsDTO.setLifeNumber(lifeNumber);
		
		return clientCalculationsDTO;
	}
	
	public List<ClientCalculationsDTO> getClients() {
		List<Client> clientsList = clientRepository.findAll();
		
		List<ClientDTO> clientsDTO = clientsList
				.stream()
				.map(customMapper::mapToClientDTO)
				.toList();
		
		return clientsDTO
				.stream()
				.map(this::buildClientCalculationsDTO)
				.toList();
	}
	
	private Client findClient(Long clientId) {
		return clientRepository
				.findById(clientId)
				.orElseThrow(() -> new ClientNotFoundException(String.format("Client with ID '%s' is not found", clientId)));
	}
	
	public ClientDTO saveClient(ClientDTO clientDTO) {
		Client client = customMapper.mapToClient(clientDTO);
		
		try {
			clientRepository.save(client);
		} catch (Exception exception) {
			log.error("Something went wrong when trying to save client: {}", exception.getMessage(), exception);
			return null;			
		}
		
		return customMapper.mapToClientDTO(client);
	}
	
	public ClientDTO updateClient(Long clientId, ClientDTO clientDTO) {
		Client client = findClient(clientId);
		
		client.setName(clientDTO.getName());
		client.setLastName(clientDTO.getLastName());
		client.setAge(clientDTO.getAge());
		client.setBirthdate(clientDTO.getBirthdate());
		
		client.getContactInformation().setEmail(clientDTO.getContactInformationDTO().getEmail());
		client.getContactInformation().setPhoneNumber(clientDTO.getContactInformationDTO().getPhoneNumber());
		
		try {
			clientRepository.save(client);
		} catch (Exception exception) {
			log.error("Something went wrong when trying to update client: {}", exception.getMessage(), exception);
			return null;			
		}
		
		return customMapper.mapToClientDTO(client);
	}
	
	public ClientMetricsDTO getClientMetrics() {
		List<Integer> clientAges = clientRepository.findAll().stream().map(Client::getAge).toList();
		
		return ClientMetricsDTO
				.builder()
				.totalClients(clientAges.size())
				.ageAverage(metricsUtils.calculateAgeAverage(clientAges))
				.ageVariance(metricsUtils.calculateAgeVariance(clientAges))
				.ageDeviation(metricsUtils.calculateAgeDeviation(clientAges))
				.build();
	}
}