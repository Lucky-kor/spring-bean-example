package com.codeit.springbeanexampleV4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.codeit.springbeanexampleV4.book.BookService;
import com.codeit.springbeanexampleV4.book.BookServiceField;
import com.codeit.springbeanexampleV4.book.BookServiceSetter;

/** 현재 패키지의 빈만 읽는 독립 예제입니다. README의 해당 단계와 함께 보세요. */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // Spring을 시작하고, 생성·관리된 빈을 조회할 수 있는 컨테이너를 받습니다.
        ApplicationContext context = SpringApplication.run(Application.class, args);

        // 1. 먼저 생성자 주입 예제만 읽고 실행합니다.
        BookService bookService = (BookService) context.getBean("bookService");
        bookService.findBooks().forEach(System.out::println);
        System.out.println(bookService.findBook(1));
        System.out.println(bookService.findBook(2));

        // 2. 위 BookService 조회 대신 아래 한 쌍을 풀어 setter 주입을 비교합니다.
        // BookServiceSetter setterService = (BookServiceSetter) context.getBean("bookServiceSetter");
        // System.out.println(setterService.findBook(1));

        // 3. 그다음 필드 주입을 비교합니다. 세 방식 모두 같은 BookRepository를 사용합니다.
        // BookServiceField fieldService = (BookServiceField) context.getBean("bookServiceField");
        // System.out.println(fieldService.findBook(1));

        // 4. 마지막으로 없는 도서를 조회하면 어떤 예외가 나는지 확인합니다.
        // System.out.println(bookService.findBook(999));
    }
}
