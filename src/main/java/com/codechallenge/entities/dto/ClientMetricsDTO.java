package com.codechallenge.entities.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientMetricsDTO {
	
	private Integer totalClients;
	
	private Double ageAverage;
	private Double ageVariance;
	private Double ageDeviation;
	
}