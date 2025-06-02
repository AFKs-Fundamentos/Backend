package com.pcmaster.AFK.paymentmanagement.interfaces.rest;


import com.pcmaster.AFK.paymentmanagement.domain.model.aggregates.Payment;
import com.pcmaster.AFK.paymentmanagement.domain.model.commands.CreatePaymentCommand;
import com.pcmaster.AFK.paymentmanagement.domain.services.PaymentCommandService;
import com.pcmaster.AFK.paymentmanagement.interfaces.rest.resources.CreatePaymentResource;
import com.pcmaster.AFK.paymentmanagement.interfaces.rest.transform.CreatePaymentCommandFromResourceAssembler;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@Tag(name = "Payment", description = "Payment Management endpoints")
public class PaymentController {
    private final PaymentCommandService paymentCommandService;

    public PaymentController(PaymentCommandService paymentCommandService) {
        this.paymentCommandService = paymentCommandService;
    }

    @PostMapping
    @Operation(summary = "Process Payment", description = "Processes a payment for a customer")
    public ResponseEntity<String> createPayment(@RequestBody CreatePaymentResource resource){
        var createPaymentCommand = CreatePaymentCommandFromResourceAssembler.toCommandFromResource(resource);
        PaymentIntent paymentIntent = paymentCommandService.handle(createPaymentCommand);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }

    @PostMapping("confirm/{id}")
    public ResponseEntity<String> confirmPaymentIntent(@PathVariable("id") String id) throws StripeException{
        PaymentIntent paymentIntent = paymentCommandService.confirmPaymentIntent(id);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }

}
