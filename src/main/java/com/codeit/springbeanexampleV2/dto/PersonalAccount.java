package com.codeit.springbeanexampleV2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Lombok이 접근자와 생성자를 만듭니다. 이 객체는 @Bean 메서드에서 생성하며 컴포넌트 스캔 대상은 아닙니다. */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonalAccount implements Account {
    private String accNo;
    private int balance;
    private String bankCode;
}
