package com.souptik.estore.productservice.core.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class ProductEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1591012928791569808L;
	
	@Id
	@Column(unique = true)
	private String productId;
	
	@Column(unique = true)
	private String title;
	private Float price;
	private Integer quantity;

}
