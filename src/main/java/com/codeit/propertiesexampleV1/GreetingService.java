package com.codeit.propertiesexampleV1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/** 소수의 값을 @Value로 읽습니다. 값 조회, 기본값, SpEL, 쉼표 목록 변환을 각각 관찰하세요. */
@Component
public class GreetingService {
    // ${...}는 외부 설정 조회입니다. Spring이 객체 생성 후 필드에 값을 넣습니다.
    @Value("${my.greeting}")
    private String greeting;

    // #{...}는 SpEL 계산입니다. 실제 서버 포트를 바꾸지는 않습니다.
    @Value("계산한 값 (실제 서버 포트 아님): #{${server.port} * 2}")
    private String message;

    // 키가 없으면 콜론 뒤 기본값을 사용합니다. 빈 문자열과 누락은 다릅니다.
    @Value("${my.username:anonymous}")
    private String username;

    @Value("#{'${my.server-names}'.split(',')}")
    private List<String> servers;

    public void printGreeting() {
        System.out.println(greeting);
    }

    public void printMessage() {
        System.out.println(message);
    }

    public void printUsername() {
        System.out.println(username);
    }

    public void printServers() {
        System.out.println(servers);
    }
}
