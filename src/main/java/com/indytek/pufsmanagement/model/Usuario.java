package com.indytek.pufsmanagement.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

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

    @Column (name="username", length=50)
    private String username;

    @NonNull
    @Column (name="password", length=50)
    private String password;

    @NonNull
    @Column(name="usertype")
    private TipoUsuario usertype;

}