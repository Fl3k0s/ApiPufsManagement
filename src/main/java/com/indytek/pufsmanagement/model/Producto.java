package com.indytek.pufsmanagement.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@ToString

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn( name="product", discriminatorType=DiscriminatorType.STRING )
@JsonTypeInfo(
		use = JsonTypeInfo.Id.CLASS,
		include = JsonTypeInfo.As.PROPERTY,
		property = "type"

)
@JsonSubTypes({
		@JsonSubTypes.Type(value = Comida.class, name = "food"),
		@JsonSubTypes.Type(value = Bebida.class, name = "drink")
})
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
