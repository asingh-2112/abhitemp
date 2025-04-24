package com.tcs.ebms.controllers;

import com.tcs.ebms.dto.BillDTO;
import com.tcs.ebms.dto.BillResponse;
import com.tcs.ebms.dto.ComplaintResponse;
import com.tcs.ebms.dto.CustomerResponse;
import com.tcs.ebms.service.AdminService;
import com.tcs.ebms.service.ComplaintService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final ComplaintService complaintService;

    @PostMapping("/bills/createbill")
    public ResponseEntity<BillResponse> createBill(@Valid @RequestBody BillDTO billDTO) {
        BillResponse response = adminService.createBill(billDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/find/{customerId}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long customerId){
        CustomerResponse customerResponse = adminService.getCustomerById(customerId);
        return ResponseEntity.ok(customerResponse);
    }

    @GetMapping("/getcomplaints")
    public ResponseEntity<List<ComplaintResponse>> getAllComplaints() {
        return ResponseEntity.ok(
                complaintService.getAllComplaints()
        );
    }

}
