package com.capstone.registration;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "student_id", length = 20)
    private String studentId;

    @Version
    private Long version;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(name = "max_credit", nullable = false)
    private int maxCredit = 21;

    @Column(name = "available_credit", nullable = false)
    private int availableCredit = 21;

    @Builder
    public Student(String studentId, String password, String name, int maxCredit) {
        this.studentId = studentId;
        this.password = password;
        this.name = name;
        this.maxCredit = maxCredit;
        this.availableCredit = maxCredit;
    }

    public void deductCredit(int credit) {
        if (this.availableCredit < credit) {
            throw new IllegalArgumentException("잔여 학점이 부족합니다. (현재: " + availableCredit + ")");
        }
        this.availableCredit -= credit;
    }

    public void refundCredit(int credit) {
        if (this.availableCredit + credit > this.maxCredit) {
            this.availableCredit = this.maxCredit;
        } else {
            this.availableCredit += credit;
        }
    }
}
