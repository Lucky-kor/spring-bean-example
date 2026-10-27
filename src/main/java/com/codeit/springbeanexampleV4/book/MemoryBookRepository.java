package com.codeit.springbeanexampleV4.book;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/** DB 없는 읽기 전용 메모리 예제입니다. findAll은 복사한 목록을 반환하여 외부의 목록 변경을 막습니다. */
@Repository
public class MemoryBookRepository implements BookRepository {
    private final List<Book> books = new ArrayList<>();

    public MemoryBookRepository() {
        books.add(new Book(1, "Spring Core", "Kim"));
        books.add(new Book(2, "JPA Master", "Lee"));
        books.add(new Book(3, "Clean Code", "Robert C. Martin"));
    }

    @Override
    public List<Book> findAll() {
        return books.stream()
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Book> findBySequence(int sequence) {
        return books.stream()
                .filter(book -> book.getSequence() == sequence)
                .findFirst();
    }
}
