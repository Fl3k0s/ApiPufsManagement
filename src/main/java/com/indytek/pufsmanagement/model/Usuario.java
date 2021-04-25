package com.indytek.pufsmanagement.model;

import javax.persistence.*;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString


@Entity
public class Usuario {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column (name="id")
    private int id;

    @NonNull
    @Column (name="username", length=50)
    private String username;

    @NonNull
    @Column (name="password", length=50)
    private String password;

    @NonNull
    @Enumerated
    @Column(name="usertype")
    private TipoUsuario usertype;

}