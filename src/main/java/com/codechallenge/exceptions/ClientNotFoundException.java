package com.codechallenge.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ClientNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private final String exceptionMessage;

}