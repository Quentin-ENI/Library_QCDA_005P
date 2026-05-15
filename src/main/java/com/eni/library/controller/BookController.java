package com.eni.library.controller;

import com.eni.library.bll.BookService;
import com.eni.library.bo.Book;
import com.eni.library.dto.ApiResponse;
import com.eni.library.dto.BookDto;
import com.eni.library.exceptions.AuthorException;
import com.eni.library.exceptions.BookException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<ApiResponse<BookDto>> create(
            @RequestBody BookDto bookDto
    ) {
        Book book;
        try {
            book = bookService.create(bookDto);
        } catch (RuntimeException exception) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(
                            ApiResponse.<BookDto>builder()
                                    .statusCode(HttpStatus.NOT_FOUND.value())
                                    .message(exception.getMessage())
                                    .build()
                    );
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ApiResponse
                                .<BookDto>builder()
                                .statusCode(HttpStatus.CREATED.value())
                                .message(HttpStatus.CREATED.name())
                                .data(
                                        BookDto
                                                .builder()
                                                .isbn(book.getIsbn())
                                                .editor(book.getEditor())
                                                .authorKey(book.getAuthor().getKey())
                                                .releaseDate(book.getReleaseDate())
                                                .build()
                                )
                                .build()
                );
    }
}
