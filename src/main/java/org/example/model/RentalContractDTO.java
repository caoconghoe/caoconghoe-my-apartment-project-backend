package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class RentalContractDTO {
    private Long id;
    private Long requestId;
    private String contractCode;
    private String customerName;
    private String buildingName;
    private Integer rentAreaValue;
    private String staffName;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal monthlyRent;
    private BigDecimal depositAmount;
    private String paymentCycle;
    private String status;
    private LocalDate signedDate;
    private String note;
}
