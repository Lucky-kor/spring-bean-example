package com.codeit.springbeanexampleV2;

import com.codeit.springbeanexampleV2.dto.Account;
import com.codeit.springbeanexampleV2.dto.MemberDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
// 기본 스캔 범위는 실행 클래스가 있는 패키지와 그 하위입니다.
// REGEX 필터에 맞는 service 패키지는 제외합니다. @Service가 있어도 제외될 수 있습니다.
// 이 @ComponentScan 전체를 주석 처리하면 기본 스캔으로 돌아가 myService가 등록됩니다.
@ComponentScan(basePackages = "com.codeit.springbeanexampleV2",
        excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,
                pattern = "com\\.codeit\\.springbeanexampleV2\\.service\\..*"))
public class SpringBeanExampleApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringBeanExampleApplication.class, args);

        // 먼저 제외 여부 한 가지만 확인합니다. 현재는 false입니다.
        System.out.println("myService가 등록되었는가? " + context.containsBean("myService"));

        // 다음에는 수동 빈 등록과 의존 관계를 확인합니다.
        // 클래스 타입으로 조회하면 별도 형변환 없이 해당 타입을 받을 수 있습니다.
        MemberDto memberDto = context.getBean(MemberDto.class);
        Account account = context.getBean(Account.class);
        System.out.println("회원에게 주입된 계좌가 등록된 빈인가? "
                + (memberDto.getPersonalAccount() == account));

        // 전체 빈 목록이 궁금할 때만 아래를 풀어 보세요. 자동 설정 빈도 함께 출력됩니다.
        // String[] beanNames = context.getBeanDefinitionNames();
        // System.out.println(java.util.Arrays.toString(beanNames));
    }
}
