package com.indytek.pufsmanagement.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

@Entity
@DiscriminatorValue(value = "drink")
/*
Clase de bebidas (PRODUCTO)
 */
public class Bebida extends Producto implements Serializable {

	@Column (name="volumen")
	private float volumen;

	//tamaño en litros de la bebida
	@Column (name = "tamaño")
	private float tamaño;

}
