package com.eni.library.repository;

import com.eni.library.bo.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TestBookRepository {

    private static final Logger logger = LoggerFactory.getLogger(TestBookRepository.class);

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void initialize() {
        this.bookRepository.deleteAll();
    }

    @Test
    void test_save_success() {
        // Arrange - Mise en place
        LocalDate now = LocalDate.now();

        Book book = Book.builder()
                .isbn("123-BONJOUR-456")
                .releaseDate(now)
                .author("Victor Hugo")
                .editor("Gallimard")
                .build();

        // Act - Appel de la méthode
        Book bookDB = this.bookRepository.save(book);

        // Assert - Vérification
        assertEquals("Victor Hugo", bookDB.getAuthor());
        assertEquals("Gallimard", bookDB.getEditor());
        assertEquals("123-BONJOUR-456", bookDB.getIsbn());
        assertEquals(now, bookDB.getReleaseDate());

        logger.info(bookDB.toString());
    }
}
