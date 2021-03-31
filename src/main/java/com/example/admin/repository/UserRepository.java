package com.example.admin.repository;

import com.example.admin.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{ // <객체타입, 기본키의 타입>
// 객체로 crud 가능한 인터페이스

   User findFirstByPhoneNumberOrderByIdDesc(String phoneNumber);
    // findFirstBy: 같은 번호로 여러번 가입할 수 도 있으므로, 한건에 대해 가장 최근 것 반환
    // findFirstByByPhoneNumberOOrderByIdDesc: 가장 최근 번호 매칭 + id를 기준으로 역순
}
// 정의하면 test에서 crud 구현시, 사용가능
