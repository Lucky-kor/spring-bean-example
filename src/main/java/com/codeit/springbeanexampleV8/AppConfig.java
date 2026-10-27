package com.codeit.springbeanexampleV8;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** 클래스를 수정할 수 없을 때도 @Bean의 initMethod/destroyMethod로 생명주기 메서드를 지정할 수 있습니다. */
@Configuration
public class AppConfig {

    @Bean(initMethod = "init", destroyMethod = "cleanup")
    public TestBean testBean() {
        return new TestBean();
    }
}
