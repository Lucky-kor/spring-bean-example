package com.codeit.springbeanexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.codeit.springbeanexample.config.ContextConfiguration;
import com.codeit.springbeanexample.dto.MemberDto;

/** 현재 패키지의 빈만 읽는 독립 예제입니다. README의 해당 단계와 함께 보세요. */
@SpringBootApplication
public class SpringBeanExampleApplicationV2 {
    public static void main(String[] args) {
        // Spring을 시작하고, 생성·관리된 빈을 조회할 수 있는 컨테이너를 받습니다.
        ApplicationContext context = SpringApplication.run(SpringBeanExampleApplicationV2.class, args);

        // @Bean(name = "member1")이 지정한 이름으로 회원 객체를 조회합니다.
        MemberDto memberDto = (MemberDto) context.getBean("member1");
        System.out.println("회원 이름: " + memberDto.getName());

        // 설정 클래스도 빈입니다. @Configuration의 이름은 반환 객체의 이름과 다릅니다.
        ContextConfiguration config =
                (ContextConfiguration) context.getBean("configureClassExample");

        // 기본 @Configuration은 @Bean 메서드 호출을 가로채 관리 중인 singleton을 돌려줍니다.
        // 설정 클래스를 직접 new 하면 이 동작이 적용되지 않습니다.
        MemberDto sameMember = config.getMember();
        System.out.println("@Bean 메서드로 받아도 같은 객체인가? " + (memberDto == sameMember));
    }
}
