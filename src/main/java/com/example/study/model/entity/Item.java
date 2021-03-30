package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.criterion.Order;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer price;
    private String content;


    // ## fetch타입 종류 ##
    /* 1.LAZY = 지연로딩  => select * from item where id = ?
     - 우리가 선택한 id에 대해서만 찾음
     - 변수에 대해 메소드를 호출하지 않은 이상, 연관관계가 설정된 테이블에 대해 SELECT를 하지 않음*/

     /*2.EAGER = 즉시로딩
     => item_id = order_detail.item_id
     => user_id = order_detail.user_id
     => where item.id = ?
     ~ from item item0_ left outer join order_detail orderdetai1_ on item0_.id=orderdetai1_.item_id left outer join user user2_ on orderdetai1_.user_id=user2_.id where item0_.id=?

     - 연관관계가 설정된 모든 테이블에 대해 JOIN 일어남 => 모든 정보 도출
     - 단점: 성능 저하*/

    // => fetch 연관관계 설정 추천: LAZY  // EAGER는 1:1 또는 연관관계에 있어 1건만 존재할 때 사용(N:1)
    // (Item 입장에서) 1:N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<OrderDetail> orderDetailList;
}
