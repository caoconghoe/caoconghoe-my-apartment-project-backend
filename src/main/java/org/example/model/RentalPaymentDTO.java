package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class RentalPaymentDTO {
    private Long id;
    private Long contractId;
    private String contractCode;
    private String customerName;
    private String paymentType;
    private BigDecimal amount;
    private LocalDate dueDate;
    private LocalDate paidDate;
    private String method;
    private String status;
    private String note;
}
