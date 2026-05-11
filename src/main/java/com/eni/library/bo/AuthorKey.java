package com.eni.library.bo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class AuthorKey implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "prenom", nullable = false, unique = false, length = 100)
    private String firstName;

    @Column(name = "nom_de_famille", nullable = false, unique = false, length = 100)
    private String lastName;
}
