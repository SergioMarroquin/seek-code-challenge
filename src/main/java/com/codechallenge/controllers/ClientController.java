package com.codechallenge.controllers;

import java.util.List;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codechallenge.controllers.response.Response;
import com.codechallenge.entities.dto.ClientCalculationsDTO;
import com.codechallenge.entities.dto.ClientDTO;
import com.codechallenge.entities.dto.ClientMetricsDTO;
import com.codechallenge.services.ClientService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("client")
public class ClientController {
	
	private final ClientService clientService;
	
	@GetMapping
	public ResponseEntity<Response<List<ClientCalculationsDTO>>> getClients() {
		List<ClientCalculationsDTO> result = clientService.getClients();
		return ResponseEntity.ok(new Response<>(result));
	}

	@PostMapping
	public ResponseEntity<Response<ClientDTO>> saveClient(@RequestBody ClientDTO clientDTO) {
		ClientDTO result = clientService.saveClient(clientDTO);
		
		if (Objects.isNull(result))
			return ResponseEntity.internalServerError().build();
		
		return ResponseEntity.ok(new Response<>(result));
	}
	
	@PutMapping("/{clientId}")
	public ResponseEntity<Response<ClientDTO>> updateClient(@PathVariable Long clientId, @RequestBody ClientDTO clientDTO) {
		ClientDTO result = clientService.updateClient(clientId, clientDTO);
		
		if (Objects.isNull(result))
			return ResponseEntity.internalServerError().build();
		
		return ResponseEntity.ok(new Response<>(result));
	}
	
	@GetMapping("/metrics")
	public ResponseEntity<Response<ClientMetricsDTO>> getClientMetrics() {
		ClientMetricsDTO result = clientService.getClientMetrics();
		return ResponseEntity.ok(new Response<>(result));
	}
}