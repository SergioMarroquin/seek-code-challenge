package com.codechallenge.services.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

@Component
public class CalculationsUtils {
	
	public long calculateDaysToBirthday(LocalDate birthdate) {
		LocalDate today = LocalDate.now();
		LocalDate birthday = LocalDate.of(today.getYear(), birthdate.getMonthValue(), birthdate.getDayOfMonth());
		
		if (today.isAfter(birthday))
			birthday = birthday.plusYears(1);
		
		return ChronoUnit.DAYS.between(today, birthday);
	}
	
	private int sumEachValue(int value) {
		String charValue = String.valueOf(value);
		
		int summatory = 0;
		for (char charUnit : charValue.toCharArray()) {
			summatory += Integer.parseInt(String.valueOf(charUnit));
		}
		
		return summatory;
	}
		
	public int calculateLifeNumber(LocalDate birthdate) {
		int lifeNumber = sumEachValue(birthdate.getDayOfMonth()) + sumEachValue(birthdate.getMonthValue()) + sumEachValue(birthdate.getYear());
	    
	    while (lifeNumber / 10 > 0) {
	    	lifeNumber = sumEachValue(lifeNumber);
	    }
		
		return lifeNumber;
	}
}