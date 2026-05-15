package com.eni.library.controller;

import com.eni.library.bll.BookService;
import com.eni.library.bo.Book;
import com.eni.library.dto.ApiResponse;
import com.eni.library.exceptions.BookException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Book>>> list() {
        List<Book> books = bookService.getBooks();

        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(
                ApiResponse.<List<Book>>builder()
                    .statusCode(HttpStatus.OK.value())
                    .message(HttpStatus.OK.name())
                    .data(books)
                    .build()
            );
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<ApiResponse<Book>> details(@PathVariable("isbn") String isbn) {
        Book book = null;
        try {
            book = bookService.getBookByIsbn(isbn);
        } catch (BookException bookException) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                    ApiResponse
                        .<Book>builder()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .message(HttpStatus.NOT_FOUND.name())
                        .data(book)
                        .build()
                );
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                    ApiResponse
                            .<Book>builder()
                            .statusCode(HttpStatus.OK.value())
                            .message(HttpStatus.OK.name())
                            .data(book)
                            .build()
                );
    }
}
