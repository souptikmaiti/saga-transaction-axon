package com.souptik.estore.productservice;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

import com.souptik.estore.productservice.command.interceptor.CreateProductCommandInterceptor;
import com.souptik.estore.productservice.core.errorhandling.ProductServiceEventsExceptionHandler;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Autowired
	public void registerCreateCommandProductInterceptor(ApplicationContext applicationContext, CommandBus commandBus) {
		commandBus.registerDispatchInterceptor(applicationContext.getBean(CreateProductCommandInterceptor.class));
	}
	
	@Autowired
	public void registerEventExceptionHandler(EventProcessingConfigurer config) {
		config.registerListenerInvocationErrorHandler("product-group", con -> new ProductServiceEventsExceptionHandler());
		
		/*
		 * default event error handler provided by axon
		 * 
		 * config.registerListenerInvocationErrorHandler("product-group", con -> PropagatingErrorHandler.instance()); 
		 */
		
	}

}
