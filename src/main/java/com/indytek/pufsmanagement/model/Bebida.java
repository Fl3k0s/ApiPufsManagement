package com.indytek.pufsmanagement.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

@Entity
@DiscriminatorValue("drink")
/*
Clase de bebidas (PRODUCTO)
 */
public class Bebida extends Producto {

	@Column (name="uds")
	private int uds;

}
