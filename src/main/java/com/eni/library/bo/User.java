package com.eni.library.bo;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "USER_DETAILS")
public class User {
    @Id
    @Column(name = "USERNAME", length = 100)
    private String username;

    @Column(name = "PASSWORD", length = 68)
    @ToString.Exclude
    private String password;

    @OneToOne(mappedBy = "user")
    @ToString.Exclude
    private Author author;
}
