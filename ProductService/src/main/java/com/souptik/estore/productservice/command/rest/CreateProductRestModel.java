package com.souptik.estore.productservice.command.rest;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CreateProductRestModel {
	
	@NotBlank(message = "Title is mandatory")
    private String title;
	@Min(value = 1, message = "Price can not be less than 1")
    private Float price;
	@Min(value = 1, message = "Quantity can not be less than 1")
	@Max(value = 100, message = "Quantity can not be more than 100")
    private Integer quantity; 
}
