# Spring Bean Example 학습 가이드

기존 예제의 `main → SpringApplication.run → ApplicationContext.getBean → 결과 출력` 흐름으로 Spring 빈을 공부합니다. 변수는 `ApplicationContext`, `BookService`처럼 타입을 직접 적습니다. 각 패키지를 하나의 수업으로 보고, 한 가지 개념을 읽고 실행한 다음 다음 단계로 넘어가세요.

Java 17, Spring Boot 4.1.1, Gradle Wrapper 9.8.2를 사용합니다. `spring-starter-practice`의 Boot 3과 주요 버전이 다르므로 Jackson의 import와 starter 이름도 다릅니다. 이 프로젝트의 기존 Boot 4 설정을 유지했습니다.

## 1. 시작하기

JDK 17을 설치하세요. Gradle 자체를 실행할 JVM과 Java 소스를 컴파일하는 toolchain은 구분합니다. 현재 개발 환경처럼 Gradle은 JDK 21로 실행하고 소스는 JDK 17로 컴파일할 수도 있습니다.

```bash
java -version
./gradlew --version
./gradlew compileJava
./gradlew lessons
./gradlew bootRun
```

`bootRun`의 기본 예제는 `beans`입니다. 시작 로그 아래의 `testMethod`, `같은 singleton 빈인가? true`를 확인하세요. 여기서 `testMethod`는 원래 서비스 메서드의 출력 문자열입니다.

이 프로젝트에는 실행 클래스가 여럿 있습니다. `-Plesson=이름`은 그중 실행할 **기존 main 클래스 하나**를 선택하는 Gradle 옵션입니다. 새로운 실행 체계를 공부할 필요 없이 아래 표에서 이름을 골라 쓰면 됩니다. IDE에서는 해당 클래스의 `main`을 직접 실행해도 됩니다.

```bash
./gradlew bootRun -Plesson=di
```

한 번에 하나씩 실행하고, 다음 예제로 넘어갈 때 `Ctrl+C`로 종료하세요. 기존 웹 starter가 있으므로 서버도 시작되지만, 이 학습의 결과는 **터미널 출력**으로 확인합니다. 브라우저 주소나 API 요청을 사용할 필요가 없습니다. Windows에서는 `./gradlew` 대신 `gradlew.bat`를 사용합니다. 최초 실행에는 의존성 다운로드를 위한 네트워크가 필요합니다.

## 2. 개념별 학습 순서

아래 패키지는 `src/main/java/com/codeit/` 아래에 있습니다. `V1`, `V2` 같은 이름만 보고 순서를 추측하지 말고 표의 단계대로 읽으세요. 각 문서는 읽을 파일, 설명, 실행 방법, 예상 결과, 확인 질문을 담고 있습니다.

| 단계 | 이번에 볼 개념 | lesson | 기존 예제 패키지 | 학습 자료 |
| --- | --- | --- | --- | --- |
| 0 | Gradle과 실행 클래스 선택 | — | `build.gradle`, `settings.gradle` | [빌드 설정](docs/00-gradle.md) |
| 1-1 | 컴포넌트 스캔으로 빈 등록 | `beans` | `springbeanexample`의 ApplicationV1 | [빈 등록과 조회](docs/01-beans.md) |
| 1-2 | `@Bean`, 이름 조회, singleton | `lookup` | 같은 패키지의 ApplicationV2 | [빈 등록과 조회](docs/01-beans.md) |
| 1-3 | 스캔 범위와 제외 필터 | `scan` | `springbeanexampleV2` | [빈 등록과 조회](docs/01-beans.md) |
| 1-4 | 외부 라이브러리 객체 수동 등록 | `json` | `springbeanexampleV3` | [빈 등록과 조회](docs/01-beans.md) |
| 2 | 생성자 → setter → 필드 주입 비교 | `di` | `springbeanexampleV4` | [의존성 주입](docs/02-di.md) |
| 3-1 | 같은 타입 중 하나 고르기 | `qualifier` | `springbeanexampleV5` | [여러 후보 빈](docs/03-candidates.md) |
| 3-2 | 같은 타입을 Map/List로 모두 받기 | `collection` | `springbeanexampleV6` | [여러 후보 빈](docs/03-candidates.md) |
| 4-1 | singleton과 prototype 비교 | `scope` | 추가한 `scopeexample` | [빈의 범위](docs/04-scopes.md) |
| 4-2 | 세션 스코프와 프록시의 역할 | `session` | `springbeanexampleV7` | [빈의 범위](docs/04-scopes.md) |
| 5-1 | 초기화와 소멸 콜백 | `lifecycle` | `springbeanexampleV8` | [생명주기와 순서](docs/05-lifecycle-order.md) |
| 5-2 | 빈 초기화 의존 순서 | `depends` | `dependexample` | [생명주기와 순서](docs/05-lifecycle-order.md) |
| 5-3 | 시작 후 Runner 실행 순서 | `order` | `dependexampleV2` | [생명주기와 순서](docs/05-lifecycle-order.md) |
| 6-1 | `@Value`, 기본값, SpEL | `value` | `propertiesexampleV1` | [외부 설정 읽기](docs/06-properties.md) |
| 6-2 | 객체·목록·Map 바인딩과 검증 | `binding` | `propertiesexampleV2` | [외부 설정 읽기](docs/06-properties.md) |
| 6-3 | 환경변수에서 필수값 받기 | `secret` | `propertiesExampleV3` | [외부 설정 읽기](docs/06-properties.md) |
| 7-1 | 환경별 YAML과 `@Profile` | `profile` | `profileexampleV1` | [프로파일과 조건부 빈](docs/07-profiles-conditions.md) |
| 7-2 | 값에 따라 빈을 등록하기 | `condition` | `conditionalexample` | [프로파일과 조건부 빈](docs/07-profiles-conditions.md) |

처음에는 실행 클래스와 그 클래스가 조회하는 빈 하나만 읽으세요. 특히 `binding`은 기존처럼 주석을 한 묶음씩 풀어 출력해 봅니다. 출력 주석을 풀지 않아도 `@Component` 클래스는 스캔되어 빈으로 등록된다는 점은 별도로 기억하세요.

## 3. 먼저 실행해 볼 명령

각 명령 사이에 실행 중인 앱을 종료하세요.

```bash
./gradlew bootRun -Plesson=beans
./gradlew bootRun -Plesson=lookup
./gradlew bootRun -Plesson=di
./gradlew bootRun -Plesson=qualifier
./gradlew bootRun -Plesson=collection
./gradlew bootRun -Plesson=scope
```

| 예제 | 핵심 출력 |
| --- | --- |
| beans | `testMethod`, 같은 singleton `true` |
| lookup | 회원 이름, `@Bean` 메서드로 받아도 같은 객체 `true` |
| scan | `myService` 등록 `false`, 주입된 계좌 동일성 `true` |
| json | 들여쓰기 설정 `true` |
| di | 도서 목록과 1·2번 도서 |
| qualifier | `@Qualifier("pikachu")`로 피카츄 공격 |
| collection | 세 Pokemon의 공격과 빈 이름 세 개, Map 순서는 고정하지 않음 |
| scope | singleton 동일성 `true`, prototype 동일성 `false` |

`session` 예제의 main은 기존처럼 서비스 생성까지만 확인합니다. 실제 HTTP 세션이 바뀔 때의 동작은 웹을 배운 뒤 다룹니다. 지금은 singleton 서비스에 session 빈을 넣을 때 프록시가 필요한 이유를 이해하면 됩니다.

## 4. 프로파일 선택

예제 선택과 환경 선택을 구분하세요. `-Plesson`은 실행할 Java 클래스, `--spring.profiles.active`는 앱이 읽을 설정과 등록할 빈을 고릅니다.

```bash
# 미지정: 공통 YAML + local YAML, 개발용 출력 빈
./gradlew bootRun -Plesson=profile

# 공통 YAML + dev YAML, 개발용 출력 빈
./gradlew bootRun -Plesson=profile --args='--spring.profiles.active=dev'

# 공통 YAML + prod YAML, 운영용 출력 빈
./gradlew bootRun -Plesson=profile --args='--spring.profiles.active=prod'

# 환경변수 방식 (macOS/Linux)
SPRING_PROFILES_ACTIVE=dev ./gradlew bootRun -Plesson=profile

# 프로파일은 값 읽기 예제에도 적용됩니다.
./gradlew bootRun -Plesson=value --args='--spring.profiles.active=dev'

# 프로파일 이름이 아니라 설정값으로 빈 등록 여부를 바꿉니다.
./gradlew bootRun -Plesson=condition --args='--custom.feature-x=true'
```

| 프로파일 | 읽는 파일 | server.port | my.greeting | profile 예제의 구현 |
| --- | --- | --- | --- | --- |
| 미지정 또는 local | 공통 + local | 8080 | Hello, Local! | 개발용 |
| dev | 공통 + dev | 8081 | Hello, Dev! | 개발용 |
| prod | 공통 + prod | 8082 | Hello, Prod! | 운영용 |

프로파일별 파일의 값은 공통값 중 같은 키만 덮어씁니다. `dev`를 지정했을 때 `local` 파일을 먼저 읽는 것은 아닙니다. `prod`도 실제 DB에 연결하지 않는 학습용입니다. `secret`의 필수 환경변수 실습과 `v1/v2` 파일 설명은 [설정 문서](docs/06-properties.md)에 있습니다.

## 5. 보강한 내용

- 기존 패키지와 main 중심의 실행 방식을 유지하고, 빈 등록·주입·조회 위치를 한국어 주석으로 설명했습니다.
- 서로 다른 주제를 단계별 문서로 분리했습니다. 기존의 생성자/setter/필드 주입, `@Value`/설정 바인딩 비교는 주석을 한 묶음씩 풀며 볼 수 있습니다.
- `main`이 잘못된 실행 클래스를 참조하던 부분을 고쳤고, Gradle에서 실행 클래스를 명시적으로 선택할 수 있게 했습니다.
- singleton과 prototype을 같은 `getBean` 방식으로 비교하는 작은 예제를 추가했습니다.
- 비활성 조건부 빈을 무조건 조회하던 부분은 `containsBean` 확인으로 바꿨습니다. `@DependsOn`은 실제 적용하여 실행 로그로 관계를 볼 수 있게 했습니다.
- 문자열 서버 목록과 객체 서버 목록의 설정 키를 분리하고, 중첩 객체/목록/Map 바인딩을 단계별로 설명했습니다.
- 기존 Java 소스 폴더의 YAML은 자동 로딩되지 않는다는 점을 설명한 [참고 파일](docs/examples/jackson-settings.yml)로 옮겼습니다. 실제 실행 설정은 `src/main/resources`에 있습니다.
- `build.gradle`의 플러그인·의존성 범위·toolchain·실행 클래스 선택과 모든 YAML의 프로파일/설정값에 주석을 보강했습니다.
- 비밀번호는 필수 환경변수로 받되 원문 출력은 없앴습니다. 잘못된 설정과 후보 빈 누락 등은 main 실행으로 원인을 살펴보는 실습으로 안내합니다.

## 6. 막힐 때

| 증상 | 확인할 것 |
| --- | --- |
| `Permission denied` | `chmod +x gradlew` |
| toolchain 17을 찾지 못함 | JDK 17 설치, IDE Gradle JVM과 컴파일 JDK 구분 |
| 포트가 이미 사용 중 | 이전 앱 종료 또는 `--args='--server.port=9090'` |
| 브라우저에서 404 | 결과는 main의 터미널 출력으로 확인 |
| 실행할 main이 여럿이라고 나옴 | `./gradlew bootRun -Plesson=...` 사용, IDE에서는 해당 main 실행 |
| 빈을 못 찾음 | 빈 이름, 스캔 패키지, 제외 필터, 조건/프로파일 확인 |
| 같은 타입 후보가 여러 개 | `@Qualifier`, `@Primary`, Map/List 중 목적에 맞는 방식 확인 |
| binding 시작 실패 | `my.service.port` 1~65535, name 공백 여부 확인 |
| profile에서 같은 이름 빈 충돌 | `dev,prod`를 동시에 선택했는지 확인 |
| secret 실행 실패 | `secret` 프로파일과 `PASSWORD` 환경변수를 함께 지정 |
| `.env`를 수정해도 그대로 | 이 프로젝트는 `.env`를 자동 로드하지 않음 |

## 7. 공식 참고 자료

학습 예제는 Boot 4.1.1 기준입니다. 공식 링크는 최신 패치 문서로 연결될 수 있습니다.

- [Boot 시스템 요구사항](https://docs.spring.io/spring-boot/4.1/system-requirements.html)
- [Spring 빈과 IoC 컨테이너](https://docs.spring.io/spring-framework/reference/core/beans.html)
- [빈 스코프](https://docs.spring.io/spring-framework/reference/core/beans/factory-scopes.html)
- [생명주기](https://docs.spring.io/spring-framework/reference/core/beans/factory-nature.html)
- [초기화 의존 관계](https://docs.spring.io/spring-framework/reference/core/beans/dependencies/factory-dependson.html)
- [Boot 외부 설정](https://docs.spring.io/spring-boot/4.1/reference/features/external-config.html)
- [Boot 프로파일](https://docs.spring.io/spring-boot/4.1/reference/features/profiles.html)
