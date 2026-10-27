package com.codeit.dependexampleV2.dependson;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
// CommandLineRunner의 run은 컨테이너 준비 후 호출됩니다. 생성자와는 시점이 다릅니다.
public class FirstBean implements CommandLineRunner, Ordered {
    @Override
    public void run(String... args) {
        System.out.println("FirstBean run method called");
    }

    @Override
    public int getOrder() {
        // 작은 숫자부터 run 실행. 현재 전체 순서는 First → Third → Second입니다.
        // @Order(1)로 표현하는 방법도 있지만 여기서는 기존 Ordered 방식을 읽습니다.
        return 1;
    }
}
