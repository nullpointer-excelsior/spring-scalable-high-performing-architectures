package com.benjamin.ecommerce.shipping.dto;

import com.benjamin.ecommerce.shipping.models.DeliveryOption;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record CreateShippingRequest(
    @NotEmpty String fullname,
    @NotEmpty String address,
    @NotEmpty String city,
    DeliveryOption option) {}
