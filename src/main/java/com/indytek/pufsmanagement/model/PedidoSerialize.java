package com.indytek.pufsmanagement.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString

//clase pedido para serializar en android
public class PedidoSerialize implements Serializable {
    @EqualsAndHashCode.Include
    private int id;


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
