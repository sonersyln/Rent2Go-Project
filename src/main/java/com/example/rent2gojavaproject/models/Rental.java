package com.example.rent2gojavaproject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "rentals")
@Data
@AllArgsConstructor
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "return_date", columnDefinition = "DATE DEFAULT NULL")
    private LocalDate returnDate;

    @Column(name = "start_kilometer", nullable = false)
    private int startKilometer;

    @Column(name = "end_kilometer", columnDefinition = "INTEGER DEFAULT NULL")
    private Integer endKilometer;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "is_active", nullable = false, columnDefinition = "integer default 1")
    private int isActive;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "integer default 0")
    private int isDeleted;

    @Column(name = "created_at" , nullable = false)
    @CreationTimestamp
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "discount_id", nullable = false, columnDefinition = "BIGINT DEFAULT 0")
    private Discount discount;

    public Rental() {
        this.endKilometer = null;
        this.returnDate = null;
        this.discount = new Discount(1,"DEFAULT",0);
    }

}
