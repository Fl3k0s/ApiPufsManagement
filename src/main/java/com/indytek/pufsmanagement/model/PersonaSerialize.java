package com.indytek.pufsmanagement.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import java.io.Serializable;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString

//Clase persona para serializar en android
public class PersonaSerialize implements Serializable {
    @EqualsAndHashCode.Include
    private int id;

    private String dni;

    private String name;

    private String secondName1;

    private String secondName2;

    private String email;
}
