package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // 이 클래스는 entitiy임을 명시 == table
//@Table(name="user") // 클래스이름과 테이블이름 동일 => 자동으로 매핑됨, 선언 안해도됨
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본기를 어떻게 관리할 건지 전략 설정: 기본키 생성을 db에 위임
                                                        // 이 전략 옵션이 mysql 옵션
    private Long id;

//    @Column(name="account") // 컬럼이름과 해당변수이름 동일 => 자동으로 매핑됨, 선언 안해도됨
    private String account;

    private String email;

    private String phoneNumber; // jpa가 db의 스네이크케이스와 자동으로 매핑

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updateAt;

    private String updateBy;

    // (User의 입장) 1: N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user") // mappedBy의 설정값은 OrderDetails에서 User의 객체 이름과 동일해야함
    private List<OrderDetail> orderDetailList; // 여러개 받아오므로 리스트 설정정
}
