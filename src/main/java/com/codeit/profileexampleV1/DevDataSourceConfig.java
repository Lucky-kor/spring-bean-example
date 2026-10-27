package com.codeit.profileexampleV1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/** 환경에 따라 빈 등록 자체를 선택합니다. 실제 DataSource가 아닌 출력용 람다입니다. */
@Configuration
// 배열의 의미는 OR입니다. prod와 함께 켜면 동일 이름 빈 충돌을 실습할 수 있습니다.
@Profile({"local", "dev", "v2"})
public class DevDataSourceConfig {

    @Bean
    public DataSourceInfoPrinter dataSourceInfoPrinter() {
        return () ->
                System.out.println("개발용 데이터베이스");
    }
}
