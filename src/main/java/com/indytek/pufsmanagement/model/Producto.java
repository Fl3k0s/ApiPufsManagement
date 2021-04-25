package com.indytek.pufsmanagement.model;

import java.io.Serializable;

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
public class Producto implements Serializable {

	@NonNull
	@EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="id")
	private int id;
	
	@NonNull
	@Column (name="name", length=50)
	private String name;
	
	@Column (name="provider", length=50)
	private String providerEmail;

}
