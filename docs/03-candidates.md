# 3. 같은 타입의 빈이 여러 개일 때

목표: 하나를 선택하는 경우와 전부 받는 경우를 구분할 수 있습니다.

## 3-1. Qualifier와 Primary

읽을 코드: `springbeanexampleV5/Pokemon` → 세 구현 클래스 → `service/PokemonService` → `PokemonApplication`.

Pikachu, Charmander, Squirtle은 모두 Pokemon 타입입니다. 아무 기준 없이 Pokemon 하나를 요구하면 여러 후보 사이에서 모호해질 수 있습니다.

- `@Primary`는 단일 타입 주입에서 기본 후보를 지정합니다. 이 예제는 Charmander입니다.
- `@Qualifier("pikachu")`는 주입 지점에서 후보를 명시합니다. 이 예제의 서비스는 피카츄를 요구합니다.

```bash
./gradlew bootRun -Plesson=qualifier
```

피카츄 공격이 나옵니다. 다음 실습은 하나씩 바꾸고 매번 복구하세요.

1. 생성자의 `@Qualifier("pikachu")`만 제거하면 Primary인 파이리가 선택됩니다.
2. 그 상태에서 Charmander의 `@Primary`도 제거하면 단일 후보를 고를 수 없어 시작이 실패합니다.
3. 원래대로 복구한 다음 Qualifier의 이름을 존재하지 않는 이름으로 바꾸면 일치하는 빈을 찾지 못합니다.

빈 이름과 `Pokemon.name()`은 다릅니다. 예를 들어 Charmander의 name()은 `파이리`이지만 기본 빈 이름은 `charmander`입니다.

## 3-2. Map과 List

읽을 코드: `springbeanexampleV6/service/PokemonService` → `PokemonApplication`.

```bash
./gradlew bootRun -Plesson=collection
```

세 Pokemon의 공격과 `pikachu`, `squirtle`, `charmander` 키가 출력됩니다. Map의 출력 순서 자체를 학습 결과로 삼지는 마세요. `Map<String, Pokemon>`의 키는 Spring 빈 이름이고 값은 빈 객체입니다. Primary 하나만 받는 것이 아니라 해당 타입 후보들을 모두 받습니다.

Map을 이해한 뒤 서비스 아래 주석의 List 대안으로 바꿔 보세요. 생성자는 Map용과 List용을 동시에 두지 말고 하나만 남깁니다. `printKeys()`와 main의 해당 호출도 주석 처리합니다. List에는 Map의 키가 없기 때문입니다.

List를 쓰면 후보의 `@Order`에 따라 피카츄 → 꼬부기 → 파이리 순서를 볼 수 있습니다. 여기의 Order는 목록 정렬 기준입니다. 빈의 초기화 순서는 [5단계](05-lifecycle-order.md)에서 따로 구분합니다. 처음에는 Map 실습까지만 해도 됩니다.

## 확인 질문

1. Qualifier가 붙은 서비스에서 Primary를 바꾸면 선택이 달라지나요?
2. Map의 키를 `파이리`로 찾으면 Charmander 빈이 나오나요?

<details><summary>답</summary>

1. 해당 Qualifier와 일치하는 후보가 선택되므로 현재 예제에서는 계속 피카츄입니다.
2. 현재 기본 빈 이름은 `charmander`입니다. name()의 반환값은 빈 이름을 정하지 않습니다.

</details>
