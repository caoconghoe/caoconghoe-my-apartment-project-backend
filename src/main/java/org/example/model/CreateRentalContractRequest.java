package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CreateRentalContractRequest {
    private String contractCode;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal monthlyRent;
    private BigDecimal depositAmount;
    private String paymentCycle;
    private LocalDate signedDate;
    private String note;
}
