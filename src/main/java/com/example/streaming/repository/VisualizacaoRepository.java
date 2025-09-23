package com.example.streaming.repository;

import com.example.streaming.entity.Visualizacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisualizacaoRepository extends JpaRepository<Visualizacao, Long> {
}
