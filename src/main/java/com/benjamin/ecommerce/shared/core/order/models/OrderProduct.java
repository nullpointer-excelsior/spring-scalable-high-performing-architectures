package com.benjamin.ecommerce.shared.core.order.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record OrderProduct(
    @NotEmpty String sku,
    @NotEmpty String name,
    @Positive Double price,
    @Positive Integer quantity) {}
