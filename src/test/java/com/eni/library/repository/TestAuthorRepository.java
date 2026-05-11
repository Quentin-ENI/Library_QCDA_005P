package com.eni.library.repository;

import com.eni.library.bo.Author;
import com.eni.library.bo.AuthorKey;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TestAuthorRepository {

    @Autowired
    private AuthorRepository authorRepository;

    private Logger logger = LoggerFactory.getLogger(TestAuthorRepository.class);

    @Test
    void test_save_author() {
        // A
        AuthorKey key = AuthorKey.builder()
                .firstName("John")
                .lastName("Doe")
                .build();

        Author author = Author.builder()
                .key(key)
                .birthdate(LocalDate.now())
                .build();

        // A
        Author authorDB = authorRepository.save(author);

        // A
        assertNotNull(authorDB);

        logger.info(authorDB.toString());
    }
}
