package com.eni.library.bo;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

// Lombok
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@SuperBuilder
// Data JPA
@Entity
@Table(name = "BOOK")
@Inheritance(strategy = InheritanceType.JOINED)
public class Book {
    @Id
    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "AUTHOR", nullable = false, length = 90)
    private String author;

    @Column(name = "RELEASE_DATE", nullable = false)
    private LocalDate releaseDate;

    @Column(name = "EDITOR", nullable = true)
    private String editor;
}
