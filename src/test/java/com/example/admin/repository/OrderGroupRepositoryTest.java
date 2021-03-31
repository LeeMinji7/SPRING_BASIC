package com.example.admin.repository;

import com.example.admin.compenent.LoginUserAuditorAware;
import com.example.admin.config.JpaConfig;
import com.example.admin.model.entity.OrderGroup;
import com.example.admin.model.entity.Partner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("PartnerRepositoryTest 테스트")
@Import({JpaConfig.class, LoginUserAuditorAware.class})
public class OrderGroupRepositoryTest {

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void create(){
        OrderGroup orderGroup = new OrderGroup();
        orderGroup.setStatus("COMPLETE");
        orderGroup.setOrderType("ALL");
        orderGroup.setRevAddress("서울시 강남구");
        orderGroup.setRevName("홍길동");
        orderGroup.setPaymentType("CARD");
        orderGroup.setTotalPrice(BigDecimal.valueOf(900000));
        orderGroup.setTotalQuantity(1);
        orderGroup.setOrderAt(LocalDateTime.now().minusDays(2)); // LocalDateTime.now().minusDays(2): 현재기준으로 이틀전
        orderGroup.setArrivalDate(LocalDateTime.now());
        orderGroup.setCreatedAt(LocalDateTime.now());
        orderGroup.setCreatedBy("AdminServer");
//        orderGroup.setUserId(1L);

        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);
        Assertions.assertNotNull(newOrderGroup);

        System.out.println(newOrderGroup);

    }

    @Test
    public void read(){

        Optional<OrderGroup> orderGroup = orderGroupRepository.findById(1L);

        Assertions.assertNotNull(orderGroup);
        System.out.println(orderGroup);
    }


}
