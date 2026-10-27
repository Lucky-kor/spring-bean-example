# 6. 외부 설정을 한 값부터 읽기

목표: YAML 키를 값 하나, 설정 객체, 목록, Map으로 읽는 방식을 구분합니다.

## 6-1. Value

읽을 코드: `src/main/resources/application.yml` → `propertiesexampleV1/GreetingService` → `Application`.

```bash
./gradlew bootRun -Plesson=value
```

기본 local에서 주요 결과는 `Hello, Local!`, 계산값 16160, `anonymous`, 서버 이름 목록입니다.

| 표현식 | 의미 |
| --- | --- |
| `${my.greeting}` | 설정 키 조회 |
| `${my.username:anonymous}` | 키가 없을 때 기본값 적용 |
| `#{${server.port} * 2}` | 설정값을 읽고 SpEL로 계산 |
| `#{'${my.server-names}'.split(',')}` | 문자열을 쉼표로 나눔 |

계산식의 16160은 실제 서버 포트를 바꾸는 값이 아닙니다. 실제 server.port는 8080입니다. 빈 문자열을 지정하는 경우와 키 자체가 없는 경우도 구분하세요.

## 6-2. Environment부터 설정 객체로

읽을 코드: `propertiesexampleV2/Application` → `GreetingService` → `MyServiceProperties` → `MyServicePropertiesV2` → `MyServicePropertiesV3`.

```bash
./gradlew bootRun -Plesson=binding
```

처음에는 원래 활성 코드인 Environment 조회만 실행됩니다. 출력은 my.service.port의 8081과 my.servers[1].port의 80입니다. Environment.getProperty는 키로 읽으며, Integer.class를 지정하면 타입 변환을 요청합니다. 필수 키가 빠져 null이 나오는데 int로 받으면 문제가 될 수 있습니다.

이후 main의 주석 묶음을 하나씩 풀어 아래 순서로 읽습니다.

1. MyServiceProperties: `@Value`로 각 값을 받습니다.
2. MyServicePropertiesV2: `@ConfigurationProperties(prefix="my.service")`로 묶어서 받습니다. @Component는 빈 등록, setter는 값 바인딩에 사용됩니다.
3. StorageProperties: `my.storage.s3.bucket`처럼 중첩 객체입니다. static 중첩 S3 타입이 실제 사용되는 타입입니다.
4. HostListProperties와 SettingsProperties: YAML 목록과 Map을 받습니다. Map의 boolean/숫자 값이 문자열 필드에 변환되는 것도 관찰합니다.
5. MyServicePropertiesV3: 바인딩 후 @Validated로 설정을 검증합니다.

기본 이름은 local 프로파일이 덮어쓴 `SpringDocs-local`입니다. my.service.port와 server.port는 다른 키입니다. 앞쪽은 학습용 서비스 설정, 뒤쪽은 내장 서버 포트입니다.

문자열 목록은 `my.server-names`, 객체 목록은 `my.servers`로 분리했습니다. 같은 키에 문자열과 객체 목록을 섞으면 두 예제가 같은 설정을 공유하기 어렵기 때문입니다.

## 6-3. 잘못된 값으로 시작해 보기

```bash
./gradlew bootRun -Plesson=binding --args='--my.service.port=0'
```

1 이상이어야 한다는 검증 때문에 시작이 실패합니다. 65535보다 큰 값과 빈 name도 거절합니다. 이 검증은 main에서 값을 출력할 때가 아니라 빈을 바인딩하여 생성하는 과정에서 일어납니다. main의 해당 출력이 주석이어도 @Component는 등록되므로 검증은 실행됩니다.

## 6-4. 환경변수 필수값

읽을 코드: `application-secret.yml` → `propertiesExampleV3/MemberService` → `Application`.

이 단계만 secret 프로파일과 가짜 환경변수를 함께 지정합니다.

```bash
PASSWORD=practice-only ./gradlew bootRun -Plesson=secret --args='--spring.profiles.active=secret'
```

`외부 비밀번호가 비어 있지 않음: true`가 나옵니다. 원문은 출력하지 않습니다. secret YAML의 `${PASSWORD}`에 기본값이 없으므로 PASSWORD를 주지 않으면 해당 예제는 시작할 수 없습니다. secret 프로파일도 빼면 my.password 키 자체가 없습니다. 두 경우를 나누어 살펴보세요.

Windows PowerShell에서는 다음처럼 실행하고 연습 후 환경변수를 지웁니다.

```powershell
$env:PASSWORD='practice-only'
./gradlew.bat bootRun -Plesson=secret --args='--spring.profiles.active=secret'
Remove-Item Env:PASSWORD
```

`.env` 파일은 자동으로 읽지 않습니다. 다른 예제를 실행할 때는 secret을 선택할 필요가 없습니다.

## 기존 v1/v2 파일

기존 `application-v1.yml`, `application-v2.yml`은 남겨 두었지만 설정 바인딩에 필요한 기본 데이터는 공통 YAML로 옮겼습니다. v1/v2는 코드 버전별 예제 선택이 아닙니다. `-Plesson=value` 또는 `-Plesson=binding`으로 코드를 고르고, 환경은 local/dev/prod로 비교하세요. 기존 v1/v2를 지정하면 greeting을 바꿔 보는 추가 연습이 됩니다.

## 확인 질문

1. @ConfigurationProperties만 붙이면 항상 빈으로 등록되나요?
2. @Validated가 설정값을 확인하는 시점은 언제인가요?

<details><summary>답</summary>

1. 별도의 등록 경로가 필요합니다. 현재 예제는 @Component로 등록합니다.
2. 앱 시작 중 해당 설정 빈을 생성하고 바인딩하는 과정입니다.

</details>
