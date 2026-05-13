package com.eni.library.repository;

import com.eni.library.bo.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
public class TestJPQL {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void initDB() {
        Book book1 = Book.builder()
                .isbn("123-AZE-123")
                .releaseDate(LocalDate.now())
                .editor("Casterman")
                .build();

        Book book2 = Book.builder()
                .isbn("456-QSD-456")
                .releaseDate(LocalDate.now())
                .editor("Dargaud")
                .build();

        Book book3 = Book.builder()
                .isbn("789-WXC-789")
                .releaseDate(LocalDate.now())
                .editor("Dargaud")
                .build();

        entityManager.persist(book1);
        entityManager.persist(book2);
        entityManager.persist(book3);
        entityManager.flush();
    }

    @Test
    void test_JPQL() {
        // A

        // A
        List<Book> books = bookRepository.filterBook("Casterman");

        // A
        assertEquals(1, books.size());
        assertEquals("Casterman", books.getFirst().getEditor());
    }

    @Test
    void test_keyWords() {
        // A

        // A
        List<Book> books = bookRepository.findByEditor("Dargaud");

        // A
        assertEquals(2, books.size());
        assertEquals("Dargaud", books.getFirst().getEditor());
        assertEquals("Dargaud", books.getLast().getEditor());
    }
}
