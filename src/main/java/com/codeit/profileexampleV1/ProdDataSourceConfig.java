package com.codeit.profileexampleV1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/** prod에서만 등록되는 모의 구현입니다. 데이터베이스에 접속하지 않습니다. */
@Configuration
@Profile("prod")
public class ProdDataSourceConfig {
    @Bean
    public DataSourceInfoPrinter dataSourceInfoPrinter() {
        return () ->
                System.out.println("운영용 데이터베이스");
    }
}
