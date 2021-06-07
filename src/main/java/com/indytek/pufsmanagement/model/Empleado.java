package com.indytek.pufsmanagement.model;

import javax.persistence.*;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

@Entity
@DiscriminatorValue(value="employee")
public class Empleado extends Persona {

	@NonNull
	@Enumerated
	@Column (name = "position")
	private Cargo position;

	@Column (name = "horaEntrada")
	private LocalTime horaEntrada;

	@Column (name = "horaSalida")
	private LocalTime horaSalida;

	/*//Comentado debido a conservación de codigo.
		no es necesario especificar relaccion ya que pedido contendrá el username del empleado.
	/*
	pedidos atendidos por el empleado (si no es de tipo repartidor, la lista estará vacía y no se tratará)
	@Singular
	//lazy collection debido a que hibernate no puede soportar dos bolsas de pedidos a la vez (empleado y usuario)
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade=CascadeType.PERSIST)//, fetch=FetchType.EAGER)
	private List<Pedido> orders;
	*/
}
