package com.codeit.propertiesexampleV2;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/** JavaBean setter 바인딩입니다. @Component가 빈 등록, @ConfigurationProperties가 설정 묶음 바인딩을 담당합니다. */
@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "my.service")
public class MyServicePropertiesV2 {
    private String name;
    private int port;
    private boolean enabled;
}
