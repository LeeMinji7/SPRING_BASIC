package com.example.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration // 설정파일에 대한 것이 들어감을 의미
@EnableJpaAuditing // Jpa에 대해 감시를 활성화
public class JpaConfig { // 감시자 설정
}
