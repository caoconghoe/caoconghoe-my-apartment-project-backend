package org.example.api;

import org.example.model.RentalContractDTO;
import org.example.service.RentalContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/manager/rental-contracts")
public class ManagerRentalContractAPI {

    @Autowired
    private RentalContractService rentalContractService;

    @GetMapping
    public List<RentalContractDTO> findAll() {
        return rentalContractService.getAllContracts();
    }
}
