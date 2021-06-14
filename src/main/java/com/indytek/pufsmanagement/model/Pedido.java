package com.indytek.pufsmanagement.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Singular;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

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

	@Column (name="dateOrdered", columnDefinition = "TIMESTAMP")
	private LocalDateTime dateOrdered;
	
	@Column (name="android")
	private boolean android;

	@Column(name = "price", precision = 6, scale = 2)
	private float price;

	@Column(name = "pay", precision = 6, scale = 2)
	private float pay;

	@Column(name = "exchange", precision = 6, scale = 2)
	private float exchange;

	@Column(name = "notes", length = 100)
	private String notes;
	//cambiado de productos a integer por que solo es necesario la id para luego mostrar la informacion con consultas

	@Column(name = "pay_method")
	@Enumerated
	private MetodoDePago payMethod;

	@Singular
	@ManyToMany(/*cascade=CascadeType.MERGE, */fetch=FetchType.EAGER)
	@JoinTable(name = "rel_order_product",
			  joinColumns = @JoinColumn(name = "fk_order"),
			  inverseJoinColumns = @JoinColumn(name = "fk_product"))
	private List<Producto> products;

	@Column (name="cliUsername", length=50)
	private String cliUsername;

	@Column (name="empUsername", length=50)
	private String empUsername;

	@Column (name = "order_type")
	private TipoPedido tipo;

	@Column (name ="abierto")
	private boolean abierto;

}


