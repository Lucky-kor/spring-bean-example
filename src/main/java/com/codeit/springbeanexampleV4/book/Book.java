package com.codeit.springbeanexampleV4.book;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** 불변 필드와 Lombok getter를 가진 도서 데이터입니다. 데이터 객체마다 Spring 빈일 필요는 없습니다. */
@AllArgsConstructor
@Getter
public class Book {
    private final int sequence;
    private final String title;
    private final String author;

    @Override
    public String toString() {
        return "Book{" +
                "sequence=" + sequence +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
