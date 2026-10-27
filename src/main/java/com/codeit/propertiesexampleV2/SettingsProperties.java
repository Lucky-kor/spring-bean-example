package com.codeit.propertiesexampleV2;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/** YAML의 키/값 묶음을 Map으로 받습니다. 문자열 값으로 변환되는 결과를 출력합니다. */
@Component
@ConfigurationProperties(prefix = "my")
@Getter
@Setter
public class SettingsProperties {
    private Map<String, String> settings;

    public void printSettings() {
        for(Map.Entry<String, String> entry : settings.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
