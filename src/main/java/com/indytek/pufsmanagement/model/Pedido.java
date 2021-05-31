package com.indytek.pufsmanagement.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Singular;
import lombok.ToString;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString


@Entity
public class Pedido implements Serializable {
	
	@EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="id")
	private int id;

	/*

	username comentado debido a que ya existe una relaccion onetomany desde cliente


	 */

	@Column (name="dateOrdered")
	private LocalDateTime dateOrdered;
	
	@Column (name="android")
	private Boolean android;

	@Column(name = "price")
	private float price;

	@Column(name = "pay")
	private float pay;

	@Column(name = "exchange")
	private float exchange;

	@Column(name = "notes")
	private String notes;
	//cambiado de productos a integer por que solo es necesario la id para luego mostrar la informacion con consultas

	@Column(name = "pay method")
	private MetodoDePago payMethod;

	@Singular
	@ManyToMany(cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
	@JoinTable(name = "rel_order_product",
			  joinColumns = @JoinColumn(name = "fk_order"),
			  inverseJoinColumns = @JoinColumn(name = "fk_product"))
	private List<Producto> products;

	@Column (name="username", length=50)
	private String username;

}


