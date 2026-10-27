# 7. 프로파일과 조건부 등록

목표: 파일의 값 선택과 빈 등록 선택을 구분하고 둘을 함께 사용할 수 있습니다.

## 7-1. 공통 파일과 환경별 파일

읽을 순서: `application.yml` → `application-local.yml` → `application-dev.yml` → `application-prod.yml`.

공통 파일에는 기본 데이터를, 프로파일 파일에는 환경별 차이를 둡니다. `spring.profiles.default: local`은 명시적인 active 프로파일이 없을 때만 적용합니다. dev를 활성화하면 공통과 dev를 읽고, local 파일은 적용하지 않습니다.

프로파일별 파일에는 `spring.profiles.active/default`를 넣지 않습니다. 공통 파일이나 실행 환경에서 프로파일을 선택해야 합니다. v1/v2와 secret의 용도는 [6단계](06-properties.md)를 참고하세요.

## 7-2. 같은 계약의 빈을 환경에 따라 선택

읽을 코드: `profileexampleV1/DataSourceInfoPrinter` → `DevDataSourceConfig` → `ProdDataSourceConfig` → `Application`.

```bash
./gradlew bootRun -Plesson=profile
./gradlew bootRun -Plesson=profile --args='--spring.profiles.active=dev'
./gradlew bootRun -Plesson=profile --args='--spring.profiles.active=prod'
```

각 실행 사이에 이전 앱을 종료합니다. 첫 두 경우는 `개발용 데이터베이스`, 마지막은 `운영용 데이터베이스`입니다. 이름에 DB가 있지만 실제 접속 없이 메시지만 출력합니다.

`@Profile({"local", "dev", "v2"})`의 배열은 OR입니다. 셋 중 하나만 활성화되어도 설정 클래스를 사용합니다. `@Profile("prod")`는 prod일 때 사용합니다. 같은 이름 dataSourceInfoPrinter를 한 번만 등록한다는 전제입니다.

실패 실습:

```bash
./gradlew bootRun -Plesson=profile --args='--spring.profiles.active=dev,prod'
```

두 설정 클래스가 같은 이름 빈을 등록하려 하므로 충돌합니다. 빈 덮어쓰기를 켜서 넘어가기보다 왜 함께 켜졌는지 확인하세요. 반대로 이 예제에서 지원하지 않는 프로파일만 활성화하면 주입할 DataSourceInfoPrinter가 없어 시작에 실패합니다.

## 7-3. 값의 우선순위

아래는 이 실습에서 쓰는 소스끼리의 비교입니다. Spring의 모든 설정 소스를 나열한 목록은 아닙니다.

```text
명령행 앱 인자 > OS 환경변수 > 같은 위치의 프로파일 YAML > 공통 YAML
```

```bash
MY_GREETING=from-env ./gradlew bootRun -Plesson=value   --args='--spring.profiles.active=dev --my.greeting=from-cli'
```

첫 인사는 from-cli입니다. 명령행의 my.greeting을 빼면 from-env, 환경변수도 빼면 Hello, Dev!입니다. 직접 하나씩 빼고 실행해 보세요.

`-P`는 Gradle 옵션이므로 `-Pspring.profiles.active=dev`를 써도 이 프로젝트에서 자동으로 앱에 전달되지 않습니다. `--args` 안에 넣으세요. IDE에서 해당 main을 실행할 때는 프로그램 인자에 `--spring.profiles.active=dev`를 적거나 실행 구성의 프로파일/환경변수 기능을 사용합니다.

## 7-4. 프로파일 대신 특정 값으로 빈 등록

읽을 코드: `conditionalexample/FeatureConfig` → `FeatureService` → `Application`. 마지막으로 `FeatureXEnabledCondition`은 직접 조건을 구현하는 참고 코드로 읽습니다.

```bash
./gradlew bootRun -Plesson=condition
./gradlew bootRun -Plesson=condition --args='--custom.feature-x=true'
```

기본 false에서는 `Feature X 비활성화: featureService 빈이 없습니다.`, true에서는 활성화 메시지가 나옵니다. `@ConditionalOnProperty`가 빈을 만들지 말지 결정합니다. 이미 만들어진 객체의 일반 메서드를 실행할지 고르는 조건문과 구분하세요.

main의 containsBean은 등록 결과를 확인합니다. 빈이 없는데 getBean을 무조건 호출하면 예외가 나므로 if로 나눴습니다. 기존 FeatureXEnabledCondition으로 비교하고 싶다면 FeatureConfig의 ConditionalOnProperty를 주석 처리하고 Conditional 줄 하나만 활성화하세요. 처음부터 두 조건을 함께 켜지 마세요.

## 확인 질문

1. application-dev.yml만 만들면 dev가 자동 활성화되나요?
2. feature-x를 false로 바꾸면 FeatureService 객체는 있지만 사용만 하지 않는 건가요?

<details><summary>답</summary>

1. 아닙니다. 활성 프로파일을 선택해야 읽습니다.
2. 이 예제는 조건이 false이면 해당 Bean 정의 자체를 등록하지 않습니다.

</details>
