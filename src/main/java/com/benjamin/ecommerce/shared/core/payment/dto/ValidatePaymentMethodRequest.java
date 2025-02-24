package com.benjamin.ecommerce.shared.core.payment.dto;

import com.benjamin.ecommerce.shared.core.payment.models.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import java.util.Map;

public record ValidatePaymentMethodRequest(
    @NotNull(message = "Payment method can not be null") PaymentMethod method,
    @NotNull(message = "Details must contains key values data") Map<String, String> details) {}
