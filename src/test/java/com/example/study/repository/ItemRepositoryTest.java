package com.example.study.repository;

import com.example.study.model.entity.Item;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("ItemRepositoryTest 테스트")
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED) // 롤백 거절
    public void create(){

        Item item = new Item();
        item.setId(3L);
        item.setName("노트북");
        item.setPrice(300000);
        item.setContent("삼성 노트북");

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
