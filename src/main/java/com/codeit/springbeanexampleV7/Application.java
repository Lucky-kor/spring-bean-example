package com.codeit.springbeanexampleV7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


/** 현재 패키지의 빈만 읽는 독립 예제입니다. README의 해당 단계와 함께 보세요. */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // Spring을 시작하고, 생성·관리된 빈을 조회할 수 있는 컨테이너를 받습니다.
        ApplicationContext context = SpringApplication.run(Application.class, args);

        // singleton 서비스가 생성되고 세션 빈의 프록시를 주입받을 수 있는지 확인합니다.
        MemberService memberService = (MemberService) context.getBean("memberService");
        System.out.println(memberService);

        // 이 출력만으로 세션마다 대상 객체가 바뀌는지까지 확인할 수는 없습니다.
        // HTTP 요청과 세션 동작은 웹을 배운 뒤 다룹니다. 지금은 프록시가 필요한 이유만 보세요.
    }
}
