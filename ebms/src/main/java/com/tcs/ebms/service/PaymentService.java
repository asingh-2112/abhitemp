package com.tcs.ebms.service;

import com.tcs.ebms.dto.PaymentDTO;
import com.tcs.ebms.dto.PaymentResponse;
import com.tcs.ebms.exception.PaymentException;
import com.tcs.ebms.exception.ResourceNotFoundException;
import com.tcs.ebms.model.Bill;
import com.tcs.ebms.model.Payment;
import com.tcs.ebms.repository.BillRepo;
import com.tcs.ebms.repository.PaymentRepo;
import com.tcs.ebms.util.TransactionIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class PaymentService {
    private final PaymentRepo paymentRepo;
    private final BillRepo billRepo;
    private final TransactionIdGenerator transactionIdGenerator;

    @Transactional
    public PaymentResponse processPayment(PaymentDTO paymentDTO) {
        Bill bill = billRepo.findById(paymentDTO.billId())
                .orElseThrow(() -> new ResourceNotFoundException("Bill not found with id: " + paymentDTO.billId()));

        // Validate payment amount
        if (paymentDTO.amount().compareTo(bill.getAmount()) < 0) {
            throw new IllegalArgumentException("Payment amount is less than the bill amount");
        }

        validatePayment(bill, paymentDTO.amount());

        // Create payment
        Payment payment = new Payment();
        payment.setBill(bill);
        payment.setAmount(paymentDTO.amount());
        payment.setPaymentDate(LocalDateTime.now());
        payment.setPaymentMethod(paymentDTO.paymentMethod());
        payment.setTransactionId(transactionIdGenerator.generate());
        payment.setStatus("SUCCESS");

        Payment savedPayment = paymentRepo.save(payment);

        // Update bill status
        bill.setStatus("PAID");
        bill.setPaymentId(payment.getPaymentId());
        billRepo.save(bill);

        return convertToResponse(savedPayment);
    }

    private void validatePayment(Bill bill, BigDecimal amount) {
        if (amount.compareTo(bill.getAmount()) < 0) {
            throw new PaymentException("Payment amount less than bill amount");
        }
        if ("PAID".equals(bill.getStatus())) {
            throw new PaymentException("Bill already paid");
        }
    }

    private PaymentResponse convertToResponse(Payment payment) {
        return new PaymentResponse(
                payment.getPaymentId(),
                payment.getBill().getBillId(),
                payment.getAmount(),
                payment.getPaymentDate(),
                payment.getPaymentMethod(),
                payment.getTransactionId(),
                payment.getStatus()
        );
    }
}