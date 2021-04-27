package com.indytek.pufsmanagement.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString


@Entity
/*
Clase de direccion
 */
public class Direccion implements Serializable {

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
    @Column(name = "numero", length = 10)
    private String numero;

    //se pone como String porque el portal puede ser una letra
    @Column(name = "portal")
    private String portal;

    @Column(name = "piso")
    private int piso;

    @Column(name = "puerta", length = 5)
    private String puerta;
}
