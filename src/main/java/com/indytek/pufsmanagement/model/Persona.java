package com.indytek.pufsmanagement.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
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
public class Persona implements Serializable {
	
	@NonNull
	@EqualsAndHashCode.Include
    @Id
	@Column (name="username", length=50)
	private String username;
	
	@NonNull
	@Column (name="password", length=50)
	private String password;

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
	
}
