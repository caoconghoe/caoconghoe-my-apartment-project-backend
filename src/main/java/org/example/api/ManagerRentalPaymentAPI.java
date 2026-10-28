package org.example.api;

import org.example.model.RentalPaymentDTO;
import org.example.model.StaffTotalRevenueDTO;
import org.example.service.RentalPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/manager/rental-payments")
public class ManagerRentalPaymentAPI {

    @Autowired
    private RentalPaymentService rentalPaymentService;

    @GetMapping
    public List<RentalPaymentDTO> findAll() {
        return rentalPaymentService.getAllPayments();
    }
    @GetMapping("/staff-total-revenue")
    public List<StaffTotalRevenueDTO> staffRevenue(){
        return rentalPaymentService.staffRevenue();
    }
}
