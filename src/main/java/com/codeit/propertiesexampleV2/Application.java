package com.codeit.propertiesexampleV2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


/** 현재 패키지의 빈만 읽는 독립 예제입니다. README의 해당 단계와 함께 보세요. */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // Spring을 시작하고, 생성·관리된 빈을 조회할 수 있는 컨테이너를 받습니다.
        ApplicationContext context = SpringApplication.run(Application.class, args);

        // 처음에는 Environment로 개별 값을 읽는 기존 예제부터 실행합니다.
        GreetingService greetingService = context.getBean(GreetingService.class);
        greetingService.printPort();

        // 아래는 한 묶음씩 주석을 풀어 비교하세요. 처음부터 모두 실행할 필요는 없습니다.

        // 1. @Value로 세 개의 값을 따로 받습니다.
        // MyServiceProperties values = context.getBean(MyServiceProperties.class);
        // System.out.println(values.getName());
        // System.out.println(values.getPort());
        // System.out.println(values.isEnabled());

        // 2. 같은 값을 @ConfigurationProperties의 setter 바인딩으로 받습니다.
        // MyServicePropertiesV2 service = context.getBean(MyServicePropertiesV2.class);
        // System.out.println(service.getName());
        // System.out.println(service.getPort());
        // System.out.println(service.isEnabled());

        // 3. 중첩 객체입니다. my.storage.s3.bucket이 getS3().getBucket()에 대응합니다.
        // StorageProperties storage = context.getBean(StorageProperties.class);
        // System.out.println(storage.getPath());
        // System.out.println(storage.getS3().getBucket());
        // System.out.println(storage.getS3().getRegion());

        // 4. YAML 목록과 Map을 각각 확인합니다.
        // HostListProperties hosts = context.getBean(HostListProperties.class);
        // System.out.println(hosts.getServers());
        // SettingsProperties settings = context.getBean(SettingsProperties.class);
        // settings.printSettings();

        // 5. @Validated가 추가된 설정 객체입니다.
        // MyServicePropertiesV3 validated = context.getBean(MyServicePropertiesV3.class);
        // System.out.println(validated.getName());
        // System.out.println(validated.getPort());
        // 이 패키지의 @Component는 출력 주석 여부와 무관하게 모두 생성/바인딩됩니다.
        // 따라서 port를 0으로 바꾸면 위 주석을 풀지 않아도 시작할 때 검증이 실패합니다.
    }
}
