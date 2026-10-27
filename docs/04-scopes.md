# 4. 빈의 범위: singleton부터

목표: 같은 빈 조회가 같은 객체를 반환하는 경우와 새 객체를 반환하는 경우를 구분합니다.

## 4-1. 기존 조회 방식으로 비교

추가한 예제의 읽는 순서: `scopeexample/SingletonBean` → `PrototypeBean` → `Application`.

기존 main과 같은 SpringApplication.run과 getBean만 사용합니다. 두 빈은 일반 클래스이고 차이는 `@Scope("prototype")`입니다.

```bash
./gradlew bootRun -Plesson=scope
```

예상 결과:

```text
SingletonBean 생성자 호출
singleton은 같은 객체인가? true
PrototypeBean 생성자 호출
PrototypeBean 생성자 호출
prototype은 같은 객체인가? false
```

Spring 시작 로그를 제외하고 각 생성 횟수와 true/false를 확인하세요.

singleton은 컨테이너에서 해당 빈을 공유하고, prototype은 컨테이너에 조회를 요청할 때마다 새 객체를 만듭니다. prototype 객체의 일반 메서드를 호출할 때마다 새로 생성된다는 뜻은 아닙니다.

실습: PrototypeBean의 Scope를 잠깐 주석 처리해 동일성이 true로 바뀌는지 확인한 뒤 복구하세요. prototype의 생성과 초기화는 Spring이 하지만 소멸 콜백은 자동 관리하지 않는다는 차이도 기억하세요.

## 4-2. 기존 session 예제는 구조까지만

읽을 코드: `springbeanexampleV7/SessionScopedBean` → `MemberService` → `Application`.

```bash
./gradlew bootRun -Plesson=session
```

기존처럼 MemberService 빈이 만들어졌다는 객체 문자열이 출력됩니다. `MemberService`는 기본 singleton이고 `SessionScopedBean`은 세션마다 대상을 달리하려는 빈입니다. 시작할 때는 HTTP 요청이나 세션이 없으므로 실제 세션 객체를 바로 주입하기 어렵습니다.

`proxyMode = ScopedProxyMode.TARGET_CLASS`는 실제 대상을 나중에 찾는 대리 객체를 주입하도록 합니다. 지금은 이 덕분에 singleton 서비스를 생성할 수 있다는 점을 봅니다. 세션마다 대상이 달라지는 실습은 HTTP 요청과 세션을 배운 뒤 할 내용입니다. main의 출력만으로 세션 분리를 확인했다고 해석하지 마세요.

실습: proxyMode 부분을 잠깐 제거하면 서비스 생성 중 활성 세션을 찾을 수 없다는 오류가 날 수 있습니다. 복구하세요. 프록시 내부 구현은 지금 암기할 필요가 없습니다.

## 확인 질문

1. singleton은 JVM 어디서든 해당 클래스의 객체를 하나만 만들 수 있다는 뜻인가요?
2. prototype을 한 번 받아 변수에 저장한 후 그 변수를 두 번 사용하면 새 객체인가요?

<details><summary>답</summary>

1. 아닙니다. 컨테이너에서 해당 빈을 관리하는 범위의 이야기입니다.
2. 같은 변수는 같은 객체를 가리킵니다. 컨테이너에 새 조회를 요청하는 시점과 구분하세요.

</details>
