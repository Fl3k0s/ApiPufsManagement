package com.indytek.pufsmanagement.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
@DiscriminatorColumn( name="persona", discriminatorType=DiscriminatorType.STRING )
@JsonTypeInfo(
		use = JsonTypeInfo.Id.CLASS,
		include = JsonTypeInfo.As.PROPERTY,
		property = "type"

)
@JsonSubTypes({
		@JsonSubTypes.Type(value = Empleado.class, name = "empleado"),
		@JsonSubTypes.Type(value = Cliente.class, name = "cliente")
})


/*
Clase padre de persona
 */
public class Persona implements Serializable {

	@EqualsAndHashCode.Include
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="id")
	private int id;

	//@Id
	//@NonNull
	@Column (name = "dni", length = 9, unique = true)
	private String dni;

	@Column (name="name", length=50)
	private String name;

	@Column (name="secondname1", length=50)
	private String secondName1;

	@Column (name="secondname2", length=50)
	private String secondName2;

	//el email será unico
	@Email
	@Column (name="email", length=50, unique = true)
	private String email;
	
}
