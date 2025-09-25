package com.example.streaming.entity;
import jakarta.persistence.*; import lombok.*; import java.util.*;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Categoria {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable=false, unique=true, length=80) private String nome;
    private String descricao;
    @OneToMany(mappedBy="categoria", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Video> videos = new ArrayList<>();
}