package com.example.study.repository;

import com.example.study.model.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{ // <객체타입, 기본키의 타입>
// 객체로 crud 가능한 인터페이스

    // select문 : select * from user where account = ?  // ? << account: test01, test02로 검색
    Optional<User> findByAccount(String account); // By뒤 Account를 보고 매개변수와 매칭

    Optional<User> findByEmail(String email);

    // select * from user where account = ? and email = ? // 2가지값 검색
    Optional<User> findByAccountAndEmail(String account, String email); // account와 email같이 검색
//    Optional<User> findByAccountAndEmail(String ac, String e); // By 뒤의 글자와 변수이름 같지 않아도 됨
}
// 정의하면 test에서 crud 구현시, 사용가능
