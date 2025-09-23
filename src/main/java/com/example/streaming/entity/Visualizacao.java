package com.example.streaming.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Visualizacao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Video video;

    @Column(nullable = false)
    private Instant instante;
}
