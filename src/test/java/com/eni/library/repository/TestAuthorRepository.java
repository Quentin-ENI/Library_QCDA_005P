package com.eni.library.repository;

import com.eni.library.bo.Author;
import com.eni.library.bo.AuthorKey;
import com.eni.library.bo.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TestAuthorRepository {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(TestAuthorRepository.class);

    @Test
    void test_save_author() {
        // A
        User user = User.builder()
                .username("john.doe")
                .password("123456")
                .build();

        AuthorKey key = AuthorKey.builder()
                .firstName("John")
                .lastName("Doe")
                .build();

        Author author = Author.builder()
                .key(key)
                .birthdate(LocalDate.now())
                .user(user)
                .build();

        // A
        Author authorDB = authorRepository.save(author);

        // A
        assertNotNull(authorDB);

        logger.info(authorDB.toString());

        Optional<User> userDB = userRepository.findById("john.doe");

        assertTrue(userDB.isPresent());

        logger.info(userDB.get().toString());
    }
}
