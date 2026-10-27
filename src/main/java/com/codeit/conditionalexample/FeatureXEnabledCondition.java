package com.codeit.conditionalexample;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/** 직접 Condition 구현을 비교하는 확장 예제입니다. 현재는 FeatureConfig의 ConditionalOnProperty를 사용합니다. */
public class FeatureXEnabledCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String enabled = context.getEnvironment().getProperty("custom.feature-x");
        return "true".equalsIgnoreCase(enabled);
    }
}
