package com.codeit.conditionalexample;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/** 프로파일 이름 대신 외부 설정값으로 등록 여부를 결정합니다. false/누락이면 빈을 만들지 않습니다. */
@Configuration
public class FeatureConfig {

    @Bean
    // 직접 작성한 조건을 비교할 때만 아래 줄을 풀고 ConditionalOnProperty는 주석 처리합니다.
    // @Conditional(FeatureXEnabledCondition.class)
    @ConditionalOnProperty(name = "custom.feature-x", havingValue = "true")
    public FeatureService featureService() {
        return new FeatureService();
    }
}
