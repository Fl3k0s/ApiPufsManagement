package com.indytek.pufsmanagement.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString

public class PedidoSerialize {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonSerialize
    private int id;

    @JsonSerialize
    private LocalDateTime dateOrdered;

    @JsonSerialize
    private boolean android;

    @JsonSerialize
    private float price;

    @JsonSerialize
    private float pay;

    @JsonSerialize
    private float exchange;

    @JsonSerialize
    private String notes;
    //cambiado de productos a integer por que solo es necesario la id para luego mostrar la informacion con consultas

    @JsonSerialize
    private int payMethod;

    @JsonSerialize
    private List<Integer> products;
    @JsonSerialize
    private String username;
}
