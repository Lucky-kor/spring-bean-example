package com.codeit.springbeanexampleV6;

/** 같은 타입을 구현하는 여러 빈의 공통 계약입니다. 빈 이름과 name()의 반환 문자열은 별개입니다. */
public interface Pokemon {
    void attack();
    String name();
}
