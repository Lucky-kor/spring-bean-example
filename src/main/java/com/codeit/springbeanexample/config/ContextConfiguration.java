package com.codeit.springbeanexample.config;

import com.codeit.springbeanexample.dto.MemberDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** @Configuration은 설정 클래스 빈의 이름, @Bean은 반환 객체의 이름을 지정합니다. 둘은 별개입니다. */
@Configuration("configureClassExample")
public class ContextConfiguration {
    @Bean(name = "member1")
    public MemberDto getMember() {
        return new MemberDto(1L, "010-1234-1234", "catcat@meow.com", "이정민");
    }
}
