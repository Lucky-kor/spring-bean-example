# 1. 빈 등록과 조회

목표: 객체를 누가 만들고, 어떤 이름으로 등록하고, 어디서 조회하는지 설명할 수 있습니다.

## 1-1. 스캔과 @Service

읽을 코드: `springbeanexample/SpringBeanExampleApplicationV1` → `service/MyService`.

기존 흐름을 그대로 따라갑니다.

```java
ApplicationContext context = SpringApplication.run(SpringBeanExampleApplicationV1.class, args);
MyService myService = (MyService) context.getBean("myService");
myService.testMethod();
```

Spring이 생성·관리하는 객체가 빈입니다. `@Service`는 컴포넌트 스캔 대상이며 기본 이름은 클래스 첫 글자를 소문자로 바꾼 `myService`입니다. `getBean("이름")`은 Object를 돌려주므로 기존 코드처럼 형변환합니다. `getBean(MyService.class)`는 타입으로 조회하므로 별도 형변환이 필요 없습니다. 먼저 기존 이름 조회를 이해한 뒤 비교하세요.

```bash
./gradlew bootRun -Plesson=beans
```

`testMethod`와 같은 singleton `true`가 나옵니다. singleton은 이 컨테이너에서 해당 빈을 공유한다는 뜻입니다. 같은 클래스로 `new`를 호출하는 것까지 막는 의미는 아닙니다.

실습: `@Service`를 잠깐 주석 처리하면 조회할 빈을 찾지 못합니다. 원인을 확인한 뒤 복구하세요.

## 1-2. @Configuration과 @Bean

읽을 코드: `SpringBeanExampleApplicationV2` → `config/ContextConfiguration` → `dto/MemberDto`.

`@Configuration("configureClassExample")`는 설정 클래스 빈의 이름입니다. `@Bean(name = "member1")`는 메서드가 반환한 회원 객체의 이름입니다. 이름이 서로 다르다는 점을 먼저 확인하세요.

```bash
./gradlew bootRun -Plesson=lookup
```

회원 이름과 `@Bean` 메서드로 받아도 같은 객체 `true`가 출력됩니다. 기본 `@Configuration`의 빈 메서드 호출은 Spring이 관리하는 singleton을 돌려주도록 처리됩니다. `new ContextConfiguration()`으로 직접 생성한 객체에는 이 처리가 없습니다. 이 부분의 내부 구현은 나중에 공부해도 됩니다.

DTO를 빈으로 등록한 것은 등록 원리를 보여주는 예제입니다. 일반적인 회원 데이터 객체를 모두 singleton 빈으로 만들라는 뜻은 아닙니다. singleton에 사용자별 가변 데이터를 저장하면 여러 사용자가 같은 데이터를 공유하게 됩니다.

## 1-3. 스캔 범위와 제외

읽을 코드: `springbeanexampleV2/SpringBeanExampleApplication` → `service/MyService` → `config/ContextConfiguration`.

`@SpringBootApplication`은 기본적으로 자신의 패키지와 하위를 스캔합니다. `com.codeit` 전체로 범위를 넓히면 다른 수업의 같은 이름 빈들이 충돌할 수 있으므로 현재처럼 분리합니다.

현재 실행 클래스의 `@ComponentScan`은 REGEX 필터로 service 패키지를 제외합니다.

```bash
./gradlew bootRun -Plesson=scan
```

`myService` 등록은 `false`, 회원에게 주입된 계좌의 동일성은 `true`입니다. `@ComponentScan` 선언 전체를 잠깐 주석 처리하면 기본 스캔이 적용되어 `myService`는 `true`가 됩니다. 실습 후 복구하세요.

`@Bean getMember(Account account)`의 `account`는 컨테이너가 넣어 준 계좌입니다. 그 값을 MemberDto 생성자에 전달하므로 같은 빈임을 비교할 수 있습니다.

## 1-4. 외부 라이브러리 객체 등록

읽을 코드: `springbeanexampleV3/config/CustomWebConfig` → `Application`.

외부 라이브러리 JsonMapper의 소스를 수정해 `@Component`를 붙일 수 없으므로 `@Bean` 메서드에서 생성해 반환합니다. 지금은 라이브러리 생성 문법을 외우기보다 반환 객체가 빈으로 등록되는 점에 집중하세요.

```bash
./gradlew bootRun -Plesson=json
```

들여쓰기 설정 `true`가 출력됩니다. Boot 4는 Jackson 3의 `tools.jackson` 패키지를 사용합니다. 기존 Java 소스 디렉터리에 있던 YAML은 자동 로딩되지 않았으므로 [설명용 참고 파일](examples/jackson-settings.yml)로 옮겼습니다. 직접 만든 JsonMapper와 Boot가 자동 구성한 JsonMapper에 설정을 적용하는 경로가 같다고 가정하지 마세요.

## 확인 질문

1. 클래스 이름에 Service가 들어가면 자동으로 빈이 되나요?
2. @Configuration 이름과 @Bean 이름이 가리키는 객체는 같은가요?
3. 제외 필터에 걸린 @Service는 등록되나요?

<details><summary>답</summary>

1. 아닙니다. 스캔 범위 안에서 @Service 같은 애너테이션을 발견하거나 @Bean으로 등록해야 합니다.
2. 각각 설정 객체와 메서드의 반환 객체입니다.
3. 등록되지 않습니다. 애너테이션 유무와 스캔 규칙을 함께 확인해야 합니다.

</details>
