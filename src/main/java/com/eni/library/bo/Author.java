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
@Table(name = "Author")
///   Méthode 1
//@IdClass(AuthorKey.class)
public class Author {
///   Méthode 1
//    @Id
//    @Column(name = "prenom", nullable = false, unique = false, length = 100)
//    private String firstName;
//
//    @Id
//    @Column(name = "nom_de_famille", nullable = false, unique = false, length = 100)
//    private String lastName;

/// Méthode 2
    @EmbeddedId
    private AuthorKey key;

    @Column(name = "date_de_naissance")
    private LocalDate birthdate;
}
