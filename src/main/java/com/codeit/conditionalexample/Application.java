package com.codeit.conditionalexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


/** 현재 패키지의 빈만 읽는 독립 예제입니다. README의 해당 단계와 함께 보세요. */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // Spring을 시작하고, 생성·관리된 빈을 조회할 수 있는 컨테이너를 받습니다.
        ApplicationContext context = SpringApplication.run(Application.class, args);

        // false일 때는 빈이 아예 등록되지 않습니다. 조회하기 전에 유무를 확인합니다.
        // getBean을 무조건 호출하면 기능을 껐을 때 빈을 찾을 수 없다는 예외가 납니다.
        if (context.containsBean("featureService")) {
            FeatureService featureService = context.getBean(FeatureService.class);
            System.out.println(featureService.getMessage());
        } else {
            System.out.println("Feature X 비활성화: featureService 빈이 없습니다.");
        }
    }
}
