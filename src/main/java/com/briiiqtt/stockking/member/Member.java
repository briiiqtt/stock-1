package com.briiiqtt.stockking.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(
        name = "member", // 지정 안해도 기본적으로 클래스 이름을 따라감
        uniqueConstraints = @UniqueConstraint(columnNames = "email")
)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String email;
    private String name;
    private String hashedPassword;
}
