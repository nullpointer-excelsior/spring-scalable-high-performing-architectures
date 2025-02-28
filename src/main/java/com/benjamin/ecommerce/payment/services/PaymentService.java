package com.benjamin.ecommerce.payment.services;

import com.benjamin.ecommerce.payment.entities.PaymentEntity;
import com.benjamin.ecommerce.payment.repositories.PaymentRepository;
import com.benjamin.ecommerce.shared.core.payment.PaymentUseCases;
import com.benjamin.ecommerce.shared.core.payment.dto.ValidatePaymentMethodRequest;
import com.benjamin.ecommerce.shared.core.payment.dto.ValidatePaymentMethodResponse;
import com.benjamin.ecommerce.shared.core.payment.models.Payment;
import com.benjamin.ecommerce.shared.core.payment.models.PaymentStatus;
import com.benjamin.ecommerce.shared.core.purchase.dto.CreatePayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class PaymentService implements PaymentUseCases {

    @Autowired
    private PaymentRepository repository;

    public ValidatePaymentMethodResponse validatePaymentMethod(ValidatePaymentMethodRequest request) {
        var isValid = !request.details().isEmpty();
        return new ValidatePaymentMethodResponse(request.method().toString(), isValid);
    }

    @Override
    public Payment create(CreatePayment request) {
        var status = PaymentStatus.ACCEPTED;
        var validDetailsValues = Arrays.asList("cardName", "cardNumber", "cvv", "code", "dni");
        if (request.details().values().stream().anyMatch(validDetailsValues::contains)) {
            status = PaymentStatus.ERROR;
        }
        if (request.details().containsValue("-1")) {
            status = PaymentStatus.DECLINED;
        }
        var entity = PaymentEntity.builder()
                .purchaseId(request.purchaseId())
                .method(request.method())
                .status(status)
                .amount(request.amount())
                .build();
        this.repository.save(entity);

        return Payment.builder()
                .id(entity.getId())
                .purchaseId(entity.getPurchaseId())
                .status(entity.getStatus())
                .method(entity.getMethod())
                .amount(entity.getAmount())
                .build();
    }
}
