package com.tcs.ebms.repository;

import com.tcs.ebms.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepo extends JpaRepository<Complaint, Long> {
    List<Complaint> findByCustomer_Cid(Long customerId);
    List<Complaint> findAll();
}
