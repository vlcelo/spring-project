package com.example.streaming.repository;

import com.example.streaming.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("select u from Usuario u join u.visualizacoes vis group by u order by count(vis) desc")
    Usuario usuarioQueMaisAssistiu();
}
