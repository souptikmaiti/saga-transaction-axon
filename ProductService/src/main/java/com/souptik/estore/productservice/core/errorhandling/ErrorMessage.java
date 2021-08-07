package com.souptik.estore.productservice.core.errorhandling;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {
	private final Date timeStampDate;
	private final String message;	
}
