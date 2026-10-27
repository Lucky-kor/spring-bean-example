package com.codeit.propertiesexampleV2;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/** 같은 my.service 설정을 @Value 필드 세 개로 읽는 비교 기준입니다. */
@Component
@Getter
public class MyServiceProperties {
    @Value("${my.service.name}")
    private String name;

    @Value("${my.service.port}")
    private int port;

    @Value("${my.service.enabled}")
    private boolean enabled;
}
