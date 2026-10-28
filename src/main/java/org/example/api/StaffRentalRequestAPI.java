package org.example.api;

import org.example.model.RentalRequestDTO;
import org.example.model.UpdateRentalRequestStatus;
import org.example.service.RentalRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff/rental-requests")
public class StaffRentalRequestAPI {

    @Autowired
    private RentalRequestService rentalRequestService;

    @GetMapping
    public List<RentalRequestDTO> findMine() {
        return rentalRequestService.getMyAssignedRequests();
    }

    @PutMapping("/{id}/status")
    public String updateStatus(@PathVariable Long id, @RequestBody UpdateRentalRequestStatus request) {
        try {
            rentalRequestService.updateStatusForAssignedRequest(id, request);
            return "Cap nhat trang thai thanh cong";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
