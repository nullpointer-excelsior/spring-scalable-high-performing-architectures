package com.benjamin.ecommerce.payment.listeners;

import com.benjamin.ecommerce.payment.services.PaymentService;
import com.benjamin.ecommerce.payment.events.PaymentCreatedEvent;
import com.benjamin.ecommerce.purchase.events.CreatePaymentEvent;
import com.benjamin.ecommerce.shared.integration.EventBus;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class PaymentEventListener {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private EventBus eventBus;

    @EventListener(CreatePaymentEvent.class)
    public void onPaymentRequestEvent(CreatePaymentEvent event) {
        log.info("create-payment: {}", event);
        var payment = this.paymentService.create(event.getPayload());
        this.eventBus.dispatch(new PaymentCreatedEvent(payment));
    }
}
