# 0. build.gradle 읽기

목표: Java 코드를 실행하기 전에 어떤 도구가 무엇을 준비하는지 설명할 수 있습니다.

읽는 순서: `settings.gradle` → `gradle/wrapper/gradle-wrapper.properties` → `build.gradle`.

## 플러그인과 의존성은 다릅니다

| 부분 | 역할 | 이 프로젝트에서 볼 것 |
| --- | --- | --- |
| `plugins`의 java | Java 컴파일 등의 작업 제공 | compileJava, jar |
| Spring Boot 플러그인 | Boot 앱 실행과 실행 가능한 JAR 생성 | bootRun, bootJar |
| dependency-management | Boot BOM의 호환 버전 목록 사용 | 의존성마다 버전을 따로 적지 않음 |
| `repositories` | 라이브러리를 찾을 장소 | Maven Central |
| `dependencies` | 소스가 사용할 라이브러리 | MVC, Validation, Lombok 등 |

플러그인은 빌드 도구의 기능을 추가하고, 라이브러리 의존성은 소스에서 import할 클래스를 제공합니다. starter는 관련 라이브러리를 모아 가져오는 묶음입니다. Boot 4의 `webmvc` 이름을 이전 프로젝트의 Boot 3 starter 이름과 혼동하지 마세요.

`group`은 산출물의 소속, `version`은 버전, `description`은 설명입니다. `settings.gradle`의 프로젝트 이름은 기본 JAR 이름에도 반영됩니다. 이 값이 Java 패키지를 자동으로 바꾸지는 않습니다.

## JDK와 Wrapper

`java.toolchain.languageVersion = 17`은 Java 컴파일에 사용할 JDK를 고릅니다. `java -version`으로 보이는 터미널 JDK와 반드시 같지는 않습니다. JDK 17이 없으면 컴파일할 JDK를 찾지 못할 수 있습니다. 이 프로젝트는 JDK 자동 다운로드를 설정하지 않았습니다.

`./gradlew`는 프로젝트에 고정된 Gradle을 사용합니다. 전역에 설치한 `gradle`과 버전이 다를 수 있습니다. Wrapper 속성의 `distributionUrl`이 버전을 결정합니다. Gradle 9 자체 실행에는 JDK 17 이상이 필요합니다.

## 의존성 범위

| 선언 | 의미 |
| --- | --- |
| implementation | main 코드 컴파일과 실행에 사용 |
| compileOnly | 컴파일할 때만 사용, 실행 JAR에는 넣지 않음 |
| annotationProcessor | 컴파일 중 애너테이션을 읽고 코드를 생성 |
| testImplementation/testRuntimeOnly | 기존 기본 프로젝트가 가진 테스트 전용 의존성. 지금 학습에서는 건너뛰어도 됨 |

Lombok은 `compileOnly`와 `annotationProcessor`가 함께 있습니다. 앞쪽은 `@Getter` 같은 애너테이션을 알게 하고 뒤쪽은 getter/생성자 코드를 생성하게 합니다. 클래스에 `@AllArgsConstructor`가 보이면 모든 필드를 인자로 받는 생성자가 만들어진다고 읽으세요.

## main 선택 부분은 필요한 만큼만

`lessons`는 예제 이름과 main 클래스 이름을 연결한 Gradle 설정입니다. `-Plesson=di`를 주면 `springbeanexampleV4.Application`을 선택합니다. 생략하면 `beans`입니다. `def`는 이 Groovy 빌드 파일의 변수 선언이며 Java 소스의 문법과 구분하세요.

```bash
./gradlew lessons
./gradlew compileJava
./gradlew bootRun -Plesson=di
```

`compileJava`는 컴파일만 합니다. `bootRun`은 앱을 실제 시작하며 종료할 때까지 실행됩니다. JAR로 실행해 보고 싶을 때만 다음을 사용하세요.

```bash
./gradlew bootJar -Plesson=di
java -jar build/libs/spring-bean-example-0.0.1-SNAPSHOT.jar
```

이 JAR에는 선택한 main이 진입점으로 들어갑니다. 다른 예제를 담으려면 다른 lesson으로 다시 생성하세요. 원하면 JAR 뒤에 `--spring.profiles.active=dev`를 붙일 수 있습니다.

## 확인 질문

1. `-Plesson=profile`과 `--spring.profiles.active=dev`는 같은 것인가요?
2. Lombok을 실행 JAR에 넣지 않아도 getter를 사용할 수 있는 이유는 무엇인가요?

<details><summary>답</summary>

1. 앞쪽은 Gradle이 실행할 main 선택, 뒤쪽은 Spring이 사용할 환경 선택입니다. 뒤쪽은 bootRun의 `--args` 안에 넣습니다.
2. 컴파일할 때 이미 getter 코드가 생성되어 클래스 파일에 들어 있기 때문입니다.

</details>
