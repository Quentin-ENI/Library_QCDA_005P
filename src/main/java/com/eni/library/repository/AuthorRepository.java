package com.eni.library.repository;

import com.eni.library.bo.Author;
import com.eni.library.bo.AuthorKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, AuthorKey> {
}
