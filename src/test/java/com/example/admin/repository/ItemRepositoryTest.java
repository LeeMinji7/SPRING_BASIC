package com.example.admin.repository;

import com.example.admin.compenent.LoginUserAuditorAware;
import com.example.admin.config.JpaConfig;
import com.example.admin.model.entity.Item;
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


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("ItemRepositoryTest 테스트")
@Import({JpaConfig.class, LoginUserAuditorAware.class})
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED) // 롤백 거절
    public void create(){

        Item item = new Item();
        item.setStatus("UNREGISTERED");
        item.setName("노트북");
        item.setTitle("삼성 노트북 A100");
        item.setContent("2019년형 노트북 입니다.");
        item.setPrice(900000);
        item.setBrandName("삼성");
        item.setRegisteredAt(LocalDateTime.now());
        item.setCreatedAt(LocalDateTime.now());
        item.setCreatedBy("Partner01");
//        item.setPartnerId(1L); // Long -> Partner

        Item newItem = itemRepository.save(item);
        Assertions.assertNotNull(newItem); // newItem이 notnull일 경우 통과!! => 저장이 잘 됨.

        System.out.println("item: " + item);

    }

    @Test
    public void read(){

        Long id = 1L;

        Optional<Item> item = itemRepository.findById(id);

        Assertions.assertTrue(item.isPresent()); // 아래 문장의 대체문장

//        item.ifPresent(i->{
//            System.out.println(i);
//        });
    }
}
