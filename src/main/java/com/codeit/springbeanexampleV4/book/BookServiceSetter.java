package com.codeit.springbeanexampleV4.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/** 비교용 setter 주입 예제입니다. 생성 후 의존성을 넣으므로 불완전한 상태가 잠시 존재합니다. */
@Service
public class BookServiceSetter {
    // 생성 직후에는 null입니다. Spring은 빈 생성 후 이 setter를 호출합니다.
    // @Autowired 기본 required=true이므로 컨테이너 안에서는 의존성 누락 시 시작 실패합니다.
    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findBooks() {
        return bookRepository.findAll();
    }

    public Book findBook(int sequence) {
        return bookRepository.findBySequence(sequence)
                .orElseThrow(
                        () -> new IllegalArgumentException("해당 도서가 없습니다."));
    }
}
