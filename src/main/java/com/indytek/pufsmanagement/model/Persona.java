package com.indytek.pufsmanagement.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import lombok.Singular;
import javax.persistence.OneToMany;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@ToString

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn( name="type" )
public class Persona implements Serializable {
	
	@NonNull
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

	@Column (name="email", length=50)
	private String email;

	@NonNull
	@Column (name="address")
	private String address;

	@Singular
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="username")
	private Set<Usuario> usuarios;
	
}
