package com.codeit.springbeanexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.codeit.springbeanexample.service.MyService;

/** 현재 패키지의 빈만 읽는 독립 예제입니다. README의 해당 단계와 함께 보세요. */
@SpringBootApplication
public class SpringBeanExampleApplicationV1 {
    public static void main(String[] args) {
        // Spring을 시작하고, 생성·관리된 빈을 조회할 수 있는 컨테이너를 받습니다.
        ApplicationContext context = SpringApplication.run(SpringBeanExampleApplicationV1.class, args);

        // getBean("이름")의 반환 타입은 Object이므로 MyService로 형변환합니다.
        // @Service MyService의 기본 빈 이름은 클래스 첫 글자를 소문자로 바꾼 myService입니다.
        MyService myService = (MyService) context.getBean("myService");
        myService.testMethod();

        // 같은 컨테이너에서 같은 singleton 빈을 다시 조회하면 같은 객체입니다.
        MyService sameService = (MyService) context.getBean("myService");
        System.out.println("같은 singleton 빈인가? " + (myService == sameService));
    }
}
