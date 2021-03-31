package com.example.admin.repository;

import com.example.admin.compenent.LoginUserAuditorAware;
import com.example.admin.config.JpaConfig;
import com.example.admin.model.entity.OrderGroup;
import com.example.admin.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("UserRepositoryTest 테스트")
@Import({JpaConfig.class, LoginUserAuditorAware.class})
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void create(){
        String account = "TestUser03";
        String password = "TestUser03";
        String status = "REGISTERED";
        String email = "TestUser03@gmail.com";
        String phoneNumber = "010-3333-3333";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "TestUser2";

//        User user = new User(account, password, status, email, phoneNumber, registeredAt, createdAt); // builder 미사용시, 오류
//        User user = new User(account, password, status); // builder 미사용시, 오류
        // ## 매개변수 생성자의 매개변수를 선택적으로 추가하는 법 ##
        /* 불편한 점
          1. lombok의 기본: 모든 생성자 다 넣어야 함, 클래스에 선언된 멤버변수의 순서와 같아야 함
          2. 중간에 변수 추가시 -> 매개변수에도 자리를 찾아 넣어야 함 => 불편!!!
          3. 3가지만 사용한다면 -> 3가지를 가지는 메소드 추가해야함 => 불편!! (Lombok을 쓰는 이유가 없어짐) */
        /* 해결법 : @Builder => 클래스에 builder패턴 적용
                            => 매개변수 생성자의 매개변수를 선택적으로 추가 가능!!! */

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(LocalDateTime.now());
//        user.setCreatedAt(LocalDateTime.now());
//        user.setCreatedBy(createdBy);

        // < Builder 패턴 > => account, password, status, email만 들어간 user 생성
        //                 => 보통 객체 생성시에 이용
//        User u = User.builder().account(account).password(password).status(status).email(email).build();

        User newUser = userRepository.save(user);

        Assertions.assertNotNull(newUser);
        System.out.println("newUser : "+newUser);

    }

    @Test
    public void read(){

        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-1111");

       /* chain 패턴: Accessors(chain = true): 객체에 .연산자로 chaining 해서 값 변경 가능
                    => 보통 객체 생성 후에, update할 때 사용 */
//        user.setEmail("").setPhoneNumber("").setStatus("");

        // chain 패턴 => account와 email만 가진 객체 생성 가능 + 객체 생성시에도 사용 가능
//        User u = new User().setAccount("").setEmail("");

        Assertions.assertNotNull(user); // 없으면 에러

        user.getOrderGroupList().stream().forEach(orderGroup-> {

            System.out.println("--------------------주문묶음-------------------");
            System.out.println("수령인: " + orderGroup.getRevName());
            System.out.println("수령지: " + orderGroup.getRevAddress());
            System.out.println("총 금액: " + orderGroup.getTotalPrice());
            System.out.println("총 수량: " + orderGroup.getTotalQuantity());

            System.out.println("--------------------주문상세-------------------");
            orderGroup.getOrderDetailList().forEach(orderDetail -> {
                System.out.println("파트너사 이름:" + orderDetail.getItem().getPartner().getName());
                System.out.println("파트너사 카테고리: " + orderDetail.getItem().getPartner().getCategory().getTitle());
                System.out.println("주문 상품: " +  orderDetail.getItem().getName());
                System.out.println("고객센터 번호: " + orderDetail.getItem().getPartner().getCallCenter());
                System.out.println("주문 브랜드: " + orderDetail.getItem().getBrandName());
                System.out.println("주문의 상태: " + orderDetail.getStatus());
                System.out.println("도착예정일자: " + orderDetail.getArrivalDate());

            });
        });

//        user.ifPresent(selectUser->{ //ifPresent: 존재 한다면
//            System.out.println("user: " + selectUser);
//        });

    }
}
