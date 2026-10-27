package com.codeit.springbeanexampleV6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.codeit.springbeanexampleV6.service.PokemonService;

/** 현재 패키지의 빈만 읽는 독립 예제입니다. README의 해당 단계와 함께 보세요. */
@SpringBootApplication
public class PokemonApplication {
    public static void main(String[] args) {
        // Spring을 시작하고, 생성·관리된 빈을 조회할 수 있는 컨테이너를 받습니다.
        ApplicationContext context = SpringApplication.run(PokemonApplication.class, args);

        PokemonService service = (PokemonService) context.getBean("pokemonService");
        service.pokemonAttack();
        // Map의 키는 Pokemon.name()이 아니라 Spring 빈 이름입니다.
        service.printKeys();
    }
}
