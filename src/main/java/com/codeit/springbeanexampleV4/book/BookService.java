package com.codeit.springbeanexampleV4.book;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
// Lombok이 모든 필드를 인자로 받는 생성자를 만듭니다.
// 이 클래스에서는 public BookService(BookRepository bookRepository) { this.bookRepository = bookRepository; }
// 와 같은 모양입니다. 생성자가 하나이면 Spring은 @Autowired 없이도 그 생성자를 사용합니다.
@AllArgsConstructor
public class BookService {
    // 필수 의존성을 생성할 때 받습니다. final은 이 참조를 나중에 바꾸지 못하게 합니다.
    private final BookRepository bookRepository;

    public List<Book> findBooks() {
        return bookRepository.findAll();
    }

    public Book findBook(int sequence) {
        // Optional에 값이 없으면 예외를 던집니다. 빈 주입 실패와 도서 조회 실패는 다릅니다.
        return bookRepository.findBySequence(sequence)
                .orElseThrow(() -> new IllegalArgumentException("해당 도서가 없습니다."));
    }
}
