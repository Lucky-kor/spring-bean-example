package com.codeit.propertiesExampleV3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    // 기존 필드 주입 예제입니다. secret YAML의 my.password는 PASSWORD 환경변수를 읽습니다.
    // 키나 환경변수가 없으면 값을 주입할 수 없어 시작이 실패합니다.
    @Value("${my.password}")
    private String value;

    public void printValue() {
        // 외부 입력 여부만 관찰합니다. 비밀번호 원문은 출력하지 않습니다.
        System.out.println("외부 비밀번호가 비어 있지 않음: " + !value.isBlank());
    }
}
