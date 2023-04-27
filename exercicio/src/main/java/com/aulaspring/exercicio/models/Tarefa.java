package com.aulaspring.exercicio.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_tarefa")
@Setter @Getter @NoArgsConstructor @AllArgsConstructor @ToString @EqualsAndHashCode
public class Tarefa {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;

    private Nivel nivel;

    private boolean realizado;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario responsavel;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

}
