package com.souptik.estore.productservice.query;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.souptik.estore.productservice.core.data.ProductEntity;
import com.souptik.estore.productservice.core.data.ProductRepository;
import com.souptik.estore.productservice.query.rest.ProductRestModel;

@Component
public class ProductQueryEventHandler {
	
	private final ProductRepository repository;

	public ProductQueryEventHandler(ProductRepository repository) {
		this.repository = repository;
	}
	
	@QueryHandler
	public List<ProductRestModel> getAllProducts(FindProductsQuery query) {
		
		List<ProductEntity> productEntities = repository.findAll();
		
		List<ProductRestModel> list = new ArrayList<>();
		
		for (ProductEntity entity: productEntities) {
			ProductRestModel model = new ProductRestModel();
			BeanUtils.copyProperties(entity, model);
			list.add(model);
		}
		return list;
	}

}
