package org.example.api;

import org.example.model.AvailableRentAreaDTO;
import org.example.model.CreateRentalRequest;
import org.example.model.RentalRequestDTO;
import org.example.service.RentalRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rental-requests")
public class RentalRequestAPI {

    @Autowired
    private RentalRequestService rentalRequestService;

    @GetMapping("/available-rentareas/{buildingId}")
    public List<AvailableRentAreaDTO> getAvailableRentAreas(@PathVariable Long buildingId) {
        return rentalRequestService.getAvailableRentAreas(buildingId);
    }

    @PostMapping
    public String create(@RequestBody CreateRentalRequest request) {
        try {
            rentalRequestService.create(request);
            return "Gui yeu cau thue thanh cong";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/my")
    public List<RentalRequestDTO> getMyRequests() {
        return rentalRequestService.getMyRequests();
    }
}
