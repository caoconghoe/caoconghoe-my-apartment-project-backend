package org.example.api;

import org.example.model.CreateRentalContractRequest;
import org.example.model.RentalContractDTO;
import org.example.service.RentalContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff/rental-contracts")
public class StaffRentalContractAPI {

    @Autowired
    private RentalContractService rentalContractService;

    @PostMapping("/from-request/{requestId}")
    public String createFromRequest(@PathVariable Long requestId,
                                    @RequestBody CreateRentalContractRequest request) {
        try {
            rentalContractService.createFromApprovedRequest(requestId, request);
            return "Tao hop dong thanh cong";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping
    public List<RentalContractDTO> findMine() {
        return rentalContractService.getMyContracts();
    }
}
