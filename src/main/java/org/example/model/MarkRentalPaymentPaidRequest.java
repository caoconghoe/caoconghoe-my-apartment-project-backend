package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MarkRentalPaymentPaidRequest {
    private LocalDate paidDate;
    private String method;
    private String note;
}
