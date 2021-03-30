package com.example.study.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // order_detail
@ToString(exclude = {"user", "item"}) // user와 item을 toString하는 것을 제외 ->왜? => 서로 상호참조하다보니 오버플로우 발생
                                    // 연관관계 설정에 대한 변수는 제외해야 함 -> toSting의 상호참조 해제
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime orderAt;

    // (OrderDetail 입장) N:1 = OrderDetail : User
    @ManyToOne
//    private Long userId; => hibernate를 통한 연관관계 설정을 위해 아래와 같이 변경
    private User user; // hibernate가 알아서 user를 찾아감

    // N:1 = OrderDetail : Item
    @ManyToOne
//    private Long itemId;
    private Item item;
}
