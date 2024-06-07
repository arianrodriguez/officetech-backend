package com.officetech.officetech.API.Payment.interfaces.rest;

import com.officetech.officetech.API.Payment.domain.model.aggregates.PaymentDetail;
import com.officetech.officetech.API.Payment.domain.services.PaymentDetailsCommandService;
import com.officetech.officetech.API.Payment.domain.services.PaymentDetailsQueryService;
import com.officetech.officetech.API.Payment.interfaces.rest.resources.CreatePaymentDetailsResource;
import com.officetech.officetech.API.Payment.interfaces.rest.resources.PaymentDetailsResource;
import com.officetech.officetech.API.Payment.interfaces.rest.transform.CreatePaymentDetailsCommandFromResourceAssembler;
import com.officetech.officetech.API.Payment.interfaces.rest.transform.PaymentDetailsResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/payment-details")
@Tag(name = "Payment Details", description = "Endpoints to manage payment details")
public class PaymentDetailsController {

    private final PaymentDetailsCommandService paymentDetailsCommandService;

    private final PaymentDetailsQueryService paymentDetailsQueryService;

    public PaymentDetailsController(PaymentDetailsCommandService paymentDetailsCommandService, PaymentDetailsQueryService paymentDetailsQueryService) {
        this.paymentDetailsCommandService = paymentDetailsCommandService;
        this.paymentDetailsQueryService = paymentDetailsQueryService;
    }

    @PostMapping
    public ResponseEntity<PaymentDetailsResource> createPaymentDetails(@RequestBody CreatePaymentDetailsResource resource){
        System.out.println("PaymentDetailsController: creating payment details");
        Optional<PaymentDetail> paymentDetail = paymentDetailsCommandService.handle(CreatePaymentDetailsCommandFromResourceAssembler.toCommandFromResource(resource));
        return paymentDetail.map(source-> new ResponseEntity<>(PaymentDetailsResourceFromEntityAssembler.toResourceFromEntity(source),CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{user_id}/isExpired")
    public ResponseEntity<Boolean> isCardExpired(@PathVariable Long user_id) {
        Optional<PaymentDetail> paymentDetail = paymentDetailsQueryService.findByUserId(user_id);
        if (paymentDetail.isPresent()) {
            int currentYear = LocalDate.now().getYear();
            int currentMonth = LocalDate.now().getMonthValue();
            int expiratoryYear = Integer.parseInt(paymentDetail.get().getExpiratoryYear());
            int expiratoryMonth = Month.valueOf(paymentDetail.get().getExpiratoryMonth().toUpperCase()).getValue();
            boolean isExpired = expiratoryYear < currentYear || (expiratoryYear == currentYear && expiratoryMonth < currentMonth);
            return ResponseEntity.ok(isExpired);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
