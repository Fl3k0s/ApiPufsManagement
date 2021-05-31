package com.indytek.pufsmanagement.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@ToString

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn( name="type" )
/*
Clase padre de persona
 */
public class Persona implements Serializable {

	@EqualsAndHashCode.Include
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="id")
	private int id;

	@Column (name="name", length=50)
	private String name;

	@Column (name="secondname1", length=50)
	private String secondName1;

	@Column (name="secondname2", length=50)
	private String secondName2;

	//el email será unico
	@Column (name="email", length=50, unique = true)
	private String email;
	
}
