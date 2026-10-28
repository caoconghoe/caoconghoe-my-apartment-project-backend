package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateRentalRequest {
    private Long buildingId;
    private Long rentAreaId;
    private Integer requestedArea;
    private LocalDate startDate;
    private Integer expectedMonths;
    private String note;
}
