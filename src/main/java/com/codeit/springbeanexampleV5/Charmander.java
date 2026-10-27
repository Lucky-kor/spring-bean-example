package com.codeit.springbeanexampleV5;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/** Pokemon 후보 빈입니다. Primary는 단일 타입 주입의 기본 후보입니다. 명시적 Qualifier가 있으면 그 조건을 먼저 봅니다. */
@Component
@Primary
public class Charmander implements Pokemon{

    @Override
    public void attack() {
        System.out.println("Charmander 불꽃 방사!");
    }

    @Override
    public String name() {
        return "파이리";
    }
}
