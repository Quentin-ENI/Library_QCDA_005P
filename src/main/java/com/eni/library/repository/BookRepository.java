package com.eni.library.repository;

import com.eni.library.bo.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {
    @Query("SELECT b FROM Book b WHERE b.editor = :editor")
    List<Book> filterBook(@Param("editor") String editor);

    List<Book> findByEditor(String editor);
}
