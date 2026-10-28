package org.example.api;

import org.example.model.AssignRentalRequest;
import org.example.model.RentalRequestDTO;
import org.example.service.RentalRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manager/rental-requests")
public class ManagerRentalRequestAPI {

    @Autowired
    private RentalRequestService rentalRequestService;

    @GetMapping
    public List<RentalRequestDTO> findAll() {
        return rentalRequestService.getAllRequests();
    }

    @PutMapping("/{id}/assign")
    public String assign(@PathVariable Long id, @RequestBody AssignRentalRequest request) {
        try {
            rentalRequestService.assignStaff(id, request);
            return "Phan cong yeu cau thue thanh cong";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
