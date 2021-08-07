package com.souptik.estore.productservice.query.rest;

import lombok.Data;

@Data
public class ProductRestModel {

	private String productId;
	private String title;
	private Float price;
	private Integer quantity;

}
