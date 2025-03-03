package com.benjamin.ecommerce.purchase;

import com.benjamin.ecommerce.purchase.dto.CreatePurchaseRequest;
import com.benjamin.ecommerce.order.models.Order;
import com.benjamin.ecommerce.payment.models.Payment;
import com.benjamin.ecommerce.purchase.dto.PurchaseCreatedResponse;
import com.benjamin.ecommerce.shipping.models.Delivery;

public interface PurchaseProcessCoordinator {
    PurchaseCreatedResponse process(CreatePurchaseRequest request);
    void process(Payment payment);
    void process(Order order);
    void process(Delivery delivery);
}
