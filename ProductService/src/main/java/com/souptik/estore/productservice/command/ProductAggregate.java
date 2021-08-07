package com.souptik.estore.productservice.command;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.souptik.estore.productservice.core.events.ProductCreatedEvent;

@Aggregate
public class ProductAggregate {
	
	@AggregateIdentifier
	private String productId;
	private String title;
	private Float price;
	private Integer quantity;
	
	
	public ProductAggregate() {
		
	}
	
	@CommandHandler
	public ProductAggregate(CreateProductCommand command) {
		
		if (command.getPrice() <= 0.0 || command.getQuantity() <=0 || command.getTitle() == null || command.getTitle().isBlank()) {
			throw new IllegalArgumentException("price or quantity must be greater than Zero or title must not be blank");
		}
		
		ProductCreatedEvent event = new ProductCreatedEvent();
		BeanUtils.copyProperties(command, event);
		
		AggregateLifecycle.apply(event);
		
		/*
		 * if(true) throw new Exception("An Exception occurred in @CommandHandler");
		 * this exception will be wrapped in CommandExecutionException
		 * Similarly any exception in @QueryHandler will be wrapped in QueryExecutionException
		 */
	}
	
	@EventSourcingHandler // used only to update the aggregate state
	public void on(ProductCreatedEvent event) {
		this.productId = event.getProductId();
		this.title = event.getTitle();
		this.price = event.getPrice();
		this.quantity = event.getQuantity();
	}

}
