package org.example.repository;

import org.example.entity.RentalContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalContractRepository extends JpaRepository<RentalContractEntity, Long> {
    boolean existsByRequestId(Long requestId);

    List<RentalContractEntity> findByStaffIdOrderByCreatedAtDesc(Long staffId);

    List<RentalContractEntity> findAllByOrderByCreatedAtDesc();
}
