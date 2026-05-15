package com.eni.library.bll.impl;

import com.eni.library.bll.BookService;
import com.eni.library.bo.Book;
import com.eni.library.exceptions.BookException;
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
}
