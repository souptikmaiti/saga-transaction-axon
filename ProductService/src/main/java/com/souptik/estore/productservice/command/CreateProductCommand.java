package com.souptik.estore.productservice.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateProductCommand {
    @TargetAggregateIdentifier
    private final String productId;
    private final String title;
    private final Float price;
    private final Integer quantity;
}
