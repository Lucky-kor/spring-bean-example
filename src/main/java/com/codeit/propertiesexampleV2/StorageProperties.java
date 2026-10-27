package com.codeit.propertiesexampleV2;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/** 중첩 객체 바인딩입니다. final s3 참조의 내부 값은 setter로 채울 수 있습니다. */
@Component
@ConfigurationProperties(prefix = "my.storage")
@Getter
@Setter
public class StorageProperties {
    private String path;
    private final S3 s3 = new S3();

    // static 중첩 타입은 바깥 객체 참조 없이 생성됩니다. s3.bucket, s3.region을 묶습니다.
    @Getter
    @Setter
    public static class S3 {
        private String bucket;
        private String region;
    }
}
