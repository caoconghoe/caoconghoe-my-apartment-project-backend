package org.example.service;

import org.example.entity.BuildingEntity;
import org.example.entity.CustomerEntity;
import org.example.entity.RentAreaEntity;
import org.example.entity.RentalRequestEntity;
import org.example.entity.UserEntity;
import org.example.model.AvailableRentAreaDTO;
import org.example.model.AssignRentalRequest;
import org.example.model.CreateRentalRequest;
import org.example.model.RentalRequestDTO;
import org.example.model.UpdateRentalRequestStatus;
import org.example.repository.BuildingRepository;
import org.example.repository.CustomerRepository;
import org.example.repository.RentAreaRepository;
import org.example.repository.RentalRequestRepository;
import org.example.repository.UserRepository;
import org.example.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RentalRequestService {

    @Autowired
    private RentalRequestRepository rentalRequestRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<AvailableRentAreaDTO> getAvailableRentAreas(Long buildingId) {
        buildingRepository.findById(buildingId)
                .orElseThrow(() -> new RuntimeException("Khong tim thay toa nha"));

        return rentAreaRepository.findByBuildingIdAndStatus(buildingId, "available")
                .stream()
                .map(area -> {
                    AvailableRentAreaDTO dto = new AvailableRentAreaDTO();
                    dto.setId(area.getId());
                    dto.setValue(area.getValue());
                    return dto;
                })
                .toList();
    }

    @Transactional
    public void create(CreateRentalRequest request) {
        UserEntity currentUser = requireCurrentCustomerUser();

        if (request.getBuildingId() == null) {
            throw new RuntimeException("BuildingId khong duoc de trong");
        }
        if (request.getRequestedArea() != null && request.getRequestedArea() <= 0) {
            throw new RuntimeException("Dien tich mong muon phai lon hon 0");
        }
        if (request.getExpectedMonths() != null && request.getExpectedMonths() <= 0) {
            throw new RuntimeException("So thang du kien phai lon hon 0");
        }

        CustomerEntity customer = customerRepository.findById(currentUser.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Khong tim thay ho so khach hang"));
        BuildingEntity building = buildingRepository.findById(request.getBuildingId())
                .orElseThrow(() -> new RuntimeException("Khong tim thay toa nha"));

        RentAreaEntity rentArea = null;
        if (request.getRentAreaId() != null) {
            rentArea = rentAreaRepository.findById(request.getRentAreaId())
                    .orElseThrow(() -> new RuntimeException("Khong tim thay dien tich thue"));

            if (!rentArea.getBuilding().getId().equals(building.getId())) {
                throw new RuntimeException("Dien tich thue khong thuoc toa nha nay");
            }
            if (!"available".equalsIgnoreCase(rentArea.getStatus())) {
                throw new RuntimeException("Dien tich thue nay hien khong san sang");
            }
        }

        if (rentArea == null && request.getRequestedArea() == null) {
            throw new RuntimeException("Can chon dien tich hoac nhap dien tich mong muon");
        }

        RentalRequestEntity entity = new RentalRequestEntity();
        entity.setCustomer(customer);
        entity.setBuilding(building);
        entity.setRentArea(rentArea);
        entity.setRequestedArea(request.getRequestedArea());
        entity.setStartDate(request.getStartDate());
        entity.setExpectedMonths(request.getExpectedMonths());
        entity.setNote(request.getNote());
        entity.setStatus("pending");

        rentalRequestRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public List<RentalRequestDTO> getMyRequests() {
        UserEntity currentUser = requireCurrentCustomerUser();

        return rentalRequestRepository.findByCustomerIdOrderByCreatedAtDesc(currentUser.getCustomerId())
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<RentalRequestDTO> getAllRequests() {
        return rentalRequestRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<RentalRequestDTO> getMyAssignedRequests() {
        Long currentStaffId = SecurityUtils.getCurrentUserId();
        if (currentStaffId == null) {
            throw new RuntimeException("Chua dang nhap");
        }

        return rentalRequestRepository.findByStaffIdOrderByCreatedAtDesc(currentStaffId)
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional
    public void assignStaff(Long requestId, AssignRentalRequest request) {
        if (request.getStaffId() == null) {
            throw new RuntimeException("StaffId khong duoc de trong");
        }

        RentalRequestEntity rentalRequest = rentalRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Khong tim thay yeu cau thue"));
        UserEntity staff = userRepository.findById(request.getStaffId())
                .orElseThrow(() -> new RuntimeException("Khong tim thay nhan vien"));

        boolean isStaff = staff.getRoles().stream()
                .anyMatch(role -> "staff".equalsIgnoreCase(role.getCode()));
        if (!isStaff) {
            throw new RuntimeException("Nguoi duoc phan cong phai la nhan vien");
        }

        rentalRequest.setStaff(staff);
        if ("pending".equalsIgnoreCase(rentalRequest.getStatus())) {
            rentalRequest.setStatus("processing");
        }
        rentalRequestRepository.save(rentalRequest);
    }

    @Transactional
    public void updateStatusForAssignedRequest(Long requestId, UpdateRentalRequestStatus request) {
        Long currentStaffId = SecurityUtils.getCurrentUserId();
        if (currentStaffId == null) {
            throw new RuntimeException("Chua dang nhap");
        }

        RentalRequestEntity rentalRequest = rentalRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Khong tim thay yeu cau thue"));

        if (rentalRequest.getStaff() == null || !rentalRequest.getStaff().getId().equals(currentStaffId)) {
            throw new RuntimeException("Ban khong duoc phan cong yeu cau nay");
        }

        String status = request.getStatus();
        if (!List.of("processing", "approved", "rejected").contains(status)) {
            throw new RuntimeException("Trang thai khong hop le");
        }

        rentalRequest.setStatus(status);
        rentalRequestRepository.save(rentalRequest);
    }

    private RentalRequestDTO toDto(RentalRequestEntity item) {
        RentalRequestDTO dto = new RentalRequestDTO();
        dto.setId(item.getId());
        dto.setBuildingId(item.getBuilding().getId());
        dto.setBuildingName(item.getBuilding().getName());
        dto.setCustomerId(item.getCustomer().getId());
        dto.setCustomerName(item.getCustomer().getFullname());
        if (item.getRentArea() != null) {
            dto.setRentAreaId(item.getRentArea().getId());
            dto.setRentAreaValue(item.getRentArea().getValue());
        }
        dto.setRequestedArea(item.getRequestedArea());
        dto.setStartDate(item.getStartDate());
        dto.setExpectedMonths(item.getExpectedMonths());
        dto.setNote(item.getNote());
        dto.setStatus(item.getStatus());
        if (item.getStaff() != null) {
            dto.setStaffId(item.getStaff().getId());
            dto.setStaffName(item.getStaff().getFullname());
        }
        dto.setCreatedAt(item.getCreatedAt());
        return dto;
    }

    private UserEntity requireCurrentCustomerUser() {
        var currentUser = SecurityUtils.getCurrentUser();
        if (currentUser == null) {
            throw new RuntimeException("Chua dang nhap");
        }

        UserEntity user = currentUser.getUser();
        if (user.getCustomerId() == null) {
            throw new RuntimeException("Tai khoan nay chua lien ket voi ho so khach hang");
        }
        return user;
    }
}
