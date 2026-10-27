package com.codeit.dependexample.dependson;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/** @DependsOn으로 secondBean 초기화 완료 후 이 빈을 생성합니다. 객체 참조 주입과는 다릅니다. */
@Component
// 주입할 필드는 없어도 secondBean의 초기화가 먼저 끝나야 한다는 관계입니다.
@DependsOn("secondBean")
public class FirstBean {
    public FirstBean() {
        System.out.println("FirstBean constructor called");
    }
}
