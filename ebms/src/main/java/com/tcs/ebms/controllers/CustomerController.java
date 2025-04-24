package com.tcs.ebms.controllers;

import com.tcs.ebms.dto.*;
import com.tcs.ebms.service.BillService;
import com.tcs.ebms.service.ComplaintService;
import com.tcs.ebms.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final BillService billService;
    private final PaymentService paymentService;
    private final ComplaintService complaintService;


    @GetMapping("/bills/{customerId}")
    public ResponseEntity<List<BillResponse>> getCustomerBills(@PathVariable Long customerId) {
        List<BillResponse> responses = billService.getBillsByCustomer(customerId);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/bills/{customerId}/unpaid")
    public ResponseEntity<List<BillResponse>> getCustomerUnpaidBills(@PathVariable Long customerId) {
        List<BillResponse> responses = billService.getUnpaidBillsByCustomer(customerId);
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/bills/payments")
    public ResponseEntity<PaymentResponse> processPayment(@Valid @RequestBody PaymentDTO paymentDTO) {
        PaymentResponse response = paymentService.processPayment(paymentDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/createcomplaint/{customerId}")
    public ResponseEntity<ComplaintResponse> createComplaint(
            @PathVariable Long customerId,
            @Valid @RequestBody ComplaintRequest request) {
        return ResponseEntity.ok(
                complaintService.createComplaint(customerId, request)
        );
    }

    @GetMapping("/getComplaints/{customerId}")
    public ResponseEntity<List<ComplaintResponse>> getCustomerComplaints(
            @PathVariable Long customerId) {
        return ResponseEntity.ok(
                complaintService.getCustomerComplaints(customerId)
        );
    }
}
