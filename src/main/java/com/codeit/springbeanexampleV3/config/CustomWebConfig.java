package com.codeit.springbeanexampleV3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

@Configuration
public class CustomWebConfig {
    // 외부 라이브러리 객체는 소스에 @Component를 붙일 수 없으므로 @Bean으로 등록합니다.
    // Boot 4는 Jackson 3(tools.jackson 패키지)을 사용합니다. 이전 프로젝트와 import가 다릅니다.
    @Bean
    public JsonMapper objectMapper() {
        // JsonMapper 라이브러리의 객체 생성 문법입니다. 지금은 반환 객체가 빈이 되는 점에 집중합니다.
        return JsonMapper.builder()
                .enable(SerializationFeature.INDENT_OUTPUT) // JSON 출력 들여쓰기 옵션
                .build();
    }
    // 직접 JsonMapper 빈을 제공하면 Boot의 해당 기본 빈 생성이 물러날 수 있습니다.
    // 직접 만든 객체에 spring.jackson.* YAML 옵션이 모두 자동 적용된다고 가정하지 마세요.
}
