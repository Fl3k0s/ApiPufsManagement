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

	@NonNull
	@Column (name = "url Producto", length = 255)
	private String urlProducto;

	@Column(name = "precio Venta")
	private float pvp;

	@Column(name = "tipo de producto")
	@Enumerated
	private Tipo tipo;

	@Column(name = "rango de producto")
	private Rango rango;

}
