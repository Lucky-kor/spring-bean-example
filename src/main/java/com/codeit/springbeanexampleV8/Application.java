package com.codeit.springbeanexampleV8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


/** 현재 패키지의 빈만 읽는 독립 예제입니다. README의 해당 단계와 함께 보세요. */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // Spring을 시작하고, 생성·관리된 빈을 조회할 수 있는 컨테이너를 받습니다.
        ApplicationContext context = SpringApplication.run(Application.class, args);

        // 시작 로그에서 각 방식의 초기화 메시지를 찾습니다.
        // 정상 종료(Ctrl+C)하면 소멸 메시지가 출력됩니다. 강제 종료는 정리를 보장하지 않습니다.
        System.out.println("초기화 완료. 종료할 때 소멸 콜백을 확인하세요.");
    }
}
