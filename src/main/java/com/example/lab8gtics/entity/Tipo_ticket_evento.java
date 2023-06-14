package com.example.lab8gtics.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Table(name = "tipo_ticket_evento")
public class Tipo_ticket_evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @Column(name = "precio")
    private String precio;

    @Column(name = "cantidad")
    private Integer cantidad;

    @OneToOne
    @JoinColumn(name ="idEvento")
    private Evento idEvento;





}
