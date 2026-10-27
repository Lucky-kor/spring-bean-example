package com.codeit.scopeexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


/** 현재 패키지의 빈만 읽는 독립 예제입니다. README의 해당 단계와 함께 보세요. */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // Spring을 시작하고, 생성·관리된 빈을 조회할 수 있는 컨테이너를 받습니다.
        ApplicationContext context = SpringApplication.run(Application.class, args);

        // 기존 singleton 조회 방식과 똑같이 두 번 조회해 참조만 비교합니다.
        SingletonBean firstSingleton = context.getBean(SingletonBean.class);
        SingletonBean secondSingleton = context.getBean(SingletonBean.class);
        System.out.println("singleton은 같은 객체인가? " + (firstSingleton == secondSingleton));

        // @Scope("prototype")을 붙인 클래스는 조회마다 새 객체입니다.
        PrototypeBean firstPrototype = context.getBean(PrototypeBean.class);
        PrototypeBean secondPrototype = context.getBean(PrototypeBean.class);
        System.out.println("prototype은 같은 객체인가? " + (firstPrototype == secondPrototype));
    }
}
