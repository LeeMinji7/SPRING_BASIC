package com.example.admin.repository;

import com.example.admin.compenent.LoginUserAuditorAware;
import com.example.admin.config.JpaConfig;
import com.example.admin.model.entity.AdminUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("UserRepositoryTest 테스트")
@Import({JpaConfig.class, LoginUserAuditorAware.class}) // jpa테스트시, 따로 감시를 활성화 해야함.
public class AdminUserRepositoryTest {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void create(){

        AdminUser adminUser = new AdminUser();
        adminUser.setAccount("AdminUser04");
        adminUser.setPassword("AdminUser04");
        adminUser.setStatus("REGISTERED");
        adminUser.setRole("PARTNER");
//        adminUser.setCreatedAt(LocalDateTime.now()); // LoginUserAuditorAware 적용으로 자동 createdAt, createdBy 설정
//        adminUser.setCreatedBy("AdminServer"); // LoginUserAuditorAware 적용으로 자동 createdAt, createdBy 설정

        AdminUser newAdminUser = adminUserRepository.save(adminUser);

        Assertions.assertNotNull(newAdminUser);
        System.out.println("newUser : "+newAdminUser);

        newAdminUser.setAccount("CHANGE");
        adminUserRepository.save(newAdminUser);

    }

    @Test
    public void read(){

        Optional<AdminUser> adminUser = adminUserRepository.findById(1L);

        Assertions.assertNotNull(adminUser); // 없으면 에러

        adminUser.ifPresent(selectUser->{ //ifPresent: 존재 한다면
            System.out.println("user: " + selectUser);
        });

    }
}
