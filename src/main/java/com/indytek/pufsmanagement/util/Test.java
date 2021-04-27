package com.indytek.pufsmanagement.util;

import lombok.*;

import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString


@Entity
/*
Clase de pruebas
 */
public class Test {

    @NonNull
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @NonNull
    @Column (name="name", length=50)
    private String name;

}
