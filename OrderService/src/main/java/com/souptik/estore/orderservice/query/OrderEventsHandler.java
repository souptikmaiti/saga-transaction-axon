package com.souptik.estore.orderservice.query;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.souptik.estore.orderservice.core.data.OrderEntity;
import com.souptik.estore.orderservice.core.data.OrderRepository;
import com.souptik.estore.orderservice.core.events.OrderCreatedEvent;

@Component
public class OrderEventsHandler {
	
	private final OrderRepository repository;

	public OrderEventsHandler(OrderRepository repository) {
		this.repository = repository;
	}
	
	@EventHandler
	public void on(OrderCreatedEvent event) {
		OrderEntity entity = new OrderEntity();
		BeanUtils.copyProperties(event, entity);
		repository.save(entity);
	}

}
