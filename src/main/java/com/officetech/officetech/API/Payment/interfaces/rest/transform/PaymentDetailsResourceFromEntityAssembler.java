package com.officetech.officetech.API.Payment.interfaces.rest.transform;

import com.officetech.officetech.API.Payment.domain.model.aggregates.PaymentDetail;
import com.officetech.officetech.API.Payment.interfaces.rest.resources.PaymentDetailsResource;

public class PaymentDetailsResourceFromEntityAssembler {
    public static PaymentDetailsResource toResourceFromEntity(PaymentDetail entity) {
        System.out.println("PaymentDetailsResourceFromEntityAssembler: converting entity to resource");
        return new PaymentDetailsResource(entity.getUserId(), entity.getCardHolderName(), entity.getCardNumber(), entity.getExpiratoryMonth(), entity.getExpiratoryYear(), entity.getCvv())    ;
    }
}
