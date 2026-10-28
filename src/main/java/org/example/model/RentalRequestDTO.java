package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class RentalRequestDTO {
    private Long id;
    private Long buildingId;
    private String buildingName;
    private Long customerId;
    private String customerName;
    private Long rentAreaId;
    private Integer rentAreaValue;
    private Integer requestedArea;
    private LocalDate startDate;
    private Integer expectedMonths;
    private String note;
    private String status;
    private Long staffId;
    private String staffName;
    private LocalDateTime createdAt;
}
