package com.officetech.officetech.API.Payment.domain.services;

import com.officetech.officetech.API.Payment.domain.model.aggregates.PaymentDetail;
import com.officetech.officetech.API.Payment.domain.model.queries.GetPaymentDetailByUserId;

import java.util.Optional;

public interface PaymentDetailsQueryService {
    Optional<PaymentDetail> hande(GetPaymentDetailByUserId query);
}
