package com.codechallenge.services.utils;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MetricsUtils {
	
	public double calculateAgeAverage(List<Integer> agesList) {
		return agesList.stream().mapToInt(Integer::intValue).average().orElse(0);
	}
	
	private double sumSquaredDifferences(List<Integer> agesList, double average) {
		return agesList.stream().mapToDouble(age -> Math.pow((age - average), 2)).sum();
	}
	
	public double calculateAgeVariance(List<Integer> agesList) {
		double average = calculateAgeAverage(agesList);
		double sumSquaredDifferences = sumSquaredDifferences(agesList, average);
		
		return sumSquaredDifferences/agesList.size();
	}
	
	public double calculateAgeDeviation(List<Integer> agesList) {
		return Math.sqrt(calculateAgeVariance(agesList));
	}	
}