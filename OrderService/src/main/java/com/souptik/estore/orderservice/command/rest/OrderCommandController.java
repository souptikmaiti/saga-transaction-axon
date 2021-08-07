package com.souptik.estore.orderservice.command.rest;

import java.util.UUID;

import javax.validation.Valid;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.souptik.estore.orderservice.command.commands.CreateOrderCommand;
import com.souptik.estore.orderservice.core.models.OrderStatus;

@RestController
@RequestMapping("/orders")
public class OrderCommandController {
	
	private final CommandGateway commandGateway;
	
	public OrderCommandController(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}

	@PostMapping
	public String createOrder(@Valid @RequestBody OrderCreateRest model) {
		
		CreateOrderCommand command = CreateOrderCommand.builder().orderId(UUID.randomUUID().toString())
		.addressId(model.getAddressId())
		.orderStatus(OrderStatus.CREATED)
		.productId(model.getProductId())
		.quantity(model.getQuantity())
		.userId("27b95829-4f3f-4ddf-8983-151ba010e35b")
		.build();
		
		return commandGateway.sendAndWait(command);
	}

}
