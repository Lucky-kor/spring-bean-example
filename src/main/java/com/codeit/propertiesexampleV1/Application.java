package com.codeit.propertiesexampleV1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


/** 현재 패키지의 빈만 읽는 독립 예제입니다. README의 해당 단계와 함께 보세요. */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // Spring을 시작하고, 생성·관리된 빈을 조회할 수 있는 컨테이너를 받습니다.
        ApplicationContext context = SpringApplication.run(Application.class, args);

        GreetingService greetingService = (GreetingService) context.getBean("greetingService");
        // 한 줄씩 실행 결과를 @Value 표현식과 연결해 보세요.
        greetingService.printGreeting(); // ${my.greeting}: YAML 값
        greetingService.printMessage(); // #{...}: 계산 결과 (서버 설정 변경 아님)
        greetingService.printUsername(); // 값 누락 → anonymous 기본값
        greetingService.printServers(); // 쉼표로 구분된 문자열을 List로 변환

    }
}
