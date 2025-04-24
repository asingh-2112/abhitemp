package com.tcs.ebms.service;

import com.tcs.ebms.dto.*;
import com.tcs.ebms.exception.ResourceNotFoundException;
import com.tcs.ebms.model.Complaint;
import com.tcs.ebms.model.Customer;
import com.tcs.ebms.repository.ComplaintRepo;
import com.tcs.ebms.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintService {

    private final ComplaintRepo complaintRepo;
    private final CustomerRepo customerRepo;

    @Transactional
    public ComplaintResponse createComplaint(Long customerId, ComplaintRequest request) {
        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Complaint complaint = new Complaint();
        complaint.setComplaintType(request.complaintType());
        complaint.setCategory(request.category());
        complaint.setLandMark(request.landMark());
        complaint.setCustomerName(request.customerName());
        complaint.setProblem(request.problem());
        complaint.setCostumerId(request.costumerId());
        complaint.setAddress(request.address());
        complaint.setMobileNumber(request.mobileNumber());
        complaint.setCustomer(customer);

        Complaint saved = complaintRepo.save(complaint);
        return convertToResponse(saved);
    }

    public List<ComplaintResponse> getCustomerComplaints(Long customerId) {
        return complaintRepo.findByCustomer_Cid(customerId).stream()
                .map(this::convertToResponse)
                .toList();
    }

    public List<ComplaintResponse> getAllComplaints() {
        return complaintRepo.findAll().stream()
                .map(this::convertToResponse)
                .toList();
    }

    private ComplaintResponse convertToResponse(Complaint complaint) {
        return new ComplaintResponse(
                complaint.getComplaintId(),
                complaint.getComplaintType(),
                complaint.getCategory(),
                complaint.getStatus(),
                complaint.getCreatedAt(),
                complaint.getCustomerName()
        );
    }
}
