package com.example.admin.repository;

import com.example.admin.compenent.LoginUserAuditorAware;
import com.example.admin.config.JpaConfig;
import com.example.admin.model.entity.OrderDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("OrderDetailRepository 테스트")
@Import({JpaConfig.class, LoginUserAuditorAware.class})
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void create(){

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setStatus("WAITING");
        orderDetail.setArrivalDate(LocalDateTime.now().plusDays(2)); // LocalDateTime.now().plusDays(2): 현재 일자로 부터 2일 뒤
        orderDetail.setQuantity(1);
        orderDetail.setTotalPrice(BigDecimal.valueOf(900000));
        orderDetail.setCreatedAt(LocalDateTime.now());
        orderDetail.setCreatedBy("AdminServer");

        // 어떠한 장바구니?
//        orderDetail.setOrderGroupId(1L); // Long -> OrderGroup

        // 어떤 상품?
//        orderDetail.setItemId(1L); // ItemId가 1번인 상품

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
        Assertions.assertNotNull(newOrderDetail);

    }

}
