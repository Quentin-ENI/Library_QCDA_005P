package com.eni.library.bo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
@Entity
@Table(name = "BOOK")
public class Book {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation
    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "AUTHOR", nullable = false, length = 90)
    private String author;

    @Column(name = "RELEASE_DATE", nullable = false)
    private LocalDate releaseDate;

    @Column(name = "EDITOR", nullable = true)
    private String editor;
}
