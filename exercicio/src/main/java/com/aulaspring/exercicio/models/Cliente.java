package com.aulaspring.exercicio.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_cliente")
@Setter @Getter @NoArgsConstructor @AllArgsConstructor @ToString @EqualsAndHashCode
public class Cliente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    // nullable = false para não ser nulo o campo no banco de dados
    // unique = true para não permitir cadastro duplicado
    @Column(nullable = false, unique = true)
    private String email;

}
