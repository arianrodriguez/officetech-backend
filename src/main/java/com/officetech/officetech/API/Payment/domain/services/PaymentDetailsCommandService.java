package com.officetech.officetech.API.Payment.domain.services;

import com.officetech.officetech.API.Payment.domain.model.aggregates.PaymentDetail;
import com.officetech.officetech.API.Payment.domain.model.commands.CreatePaymentDetailsCommand;

import java.util.Optional;

public interface PaymentDetailsCommandService {
    Optional<PaymentDetail> handle(CreatePaymentDetailsCommand command);
}
