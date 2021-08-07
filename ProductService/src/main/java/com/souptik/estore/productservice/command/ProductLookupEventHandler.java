package com.souptik.estore.productservice.command;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.souptik.estore.productservice.core.data.ProductLookupEntity;
import com.souptik.estore.productservice.core.data.ProductLookupRepository;
import com.souptik.estore.productservice.core.events.ProductCreatedEvent;

@Component
@ProcessingGroup("product-group")
public class ProductLookupEventHandler {
	
	private final ProductLookupRepository repo;
	
	public ProductLookupEventHandler(ProductLookupRepository repo) {
		this.repo = repo;
	}

	@EventHandler
	public void on(ProductCreatedEvent event) {
		ProductLookupEntity entity = new ProductLookupEntity(event.getProductId(), event.getTitle());
		repo.save(entity);
	}

}
