package com.example.admin.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class) //해당 EntityListeners는 AuditingEntityListener 감시자 사용
                                            // CreatedBy, LastModifiedBy가 자동적으로 LoginUserAuditorAware의 메소드에서 리턴하는 값으로 매칭
@Builder // 빌더 패턴 적용 가능 => 매개변수 생성자의 매개변수의 선택이 가능해짐
@Accessors(chain = true) // 체인 패턴 적용 가능 => 매개변수 생성자의 매개변수의 선택이 가능해짐
public class AdminUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String account;
    private String password;
    private String status;
    private String role;
    private LocalDateTime lastLoginAt;
    private LocalDateTime passwordUpdatedAt;
    private int loginFailCount;
    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;

    /* 모든 entity에서 사용하는 아래 변수들을 자동호출 하는 방법
     = 객체가 생성되거나 db에 들어갈 때, 자동으로 jpa에서 이부분을 설정
     = 생성일자, 수정일자, 생성자, 수정자 컬럼에 값을 자동으로 넣어주는 기능
     => LoginUserAuditorAware 적용으로 자동 설정 -> 인위적으로 지정하지 않아도 자동적으로 생성!! */

    @CreatedDate // 최초 객체 생성시에 자동으로 생성됨
    private LocalDateTime createdAt;

    @CreatedBy // By라고 쓰여진 것은 LoginUserAuditorAware의 메소드에서 리턴하는 AdminServer를 반영
                // 최초 객체 생성시에 자동으로 생성됨
    private String createdBy;

    @LastModifiedDate // 객체 수정되면 자동적으로 값이 생성됨
    private LocalDateTime updatedAt;

    @LastModifiedBy // By라고 쓰여진 것은 LoginUserAuditorAware의 메소드에서 리턴하는 AdminServer를 반영
                    // 객체 수정되면 자동적으로 값이 생성됨
    private String updatedBy;
}
