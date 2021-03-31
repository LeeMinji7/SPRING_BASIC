package com.example.admin.model.network;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class) // json형태일때, 해당 클래스의 변수들을 모두 snakecase로 만듦
//@JsonInclude(~) // 어떠한 값들만 include 시킬건지 설정
public class Header<T> { // 항상 같은 값이 들어감

    // api 통신시간
//    @JsonProperty("transaction_time") // json형태로 만들어질때, 해당이름(snake case)으로 만들어짐
                                    // => but, 일일히 다 하기 힘듦 -> application.yaml으로 전체 적용시킴
    private LocalDateTime transactionTime;

    // api 응답 코드
    private String resultCode;

    // api 부가 설명명
    private String description;

    // 매번 바뀌는 data부분 => 제네릭 이용
    private T data;

    // OK : 정상
    public static <T> Header<T> OK(){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .build();
    }

    // DATA OK : 데이터가 있을 때, 데이터 가지며 정상
    public static <T> Header<T> OK(T data){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .build();
    }

    // ERROR : 비정상
    public static <T> Header<T> ERROR(String description){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("ERROR")
                .description(description) // 어떠한 에러때문에 안돼는지 설명
                .build();
    }


}
