# 5. 초기화, 소멸, 순서를 구분하기

목표: 빈이 준비되는 순서와 준비 후 작업의 실행 순서를 구분할 수 있습니다.

## 5-1. 생명주기

읽을 코드: `springbeanexampleV8/Application` → `TestComponent` → `AppConfig`와 `TestBean` → `InterfaceTestBean`.

```bash
./gradlew bootRun -Plesson=lifecycle
```

먼저 시작할 때 초기화 로그를 보고, Ctrl+C로 정상 종료하여 소멸 로그를 봅니다. IDE의 강제 종료나 프로세스 강제 삭제는 정리 콜백을 보장하지 않습니다.

| 방식 | 초기화 | 소멸 | 읽을 파일 |
| --- | --- | --- | --- |
| 애너테이션 | PostConstruct | PreDestroy | TestComponent |
| Bean 메서드 설정 | initMethod="init" | destroyMethod="cleanup" | AppConfig, TestBean |
| Spring 인터페이스 | afterPropertiesSet | destroy | InterfaceTestBean |

일반적으로 객체 생성 → 의존성 주입 → 초기화 콜백 → 사용 → 소멸 콜백으로 읽으세요. `@Bean` 방식은 외부 클래스에도 초기화 메서드를 지정할 수 있고, 인터페이스 방식은 클래스가 Spring 인터페이스에 직접 의존합니다. 세 개의 서로 다른 빈 사이에 출력되는 전체 순서를 외우는 것이 목표는 아닙니다.

## 5-2. DependsOn은 빈 준비의 관계

읽을 코드: `dependexample/dependson/FirstBean`, `SecondBean` → `Application`.

```bash
./gradlew bootRun -Plesson=depends
```

SecondBean 생성자 로그가 FirstBean보다 먼저 나옵니다. FirstBean에 `@DependsOn("secondBean")`이 있기 때문입니다. 이 애너테이션이 SecondBean 객체를 필드에 넣어 주지는 않습니다. 실제로 사용할 객체가 필요하면 생성자 주입으로 받는 것이 자연스럽습니다.

실습: DependsOn을 주석 처리해 보세요. 순서가 우연히 그대로일 수 있어도 더 이상 이 관계로 순서를 보장하는 것은 아닙니다. 파일명이나 클래스 스캔 순서로 보장한다고 해석하지 마세요. 실습 후 복구하세요. singleton에서 DependsOn은 소멸 시 반대 방향의 관계에도 영향을 줍니다.

## 5-3. Ordered는 Runner.run 순서

읽을 코드: `dependexampleV2/dependson/FirstBean`, `ThirdBean`, `SecondBean` → `Application`.

```bash
./gradlew bootRun -Plesson=order
```

기존 Ordered 인터페이스를 사용합니다. getOrder()가 1, 2, 3인 순서로 다음이 출력됩니다.

```text
FirstBean run method called
ThirdBean run method called
SecondBean run method called
```

CommandLineRunner의 run은 컨테이너 준비가 끝난 후 실행됩니다. 작은 숫자가 먼저 실행되며, 이 숫자가 각 빈의 생성자 호출 순서까지 정하는 것은 아닙니다. 숫자를 바꿔 run 순서만 비교한 뒤 복구하세요.

## 확인 질문

1. 생성자에서 주입될 필드의 값을 읽어도 항상 준비되어 있나요?
2. Runner의 실행 순서를 바꾸려면 DependsOn을 붙이면 되나요?

<details><summary>답</summary>

1. 필드 주입은 생성자 이후에 이루어집니다. 주입 완료 후 작업에는 초기화 콜백을 구분해서 사용합니다.
2. 이 예제에서는 Ordered의 getOrder()를 바꿉니다. 빈 초기화 의존 관계와 Runner 목록의 실행 순서는 다른 개념입니다.

</details>
