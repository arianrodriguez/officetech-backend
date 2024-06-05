package com.officetech.officetech.API.Payment.application.internal.commandservices;

import com.officetech.officetech.API.Payment.domain.model.aggregates.PaymentDetail;
import com.officetech.officetech.API.Payment.domain.model.commands.CreatePaymentDetailsCommand;
import com.officetech.officetech.API.Payment.domain.services.PaymentDetailsCommandService;
import com.officetech.officetech.API.Payment.infrastructure.persistence.jpa.repositories.PaymentDetailRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentDetailsCommandServiceImpl implements PaymentDetailsCommandService {

    private final PaymentDetailRepository paymentDetailRepository;

    public PaymentDetailsCommandServiceImpl(PaymentDetailRepository paymentDetailRepository) {
        this.paymentDetailRepository = paymentDetailRepository;
    }

    @Override
    public Optional<PaymentDetail> handle(CreatePaymentDetailsCommand command){
        if(paymentDetailRepository.existsById(command.userId())){
            throw new IllegalArgumentException("Payment with same user ID already exists");
        }
        PaymentDetail paymentDetail = new PaymentDetail(command);
        paymentDetailRepository.save(paymentDetail);
        return Optional.of(paymentDetail);
    }
}
