package org.example.service;

import org.example.entity.RentalContractEntity;
import org.example.entity.RentalPaymentEntity;
import org.example.model.MarkRentalPaymentPaidRequest;
import org.example.model.RentalPaymentDTO;
import org.example.model.StaffTotalRevenueDTO;
import org.example.repository.RentalPaymentRepository;
import org.example.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RentalPaymentService {

    @Autowired
    private RentalPaymentRepository rentalPaymentRepository;

    @Transactional
    public void createInitialPayments(RentalContractEntity contract) {
        if (contract.getDepositAmount() != null && contract.getDepositAmount().signum() > 0) {
            RentalPaymentEntity deposit = new RentalPaymentEntity();
            deposit.setContract(contract);
            deposit.setPaymentType("deposit");
            deposit.setAmount(contract.getDepositAmount());
            deposit.setDueDate(contract.getSignedDate() != null ? contract.getSignedDate() : contract.getStartDate());
            deposit.setStatus("unpaid");
            deposit.setNote("Tien dat coc hop dong");
            rentalPaymentRepository.save(deposit);
        }

        RentalPaymentEntity firstRent = new RentalPaymentEntity();
        firstRent.setContract(contract);
        firstRent.setPaymentType("rent");
        firstRent.setAmount(contract.getMonthlyRent());
        firstRent.setDueDate(contract.getStartDate());
        firstRent.setStatus("unpaid");
        firstRent.setNote("Tien thue ky dau tien");
        rentalPaymentRepository.save(firstRent);
    }

    @Transactional(readOnly = true)
    public List<RentalPaymentDTO> getMyPayments() {
        Long currentStaffId = SecurityUtils.getCurrentUserId();
        if (currentStaffId == null) {
            throw new RuntimeException("Chua dang nhap");
        }

        return rentalPaymentRepository.findByContractStaffIdOrderByDueDateAsc(currentStaffId)
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<RentalPaymentDTO> getAllPayments() {
        return rentalPaymentRepository.findAllByOrderByDueDateAsc()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional
    public void markPaid(Long paymentId, MarkRentalPaymentPaidRequest request) {
        Long currentStaffId = SecurityUtils.getCurrentUserId();
        if (currentStaffId == null) {
            throw new RuntimeException("Chua dang nhap");
        }

        RentalPaymentEntity payment = rentalPaymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Khong tim thay khoan thanh toan"));

        if (!payment.getContract().getStaff().getId().equals(currentStaffId)) {
            throw new RuntimeException("Ban khong duoc quan ly khoan thanh toan nay");
        }

        payment.setPaidDate(request.getPaidDate() != null ? request.getPaidDate() : LocalDate.now());
        payment.setMethod(request.getMethod());
        payment.setNote(request.getNote());
        payment.setStatus("paid");
        rentalPaymentRepository.save(payment);
    }

    private RentalPaymentDTO toDto(RentalPaymentEntity item) {
        RentalPaymentDTO dto = new RentalPaymentDTO();
        dto.setId(item.getId());
        dto.setContractId(item.getContract().getId());
        dto.setContractCode(item.getContract().getContractCode());
        dto.setCustomerName(item.getContract().getCustomer().getFullname());
        dto.setPaymentType(item.getPaymentType());
        dto.setAmount(item.getAmount());
        dto.setDueDate(item.getDueDate());
        dto.setPaidDate(item.getPaidDate());
        dto.setMethod(item.getMethod());
        dto.setStatus(item.getStatus());
        dto.setNote(item.getNote());
        return dto;
    }
    @Transactional
    public List<StaffTotalRevenueDTO> staffRevenue(){
        return rentalPaymentRepository.staffRevenue();
    }
}
