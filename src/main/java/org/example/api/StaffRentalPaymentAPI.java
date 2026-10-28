package org.example.api;

import org.example.model.MarkRentalPaymentPaidRequest;
import org.example.model.RentalPaymentDTO;
import org.example.service.RentalPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff/rental-payments")
public class StaffRentalPaymentAPI {

    @Autowired
    private RentalPaymentService rentalPaymentService;

    @GetMapping
    public List<RentalPaymentDTO> findMine() {
        return rentalPaymentService.getMyPayments();
    }

    @PutMapping("/{id}/mark-paid")
    public String markPaid(@PathVariable Long id, @RequestBody MarkRentalPaymentPaidRequest request) {
        try {
            rentalPaymentService.markPaid(id, request);
            return "Cap nhat thanh toan thanh cong";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
