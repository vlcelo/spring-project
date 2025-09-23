package com.example.streaming.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Avaliacao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Video video;
    @Column(nullable = false)
    private Double nota; // 1.0 a 5.0
    private String comentario;
    @Column(nullable = false)
    private Instant instante;
}
