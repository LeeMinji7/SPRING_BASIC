//package com.example.study.repository;
//
//import com.example.study.model.entity.User;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.time.LocalDateTime;
//
//@DataJpaTest                                                                    // JPA 테스트 관련 컴포넌트만 Import
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    // 실제 db 사용
//@DisplayName("UserRepositoryTest 테스트")
//public class UserRepositoryTest{
//    // 스프링의 디자인 패턴 : 싱글톤 패턴 => UserRepository는 하나만 생성해서, AUTOwired라는 곳에서 함께 쓴다 => DI
//
//    // Dependency Injection(DI) : 의존성의 주입 => 직접 객체를 만들지 않고 객체들을 스프링이 관리, 스프링이 의존성 주입
//    @Autowired  // DI 주입 => 객체 만들지 않고 객체 찾아 주입
//    private UserRepository userRepository;
//
//    @Test // test하는 곳이라는 뜻
//    public void create(){
//        //과거 및 다른 프레임워크 create 사용법
//        // String sql = inser into user(%s, %s, %d) value (account, email, age); // 쿼리문 가지고 값 매칭
//
//        //현재 spring create 사용 => jpa로 객체 관리
//        User user = new User();
////        user.setId(); // auto-incremental이므로 지정해주지 않아도, 자동으로 넣어서 순차적으로 증가
//        user.setAccount("TestUser01");
//        user.setEmail("TestUser01@gmail.com");
//        user.setPhoneNumber("010-1111-1111");
//        user.setCreatedAt(LocalDateTime.now());
//        user.setCreatedBy("admin");
//
//        User newUser = userRepository.save(user); // user을 리턴시켜 db에 저장된 새로운 객체 튀어나옴
//        System.out.println("newUser : " + newUser);
//    }

package com.example.study.repository;


import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

//@SpringBootTest : 처음부터 끝까지 테스트 가능(springboottest 나 바로 아래 test 하나 선택하여 하시오~
@DataJpaTest // 자동으로 transaction 소환 => 미적용 원할시: @Transactional(propagation = Propagation.NOT_SUPPORTED) //  JPA 테스트 관련 컴포넌트만 Import
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    // 실제 db 사용
@DisplayName("UserRepositoryTest 테스트") // 테스트 이름
public class UserRepositoryTest {

    // Dependency Injection (DI)
    @Autowired
    private UserRepository userRepository;

    @Test
//    @Rollback(false) : 롤백 거절
    @Transactional(propagation = Propagation.NOT_SUPPORTED) // : @DataJpaTest적용시, 자동 롤백 거절
    public void create(){
        // String sql = insert into user (%s, %s , %d ) value (account, email, age);
        User user = new User();
        user.setAccount("TestUser02");
        user.setEmail("TestUser02@gmail.com");
        user.setPhoneNumber("010-1111-1111");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser2");

        User newUser = userRepository.save(user);
        System.out.println("newUser : "+newUser);

    }

    @Test
    public void read(){
        // .findAll(): db에 있는 user table을 모두 리스트로 가져옴
        // .findById() : 특정 id를 가지는 data를 1건만 가져옴

        //아래 문장 jpaQueryMethod로 치환 : select * from user where id = ?
//        Optional<User> user = userRepository.findById(2L); // 2L: Long타입, id가 2, Optional타입 리턴: 있을 수도 없을 수도 있음

        Optional<User> user = userRepository.findByAccount("TestUser02");

        user.ifPresent(selectUser->{ //ifPresent: 존재 한다면
//            System.out.println("user: " + selectUser);
//            System.out.println("email: " + selectUser.getEmail());

            selectUser.getOrderDetailList().stream().forEach(detail->{ // 주문상세리스트 가져옴
//                System.out.println(detail.getItemId()); //user_id=2사람이 item_id=1을 가지고 있음

                Item item = detail.getItem();
                System.out.println(item); // 2번 사용자가 1번 아이템 주문했음을 출력

            });
        });

    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void update(){

        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(updateUser->{
//            updateUser.setId(3L); // 위에 ID를 2라고 지정해도, 여기서 3으로 지정하면 => 3번의 DATA를 update!!
            updateUser.setAccount("pppp");
            updateUser.setUpdateAt(LocalDateTime.now());
            updateUser.setUpdateBy("update method()");

            userRepository.save(updateUser);
        });

        // jpa는 아이디 중복 여부를 확인하고, 중복이라면? => update()를 진행
        // id로 2를 select -> ifpresent의 특정유저 중복 확인 select -> update
        // jpa는 세팅한 값과 기존의 값들을 모두 업데이트
    }

    @Test
//    @Transactional // jpatest는 기본값으로 롤백 작동
//    @DeleteMapping("/api/user")
    @Transactional(propagation = Propagation.NOT_SUPPORTED) // 롤백 기본값 거절
    public void delete(){

        Optional<User> user = userRepository.findById(1L);
        Assertions.assertTrue(user.isPresent()); // 반드시 user.isPresent()가 true 이어야함. // 이 과정을 통과해야 함!! // false -> 오류
        // 데이터 삭제
        user.ifPresent(deleteUser->{
//            deleteUser.setId(3L); // ID=3번 삭제
            userRepository.delete(deleteUser); // 리턴 타입 없음 : row삭제 했으므로
        });

        Optional<User> deleteUser = userRepository.findById(1L);
       /* // 1) 삭제 확인
        if(deleteUser.isPresent()){
            System.out.println("데이터 존재: " + deleteUser.get());
        }else{
            System.out.println("데이터 삭제: 데이터 없음");
        }*/
        // 2) 삭제 확인
        assertFalse(deleteUser.isPresent()); // 반드시 false 이어야함.
    }

    // findid(2L) id select -> ifpresent의 id select -> delete -> findbyid(2L) select -> 당연히 ifpresent는 없기 때문에 안부름

}
