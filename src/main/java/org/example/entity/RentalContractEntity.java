package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "rentalcontract")
@Getter
@Setter
public class RentalContractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "requestid")
    private RentalRequestEntity request;

    @ManyToOne
    @JoinColumn(name = "customerid")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "buildingid")
    private BuildingEntity building;

    @ManyToOne
    @JoinColumn(name = "rentareaid")
    private RentAreaEntity rentArea;

    @ManyToOne
    @JoinColumn(name = "staffid")
    private UserEntity staff;

    @Column(name = "contractcode")
    private String contractCode;

    @Column(name = "startdate")
    private LocalDate startDate;

    @Column(name = "enddate")
    private LocalDate endDate;

    @Column(name = "monthlyrent")
    private BigDecimal monthlyRent;

    @Column(name = "depositamount")
    private BigDecimal depositAmount;

    @Column(name = "paymentcycle")
    private String paymentCycle;

    private String status;

    @Column(name = "signeddate")
    private LocalDate signedDate;

    private String note;

    @Column(name = "createdat", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updatedat", insertable = false, updatable = false)
    private LocalDateTime updatedAt;
}
