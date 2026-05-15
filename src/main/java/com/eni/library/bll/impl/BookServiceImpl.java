package com.eni.library.bll.impl;

import com.eni.library.bll.BookService;
import com.eni.library.bo.Author;
import com.eni.library.bo.Book;
import com.eni.library.dto.BookDto;
import com.eni.library.exceptions.AuthorException;
import com.eni.library.exceptions.BookException;
import com.eni.library.repository.AuthorRepository;
import com.eni.library.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    @Override
    public List<Book> getBooks() {
        List<Book> books = bookRepository.findAll();
        return books;
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        Optional<Book> optionalBook = bookRepository.findById(isbn);

        if (optionalBook.isEmpty()) {
            throw new BookException("Le livre n'existe pas en base de données.");
        }

        return optionalBook.get();
    }

    @Override
    public Book create(BookDto bookDto) {
        Optional<Author> optionalAuthor = authorRepository.findById(bookDto.getAuthorKey());

        if (optionalAuthor.isEmpty()) {
            throw new AuthorException("L'auteur n'existe pas en base de données");
        }

        Book book = Book.builder()
                .author(optionalAuthor.get())
                .editor(bookDto.getEditor())
                .isbn(bookDto.getIsbn())
                .releaseDate(bookDto.getReleaseDate())
                .build();

        try {
            book = bookRepository.save(book);
        } catch (RuntimeException exception) {
            throw exception;
        }

        return book;
    }
}
