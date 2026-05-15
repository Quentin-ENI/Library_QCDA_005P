package com.eni.library.bll;

import com.eni.library.bo.Book;

import java.util.List;

public interface BookService {
    List<Book> getBooks();
    Book getBookByIsbn(String isbn);
}
