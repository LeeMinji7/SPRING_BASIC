package com.example.admin.compenent;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginUserAuditorAware implements AuditorAware<String> { // login한 사용자 감시하는 역할

    @Override
    public Optional<String> getCurrentAuditor() { // 현재 감시자가 누구인지 리턴
        return Optional.of("AdminServer"); // 자동으로 AdminServer라는 이름 전달
    }
}
