package com.benjamin.ecommerce.shared.core.shipping.models;

import java.time.LocalDateTime;

public record Shipping(String id, Delivery delivery, LocalDateTime shippedAt, LocalDateTime deliveredAt) {}
