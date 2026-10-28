package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "rentalrequest")
@Getter
@Setter
public class RentalRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Column(name = "requestedarea")
    private Integer requestedArea;

    @Column(name = "startdate")
    private LocalDate startDate;

    @Column(name = "expectedmonths")
    private Integer expectedMonths;

    private String note;
    private String status;

    @Column(name = "createdat", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updatedat", insertable = false, updatable = false)
    private LocalDateTime updatedAt;
}
