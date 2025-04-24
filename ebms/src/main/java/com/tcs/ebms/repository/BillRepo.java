package com.tcs.ebms.repository;

import com.tcs.ebms.model.Bill;
import com.tcs.ebms.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepo extends JpaRepository<Bill, Long> {
    List<Bill> findByCustomer(Customer customer);
    List<Bill> findByCustomerAndStatus(Customer customer, String status);
}