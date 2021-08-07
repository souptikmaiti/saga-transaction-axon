package com.souptik.estore.productservice.command.interceptor;

import java.util.List;
import java.util.function.BiFunction;

import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import com.souptik.estore.productservice.command.CreateProductCommand;
import com.souptik.estore.productservice.core.data.ProductLookupEntity;
import com.souptik.estore.productservice.core.data.ProductLookupRepository;

@Component
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>>{
	
	private final ProductLookupRepository repo;

	public CreateProductCommandInterceptor(ProductLookupRepository repo) {
		this.repo = repo;
	}

	@Override
	public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(
			List<? extends CommandMessage<?>> messages) {
		
		return(index, command) -> {
		
			if (CreateProductCommand.class.equals(command.getPayloadType())) {				
				CreateProductCommand createCommand = (CreateProductCommand) command.getPayload();
				
				ProductLookupEntity findByProductIdOrTitle = repo.findByProductIdOrTitle(createCommand.getProductId(), createCommand.getTitle());
				
				if(findByProductIdOrTitle != null)
					throw new IllegalStateException(
							String.format("Product with id %s Or title %s already exists", createCommand.getProductId(), createCommand.getTitle())
							);
				
				if (createCommand.getPrice() <= 0.0 || createCommand.getQuantity() <=0 || createCommand.getTitle() == null || createCommand.getTitle().isBlank()) {
					throw new IllegalArgumentException("price or quantity must be greater than Zero or title must not be blank");
				}
			}
			
			return command;
		};
	}

}
