package com.codeit.profileexampleV1;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor // 활성 프로파일에서 등록된 DataSourceInfoPrinter를 생성자로 주입합니다.
public class Application {
    private final DataSourceInfoPrinter printer;

    @PostConstruct // 생성자 주입이 끝난 뒤 한 번 실행합니다.
    public void init() {
        // 실제 DB 접속 없이, 선택된 구현의 출력만 비교합니다.
        printer.printDataSourceInfo();
    }

    public static void main(String[] args) {
        // local/dev면 개발용, prod면 운영용 출력입니다. 둘을 함께 선택하면 빈 이름이 충돌합니다.
        SpringApplication.run(Application.class, args);
    }
}
