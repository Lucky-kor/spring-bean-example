package com.codeit.springbeanexampleV3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

/** 현재 패키지의 빈만 읽는 독립 예제입니다. README의 해당 단계와 함께 보세요. */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // Spring을 시작하고, 생성·관리된 빈을 조회할 수 있는 컨테이너를 받습니다.
        ApplicationContext context = SpringApplication.run(Application.class, args);

        // @Bean 메서드 이름 objectMapper로 등록한 외부 라이브러리 객체를 조회합니다.
        JsonMapper mapper = context.getBean(JsonMapper.class);
        System.out.println("들여쓰기 설정이 켜져 있는가? "
                + mapper.isEnabled(SerializationFeature.INDENT_OUTPUT));
    }
}
