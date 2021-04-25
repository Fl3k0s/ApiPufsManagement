package com.indytek.pufsmanagement.model;

import javax.persistence.*;

import lombok.*;
import lombok.experimental.SuperBuilder;

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
	@Column (name="position")
	private Cargo position;
	
}
