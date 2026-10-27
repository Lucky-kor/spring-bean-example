package com.codeit.springbeanexampleV4.book;

import java.util.List;
import java.util.Optional;

/** 서비스가 의존하는 저장소 계약입니다. 단위 테스트에서는 직접 만든 구현으로 교체할 수 있습니다. */
public interface BookRepository {
    List<Book> findAll();
    Optional<Book> findBySequence(int sequence);
}
