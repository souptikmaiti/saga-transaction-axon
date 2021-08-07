package com.souptik.estore.productservice.command.rest;

import java.util.UUID;

import javax.validation.Valid;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.souptik.estore.productservice.command.CreateProductCommand;

@RestController
@RequestMapping("/products")
public class ProductCommandController {
    
    private final CommandGateway commandGateway;
    
    public ProductCommandController(Environment env, CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}
    
    @PostMapping
    public String createProduct(@Valid  @RequestBody CreateProductRestModel model) {
    	CreateProductCommand createProdCommand = CreateProductCommand.builder()
    	.title(model.getTitle())
    	.price(model.getPrice())
    	.quantity(model.getQuantity())
    	.productId(UUID.randomUUID().toString())
    	.build();
    	
    	String result = commandGateway.sendAndWait(createProdCommand);    	
        return result;
    }

}
