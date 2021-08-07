package com.souptik.estore.productservice.query;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.souptik.estore.productservice.core.data.ProductEntity;
import com.souptik.estore.productservice.core.data.ProductRepository;
import com.souptik.estore.productservice.core.events.ProductCreatedEvent;

@Component
@ProcessingGroup("product-group")
public class ProductEventHandler {
	
	private final ProductRepository repo;
	
	public ProductEventHandler(ProductRepository repo) {
		this.repo = repo;
	}

	// these are axon @ExceptionHandler annotation only useful for exception thrown in @EventHandler methods
	@ExceptionHandler(resultType = IllegalArgumentException.class) 
	public void handleEventHandlerException(IllegalArgumentException ex){
		// log
	}
	
	@ExceptionHandler(resultType = Exception.class) 
	public void handleEventHandlerException(Exception ex) throws Exception {
		// re-throw to ProductServiceEventsExceptionHandler
		throw ex;
	}
	
	@EventHandler
	public void on(ProductCreatedEvent event) throws Exception {
		ProductEntity entity = new ProductEntity();
		BeanUtils.copyProperties(event, entity);
		repo.save(entity);
		
		//throw new Exception("Forceful exception in @EventHandler to test rollback");
	}
}
