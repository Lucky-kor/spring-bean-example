package com.codeit.springbeanexampleV7;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor // SessionScopedBean을 받는 생성자를 Lombok이 만듭니다.
public class MemberService {
    // 이 서비스는 singleton입니다. 더 짧은 범위의 세션 빈은 프록시를 통해 주입받습니다.
    // main에서 서비스가 생성되는 시점에는 HTTP 요청/세션이 아직 없습니다.
    private final SessionScopedBean sessionScopedBean;
}
