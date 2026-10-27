package com.codeit.springbeanexampleV6;

import org.springframework.stereotype.Component;

// List<Pokemon> 주입 시 작은 @Order 값부터 정렬합니다. 생성 순서 지정은 아닙니다.
@org.springframework.core.annotation.Order(1)
/** Pokemon 후보 빈입니다. 클래스 이름의 첫 글자를 소문자로 한 기본 빈 이름으로 등록됩니다. */
@Component
public class Pikachu implements Pokemon {

    @Override
    public void attack() {
        System.out.println("피카츄 백만볼트⚡");
    }

    @Override
    public String name() {
        return "pikachu";
    }
}