package com.codeit.propertiesexampleV2;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/** V2에 @Validated를 더했습니다. 잘못된 설정을 애플리케이션 시작 시 거절합니다. */
@Component
@ConfigurationProperties(prefix = "my.service")
@Getter
@Setter
@Validated
public class MyServicePropertiesV3 {
    @NotBlank
    private String name;
    // 빈에 설정값을 넣는 시점에 검사합니다. 잘못된 포트이면 main 출력 전 시작이 실패합니다.
    @Max(65535)
    @Min(1)
    private int port;
    private boolean enabled;
}
