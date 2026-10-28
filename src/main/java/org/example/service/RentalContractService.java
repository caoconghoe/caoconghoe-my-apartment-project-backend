package org.example.service;

import org.example.entity.RentalContractEntity;
import org.example.entity.RentalRequestEntity;
import org.example.entity.RentAreaEntity;
import org.example.model.CreateRentalContractRequest;
import org.example.model.RentalContractDTO;
import org.example.repository.RentAreaRepository;
import org.example.repository.RentalContractRepository;
import org.example.repository.RentalRequestRepository;
import org.example.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RentalContractService {

    @Autowired
    private RentalContractRepository rentalContractRepository;

    @Autowired
    private RentalRequestRepository rentalRequestRepository;

    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Autowired
    private RentalPaymentService rentalPaymentService;

    @Transactional
    public void createFromApprovedRequest(Long requestId, CreateRentalContractRequest request) {
        Long currentStaffId = SecurityUtils.getCurrentUserId();
        if (currentStaffId == null) {
            throw new RuntimeException("Chua dang nhap");
        }

        RentalRequestEntity rentalRequest = rentalRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Khong tim thay yeu cau thue"));

        if (rentalRequest.getStaff() == null || !rentalRequest.getStaff().getId().equals(currentStaffId)) {
            throw new RuntimeException("Ban khong duoc phan cong yeu cau nay");
        }
        if (!"approved".equalsIgnoreCase(rentalRequest.getStatus())) {
            throw new RuntimeException("Chi tao hop dong tu yeu cau da duyet");
        }
        if (rentalRequest.getRentArea() == null) {
            throw new RuntimeException("Yeu cau nay chua chon dien tich cu the");
        }
        if (rentalContractRepository.existsByRequestId(requestId)) {
            throw new RuntimeException("Yeu cau nay da tao hop dong");
        }
        if (request.getContractCode() == null || request.getContractCode().isBlank()) {
            throw new RuntimeException("Ma hop dong khong duoc de trong");
        }
        if (request.getStartDate() == null || request.getEndDate() == null) {
            throw new RuntimeException("Ngay bat dau va ket thuc khong duoc de trong");
        }
        if (!request.getEndDate().isAfter(request.getStartDate())) {
            throw new RuntimeException("Ngay ket thuc phai sau ngay bat dau");
        }
        if (request.getMonthlyRent() == null || request.getMonthlyRent().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Tien thue hang thang phai lon hon 0");
        }

        RentAreaEntity rentArea = rentalRequest.getRentArea();
        if (!"available".equalsIgnoreCase(rentArea.getStatus())) {
            throw new RuntimeException("Dien tich nay khong con san sang");
        }

        RentalContractEntity contract = new RentalContractEntity();
        contract.setRequest(rentalRequest);
        contract.setCustomer(rentalRequest.getCustomer());
        contract.setBuilding(rentalRequest.getBuilding());
        contract.setRentArea(rentArea);
        contract.setStaff(rentalRequest.getStaff());
        contract.setContractCode(request.getContractCode());
        contract.setStartDate(request.getStartDate());
        contract.setEndDate(request.getEndDate());
        contract.setMonthlyRent(request.getMonthlyRent());
        contract.setDepositAmount(request.getDepositAmount() != null ? request.getDepositAmount() : BigDecimal.ZERO);
        contract.setPaymentCycle(request.getPaymentCycle());
        contract.setSignedDate(request.getSignedDate());
        contract.setNote(request.getNote());
        contract.setStatus("active");
        rentalContractRepository.save(contract);
        rentalPaymentService.createInitialPayments(contract);

        rentalRequest.setStatus("converted");
        rentalRequestRepository.save(rentalRequest);

        rentArea.setStatus("rented");
        rentAreaRepository.save(rentArea);
    }

    @Transactional(readOnly = true)
    public List<RentalContractDTO> getMyContracts() {
        Long currentStaffId = SecurityUtils.getCurrentUserId();
        if (currentStaffId == null) {
            throw new RuntimeException("Chua dang nhap");
        }

        return rentalContractRepository.findByStaffIdOrderByCreatedAtDesc(currentStaffId)
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<RentalContractDTO> getAllContracts() {
        return rentalContractRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::toDto)
                .toList();
    }

    private RentalContractDTO toDto(RentalContractEntity item) {
        RentalContractDTO dto = new RentalContractDTO();
        dto.setId(item.getId());
        dto.setRequestId(item.getRequest() != null ? item.getRequest().getId() : null);
        dto.setContractCode(item.getContractCode());
        dto.setCustomerName(item.getCustomer().getFullname());
        dto.setBuildingName(item.getBuilding().getName());
        dto.setRentAreaValue(item.getRentArea().getValue());
        dto.setStaffName(item.getStaff().getFullname());
        dto.setStartDate(item.getStartDate());
        dto.setEndDate(item.getEndDate());
        dto.setMonthlyRent(item.getMonthlyRent());
        dto.setDepositAmount(item.getDepositAmount());
        dto.setPaymentCycle(item.getPaymentCycle());
        dto.setStatus(item.getStatus());
        dto.setSignedDate(item.getSignedDate());
        dto.setNote(item.getNote());
        return dto;
    }
}
