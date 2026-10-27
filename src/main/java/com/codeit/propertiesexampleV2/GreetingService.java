package com.codeit.propertiesexampleV2;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/** Environment는 키로 동적 조회합니다. 누락 시 null이 될 수 있어 필수값은 타입 바인딩+검증이 유리합니다. */
@Service
@AllArgsConstructor
public class GreetingService {
    private final Environment environment;

    public void printPort() {
        String port = environment.getProperty("my.service.port");
        int num = environment.getProperty("my.servers[1].port", Integer.class);
        System.out.println(port);
        System.out.println(num);
    }
}
