package org.example.repository;

import org.example.entity.RentalPaymentEntity;
import org.example.model.StaffTotalRevenueDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RentalPaymentRepository extends JpaRepository<RentalPaymentEntity, Long> {
    List<RentalPaymentEntity> findByContractStaffIdOrderByDueDateAsc(Long staffId);

    List<RentalPaymentEntity> findAllByOrderByDueDateAsc();
    @Query(value = "SELECT " +
            "u.id AS staffId, " +
            "u.fullname AS staffName, " +
            "COALESCE(SUM(rp.amount), 0) AS totalPaidAmount " +
            "FROM user u " +
            "JOIN user_role ur ON ur.userid = u.id " +
            "JOIN role r ON r.id = ur.roleid " +
            "LEFT JOIN rentalcontract rc ON rc.staffid = u.id " +
            "LEFT JOIN rentalpayment rp ON rp.contractid = rc.id AND rp.status = 'paid' " +
            "WHERE r.code = 'staff' " +
            "GROUP BY u.id, u.fullname " +
            "ORDER BY totalPaidAmount DESC",
            nativeQuery = true)
    List<StaffTotalRevenueDTO> staffRevenue();
}
