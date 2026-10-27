package com.codeit.springbeanexampleV2.config;

import com.codeit.springbeanexampleV2.dto.Account;
import com.codeit.springbeanexampleV2.dto.MemberDto;
import com.codeit.springbeanexampleV2.dto.PersonalAccount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** @Bean의 인자로 필요한 빈을 받습니다. 프록시 메서드 호출에 기대지 않고 의존 관계를 표현할 수 있습니다. */
@Configuration
public class ContextConfiguration {
    @Bean
    public Account accountGenerator() {
        return new PersonalAccount("No1",20, "1202-293-284854");
    }

    @Bean
    public MemberDto getMember(Account account) {
        return new MemberDto(1L,
                "010-1234-1234",
                "catcat@meow.com",
                "이정민",
                account); // 메서드 인자로 컨테이너가 주입한 빈을 사용합니다.
    }
}
