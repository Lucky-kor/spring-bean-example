package com.codeit.springbeanexampleV2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Account 참조를 가진 일반 객체입니다. @Bean 메서드가 이미 등록된 Account를 생성자에 전달합니다. */
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class MemberDto {
    private Long id;
    private String phone;
    private String email;
    private String name;
    private Account personalAccount;
}
