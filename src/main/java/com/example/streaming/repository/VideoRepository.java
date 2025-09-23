package com.example.streaming.repository;

import com.example.streaming.entity.Video;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findByTituloContainingIgnoreCaseOrderByTituloAsc(String titulo);
    List<Video> findByCategoria_NomeOrderByTituloAsc(String nomeCategoria);
    @Query("select v from Video v join v.avaliacaoes a group by v order by avg(a.nota) desc")
    List<Video> top10MaisBemAvaliados(Pageable pageable);
    @Query("select v from Video v join v.visualizacoes vis group by v order by count(vis) desc")
    List<Video> top10MaisAssistidos(Pageable pageable);
    @Query("select v from Video v where lower(v.titulo) like lower(concat('%', :q, '%'))")
    List<Video> searchByTitulo(@Param("q") String termo, Pageable pageable);
}
