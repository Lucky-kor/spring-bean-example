package com.codeit.springbeanexampleV5.service;

import com.codeit.springbeanexampleV5.Pokemon;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/** 여러 Pokemon 중 Qualifier로 pikachu를 지정합니다. 생성자 인자의 변수 이름에 의존하지 않는 명시적 선택입니다. */
@Service
public class PokemonService {
    private final Pokemon pokemon;

    public PokemonService(@Qualifier("pikachu") Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public void pokemonAttack() {
        pokemon.attack();
    }
}
