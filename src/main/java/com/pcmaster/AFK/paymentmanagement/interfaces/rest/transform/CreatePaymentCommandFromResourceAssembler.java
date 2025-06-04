package com.pcmaster.AFK.paymentmanagement.interfaces.rest.transform;

import com.pcmaster.AFK.paymentmanagement.domain.model.commands.CreatePaymentCommand;
import com.pcmaster.AFK.paymentmanagement.interfaces.rest.resources.CreatePaymentResource;

public class CreatePaymentCommandFromResourceAssembler {
    public static CreatePaymentCommand toCommandFromResource(CreatePaymentResource resource){
        return new CreatePaymentCommand(resource.cartShoppingId(),resource.amount(),
                resource.currencies(),
                resource.status(),
                resource.description(),
                resource.receiptEmail()
                );
    }
}
