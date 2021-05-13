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
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn( name="type",discriminatorType=DiscriminatorType.STRING )
/*
Clase padre de producto
 */
public class Producto implements Serializable {

	@EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="id")
	private int id;
	
	@NonNull
	@Column (name="name", length=50)
	private String name;

	//es la url de la imagen que aparecerá en las aplicaciones
	//@NonNull
	@Column (name = "url_product")
	private String urlProducto;

	@Column(name = "pvp")
	private float pvp;

	@Column(name = "tipo")
	@Enumerated
	private Tipo tipo;

	@Column(name = "rango")
	@Enumerated
	private Rango rango;

}
