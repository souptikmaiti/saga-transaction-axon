package com.souptik.estore.orderservice.command.rest;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class OrderCreateRest {
	
	@NotBlank
	private String productId;
	@Min(value = 1)
	@Max(value = 100)
	private Integer quantity;
	private String addressId;
}
