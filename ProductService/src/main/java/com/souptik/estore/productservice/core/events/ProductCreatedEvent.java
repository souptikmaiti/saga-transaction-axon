package com.souptik.estore.productservice.core.events;

import lombok.Data;

@Data
public class ProductCreatedEvent {
	
	private String productId;
	private String title;
	private Float price;
	private Integer quantity;

}
