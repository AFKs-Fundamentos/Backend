package com.pcmaster.AFK.paymentmanagement.interfaces.rest.transform;

import com.pcmaster.AFK.paymentmanagement.domain.model.aggregates.Payment;
import com.pcmaster.AFK.paymentmanagement.interfaces.rest.resources.PaymentResource;

public class PaymentResourceFromEntityAssembler {
    public static PaymentResource toResourceFromEntity(Payment payment){
        return new PaymentResource(
                payment.getId(),
                payment.getAmount(),
                payment.getCurrency(),
                payment.getStatus(),
                payment.getDescription(),
                payment.getReceipt_email(),
                payment.getCreated()
        );
    }
}
