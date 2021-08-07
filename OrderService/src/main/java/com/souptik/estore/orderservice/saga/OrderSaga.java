package com.souptik.estore.orderservice.saga;

import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.CommandResultMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import com.souptik.estore.orderservice.core.events.OrderCreatedEvent;
import com.souptik.estore.shared.commands.ReserveProductCommand;

@Saga
public class OrderSaga {
	
	@Autowired
	private transient CommandGateway commandGateway;  // saga object is serialized so make the commandGateway transient
	
	@StartSaga
	@SagaEventHandler(associationProperty = "orderId")
	public void handle(OrderCreatedEvent orderCreatedEvent) {
		ReserveProductCommand reserveProductCommand = ReserveProductCommand.builder()
		.productId(orderCreatedEvent.getProductId())
		.orderId(orderCreatedEvent.getOrderId())
		.quantity(orderCreatedEvent.getQuantity())
		.userId(orderCreatedEvent.getUserId()).build();
		commandGateway.send(reserveProductCommand, new CommandCallback<ReserveProductCommand, Object>() {

			@Override
			public void onResult(CommandMessage<? extends ReserveProductCommand> commandMessage,
					CommandResultMessage<? extends Object> commandResultMessage) {
				if (commandResultMessage.isExceptional()) {
					// start compensating rollback
				}
			}
		});
	}
	
	@SagaEventHandler(associationProperty = "productId")
	public void handle(ProductReservedEvent productReservedEvent) {
		
	}
	
	@SagaEventHandler(associationProperty = "paymentId")
	public void handle(PaymentProcessedEvent paymentProcessedEvent) {
		
	}
	
	@EndSaga
	@SagaEventHandler(associationProperty = "orderId")
	public void handle(OrderProcessedEvent orderProcessedEvent) {
		
	}
}
