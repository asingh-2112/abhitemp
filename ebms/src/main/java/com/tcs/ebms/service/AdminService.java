package com.tcs.ebms.service;

import com.tcs.ebms.dto.BillDTO;
import com.tcs.ebms.dto.BillResponse;
import com.tcs.ebms.dto.CustomerResponse;
import com.tcs.ebms.exception.ResourceNotFoundException;
import com.tcs.ebms.model.Bill;
import com.tcs.ebms.model.Customer;
import com.tcs.ebms.repository.BillRepo;
import com.tcs.ebms.repository.CustomerRepo;
import com.tcs.ebms.util.BillNumberGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final BillNumberGenerator billNumberGenerator;
    private final CustomerRepo customerRepo;
    private final BillRepo billRepo;


    @Transactional
    public BillResponse createBill(BillDTO billDTO) {
        Customer customer = customerRepo.findById(billDTO.customerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + billDTO.customerId()));

        Bill bill = new Bill();
        bill.setBillNumber(billNumberGenerator.generate());
        bill.setCustomer(customer);
        bill.setAmount(billDTO.amount());
        bill.setIssueDate(billDTO.issueDate());
        bill.setDueDate(billDTO.dueDate());
        bill.setStatus("UNPAID");
        bill.setBillingPeriod(billDTO.billingPeriod());
        bill.setDescription(billDTO.description());

        Bill savedBill = billRepo.save(bill);

        return convertToResponse(savedBill);
    }

    public CustomerResponse getCustomerById(Long customerId) {
        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        return convertToResponse(customer);
    }

    private CustomerResponse convertToResponse(Customer customer) {
        return new CustomerResponse(
                customer.getCid(),
                customer.getUsername(),
                customer.getName(),
                customer.getEmail(),
                customer.getMobileNumber(),
                customer.isActive()
        );
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
