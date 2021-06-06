package com.indytek.pufsmanagement.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString


@Entity
/*
Clase de proveedor
 */
public class Proveedor implements Serializable {

    @NonNull
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    @Column (name="id")
    private int id;

    @NonNull
    @Column(name = "calle", length = 30)
    private String calle;

    //se pone como String porque puede haber numeros que son 5 bis
    @NonNull
    @Column(name = "nombre", length = 25)
    private String nombre;

    @Email
    @Column (name="email", length=50, unique = true)
    private String email;

    @Column(name = "telefono", length = 9)
    private String telefono;
}