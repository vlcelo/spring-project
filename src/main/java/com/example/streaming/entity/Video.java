package com.example.streaming.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Video {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titulo;
    @Column(columnDefinition = "TEXT")
    private String descricao;
    @Column(nullable = false)
    private String url;
    private LocalDate dataPublicacao;
    @Column(nullable = false)
    private Integer duracaoSegundos;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Categoria categoria;
    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Visualizacao> visualizacoes = new ArrayList<>();
    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Avaliacao> avaliacaoes = new ArrayList<>();
}
