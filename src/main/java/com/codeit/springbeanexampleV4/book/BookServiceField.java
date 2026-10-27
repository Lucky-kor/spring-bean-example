package com.codeit.springbeanexampleV4.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/** 비교용 필드 주입 예제입니다. 권장 기준은 BookService의 명시적인 생성자 주입입니다. */
@Service
public class BookServiceField {
    // 생성자/API에 의존성이 드러나지 않습니다. new BookServiceField()만 하면 null입니다.
    // 리플렉션으로 넣을 수는 있지만 생성자 주입보다 일반 Java 단위 테스트가 번거롭습니다.
    @Autowired
    private BookRepository bookRepository;

    public List<Book> findBooks() {
        return bookRepository.findAll();
    }

    public Book findBook(int sequence) {
        return bookRepository.findBySequence(sequence)
                .orElseThrow(
                        () -> new IllegalArgumentException("해당 도서가 없습니다."));
    }
}
