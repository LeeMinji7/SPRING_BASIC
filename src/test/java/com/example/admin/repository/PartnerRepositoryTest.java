package com.example.admin.repository;

import com.example.admin.compenent.LoginUserAuditorAware;
import com.example.admin.config.JpaConfig;
import com.example.admin.model.entity.Partner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("PartnerRepositoryTest 테스트")
@Import({JpaConfig.class, LoginUserAuditorAware.class})
public class PartnerRepositoryTest {

    @Autowired
    private PartnerRepository partnerRepository;

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void create(){
        Partner partner = new Partner();
        partner.setName("Partner01");
        partner.setStatus("REGISTERED");
        partner.setAddress("서울시 강남구");
        partner.setCallCenter("070-1111-2222");
        partner.setPartnerNumber("010-1111-2222");
        partner.setBusinessNumber("1234567890123");
        partner.setCeoName("홍길동");
        partner.setRegisteredAt(LocalDateTime.now());
        partner.setCreatedAt(LocalDateTime.now());
        partner.setCreatedBy("AdminServer");
//        partner.setCategoryId(1L);

        Partner newPartner = partnerRepository.save(partner);
        Assertions.assertNotNull(newPartner);
        Assertions.assertEquals(newPartner.getName(), "Partner01");

        System.out.println(newPartner);

    }

    @Test
    public void read(){

        Optional<Partner> partner = partnerRepository.findById(1L);

        Assertions.assertNotNull(partner);
        System.out.println(partner);
    }


}
