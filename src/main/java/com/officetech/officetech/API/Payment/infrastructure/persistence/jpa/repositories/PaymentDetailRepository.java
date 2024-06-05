package com.officetech.officetech.API.Payment.infrastructure.persistence.jpa.repositories;

import com.officetech.officetech.API.Payment.domain.model.aggregates.PaymentDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentDetailRepository extends JpaRepository<PaymentDetail, Long> {
    Optional<PaymentDetail> findByUserId(Long userId);
    List<PaymentDetail> findAll();
}
