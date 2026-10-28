package org.example.model;

import java.math.BigDecimal;

public interface StaffTotalRevenueDTO {
    Long getStaffId();

    String getStaffName();
    BigDecimal getTotalPaidAmount();
}
