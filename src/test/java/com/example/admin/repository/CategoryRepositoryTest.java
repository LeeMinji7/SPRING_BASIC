package com.example.admin.repository;

import com.example.admin.compenent.LoginUserAuditorAware;
import com.example.admin.config.JpaConfig;
import com.example.admin.model.entity.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("CategoryRepositoryTest 테스트")
@Import({JpaConfig.class, LoginUserAuditorAware.class})
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void create(){
        String type = "COMPUTER";
        String title = "컴퓨터";
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        Category category = new Category();
        category.setType(type);
        category.setTitle(title);
        category.setCreatedAt(createdAt);
        category.setCreatedBy(createdBy);

        Category newCategory = categoryRepository.save(category);

        Assertions.assertNotNull(newCategory);
        Assertions.assertEquals(newCategory.getType(), type);
        Assertions.assertEquals(newCategory.getTitle(), title);

    }

    @Test
    public void read(){

//        Optional<Category> category = categoryRepository.findById(1L);

        String type = "COMPUTER";
        // select * from category where type = 'COMPUTER'
        Optional<Category> category = categoryRepository.findByType(type);

        category.ifPresent(c->{

            Assertions.assertEquals(c.getType(), type); // c의 타입과 우리의 타입이 동일해야 함

            System.out.println(c.getId());
            System.out.println(c.getType());
            System.out.println(c.getTitle());
        });

    }
}
