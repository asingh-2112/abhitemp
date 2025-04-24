package com.tcs.ebms.service;

import com.tcs.ebms.dto.BillDTO;
import com.tcs.ebms.dto.BillResponse;
import com.tcs.ebms.exception.ResourceNotFoundException;
import com.tcs.ebms.model.Bill;
import com.tcs.ebms.model.Customer;
import com.tcs.ebms.repository.BillRepo;
import com.tcs.ebms.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BillService {
    private final BillRepo billRepo;
    private final CustomerRepo customerRepo;


    public List<BillResponse> getBillsByCustomer(Long customerId) {
        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));

        List<Bill> bills = billRepo.findByCustomer(customer);
        List<BillResponse> responses = new ArrayList<>();

        for (Bill bill : bills) {
            responses.add(convertToResponse(bill));
        }

        return responses;
    }

    public List<BillResponse> getUnpaidBillsByCustomer(Long customerId) {
        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));

        List<Bill> bills = billRepo.findByCustomerAndStatus(customer, "UNPAID");
        List<BillResponse> responses = new ArrayList<>();

        for (Bill bill : bills) {
            responses.add(convertToResponse(bill));
        }
        return responses;
    }

    private BillResponse convertToResponse(Bill bill) {
        return new BillResponse(
                bill.getBillId(),
                bill.getCustomer().getCid(),
                bill.getCustomer().getName(),
                bill.getBillNumber(),
                bill.getAmount(),
                bill.getIssueDate(),
                bill.getDueDate(),
                bill.getStatus(),
                bill.getPaymentId(),
                bill.getBillingPeriod(),
                bill.getDescription()
        );
    }
}