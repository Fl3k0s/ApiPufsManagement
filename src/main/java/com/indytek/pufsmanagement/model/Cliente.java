package com.indytek.pufsmanagement.model;

import java.util.Set;

import javax.persistence.*;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

@Entity
@DiscriminatorValue(value="client")
/*
Clase de clientes (PERSONA)
 */
public class Cliente extends Persona {
	
}
