package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "rentalpayment")
@Getter
@Setter
public class RentalPaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contractid")
    private RentalContractEntity contract;

    @Column(name = "paymenttype")
    private String paymentType;

    private BigDecimal amount;

    @Column(name = "duedate")
    private LocalDate dueDate;

    @Column(name = "paiddate")
    private LocalDate paidDate;

    private String method;
    private String status;
    private String note;

    @Column(name = "createdat", insertable = false, updatable = false)
    private LocalDateTime createdAt;
}
