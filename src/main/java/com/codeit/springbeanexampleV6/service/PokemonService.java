package com.codeit.springbeanexampleV6.service;

import com.codeit.springbeanexampleV6.Pokemon;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class PokemonService {
    // 같은 타입이 여럿일 때 하나를 고르는 대신 모두 받을 수 있습니다.
    // key: Spring 빈 이름(pikachu 등), value: 해당 Pokemon 빈 객체
    private final Map<String, Pokemon> pokemons;

    public PokemonService(Map<String, Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public void pokemonAttack() {
        // Map에서 순회되는 순서를 생성 순서나 업무 순서로 가정하지 마세요.
        for (Pokemon pokemon : pokemons.values()) {
            pokemon.attack();
        }
    }

    public void printKeys() {
        for (String keyName : pokemons.keySet()) {
            System.out.println(keyName);
        }
    }

    // [다음 비교] Map 방식 전체를 아래 List 방식으로 바꿔 보세요. 생성자는 하나만 둡니다.
    // private final List<Pokemon> pokemons;
    // public PokemonService(List<Pokemon> pokemons) { this.pokemons = pokemons; }
    // public void pokemonAttack() {
    //     for (Pokemon pokemon : pokemons) { pokemon.attack(); }
    // }
    // List로 바꿀 때는 printKeys()와 main의 service.printKeys()도 주석 처리합니다.
    // List는 후보 빈의 @Order 순서를 반영합니다. @Order는 빈 생성 순서를 정하지 않습니다.
}
