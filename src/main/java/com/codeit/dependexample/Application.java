package com.codeit.dependexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


/** 현재 패키지의 빈만 읽는 독립 예제입니다. README의 해당 단계와 함께 보세요. */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // Spring을 시작하고, 생성·관리된 빈을 조회할 수 있는 컨테이너를 받습니다.
        ApplicationContext context = SpringApplication.run(Application.class, args);

        // 의존 순서 또는 Runner 실행 순서는 각 빈 클래스에 지정되어 있습니다.
        // 이 main에서 빈을 차례대로 조회해서 순서를 만드는 것이 아닙니다.
        System.out.println("위의 빈 생성/실행 로그를 확인하세요.");
    }
}
