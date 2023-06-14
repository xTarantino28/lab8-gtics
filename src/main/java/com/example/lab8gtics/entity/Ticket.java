package com.example.lab8gtics.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @JoinColumn(name ="idTipoTicket")
    private Tipo_ticket_evento idTipoTicket;

    @OneToOne
    @JoinColumn(name ="idUsuario")
    private Usuario idUsuario;

}
