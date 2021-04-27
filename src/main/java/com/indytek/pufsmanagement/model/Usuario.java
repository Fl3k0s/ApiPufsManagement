package com.indytek.pufsmanagement.model;

import javax.persistence.*;

import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString


@Entity
public class Usuario implements Serializable {

    @NonNull
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

    @Column(name = "rango")
    private Rango rango;

    @ManyToOne(cascade = CascadeType.MERGE, fetch=FetchType.EAGER)
    private Direccion direccion;

    @Singular
    @OneToMany(cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
    @JoinColumn(name="fk_username")
    private Set<Pedido> orders;

}