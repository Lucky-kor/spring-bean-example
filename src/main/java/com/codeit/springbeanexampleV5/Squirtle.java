package com.codeit.springbeanexampleV5;

import org.springframework.stereotype.Component;

/** Pokemon 후보 빈입니다. 클래스 이름의 첫 글자를 소문자로 한 기본 빈 이름으로 등록됩니다. */
@Component
public class Squirtle implements Pokemon {

    @Override
    public void attack() {
        System.out.println("꼬부기 물대포💧");
    }

    @Override
    public String name() {
        return "squirtle";
    }
}
