package com.codeit.springbeanexample.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** 수동 등록용 일반 Java 객체입니다. DTO를 singleton 빈으로 등록하는 것은 원리 실습이며 요청별 DTO 설계의 권장은 아닙니다. */
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class MemberDto {
    private Long id;
    private String phone;
    private String email;
    private String name;
}
