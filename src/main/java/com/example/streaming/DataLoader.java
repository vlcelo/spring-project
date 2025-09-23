package com.example.streaming;

import com.example.streaming.entity.*;
import com.example.streaming.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner seed(
            CategoriaRepository categoriaRepo,
            VideoRepository videoRepo,
            UsuarioRepository usuarioRepo,
            VisualizacaoRepository visualizacaoRepo,
            AvaliacaoRepository avaliacaoRepo
    ) {
        return args -> {
            var acao = categoriaRepo.save(Categoria.builder().nome("Ação").descricao("Filmes e séries de ação").build());
            var drama = categoriaRepo.save(Categoria.builder().nome("Drama").descricao("Dramas aclamados").build());
            var doc  = categoriaRepo.save(Categoria.builder().nome("Documentário").descricao("Docs e biografias").build());

            var alice = usuarioRepo.save(Usuario.builder().nome("Alice").email("alice@ex.com").build());
            var bob   = usuarioRepo.save(Usuario.builder().nome("Bob").email("bob@ex.com").build());
            var carol = usuarioRepo.save(Usuario.builder().nome("Carol").email("carol@ex.com").build());

            Video[] vids = new Video[]{
                    Video.builder().titulo("Missão Eclipse").descricao("Agentes no limite").url("https://ex/missao-eclipse").dataPublicacao(LocalDate.now().minusDays(10)).duracaoSegundos(5400).categoria(acao).build(),
                    Video.builder().titulo("Missão Aurora").descricao("Operação no Ártico").url("https://ex/missao-aurora").dataPublicacao(LocalDate.now().minusDays(8)).duracaoSegundos(5200).categoria(acao).build(),
                    Video.builder().titulo("Coração de Ferro").descricao("Herói urbano").url("https://ex/coracao-ferro").dataPublicacao(LocalDate.now().minusDays(30)).duracaoSegundos(5900).categoria(acao).build(),
                    Video.builder().titulo("Lágrimas do Norte").descricao("Drama familiar").url("https://ex/lagrimas-norte").dataPublicacao(LocalDate.now().minusDays(100)).duracaoSegundos(6100).categoria(drama).build(),
                    Video.builder().titulo("Missão Final").descricao("Último ato").url("https://ex/missao-final").dataPublicacao(LocalDate.now().minusDays(2)).duracaoSegundos(5000).categoria(acao).build(),
                    Video.builder().titulo("O Último Ato").descricao("Teatro e redenção").url("https://ex/ultimo-ato").dataPublicacao(LocalDate.now().minusDays(60)).duracaoSegundos(4800).categoria(drama).build(),
                    Video.builder().titulo("Planeta Azul").descricao("Vida marinha").url("https://ex/planeta-azul").dataPublicacao(LocalDate.now().minusDays(200)).duracaoSegundos(3600).categoria(doc).build(),
                    Video.builder().titulo("Missão Submersa").descricao("Conflito no oceano").url("https://ex/missao-submersa").dataPublicacao(LocalDate.now().minusDays(15)).duracaoSegundos(5300).categoria(acao).build(),
                    Video.builder().titulo("Ventos do Deserto").descricao("Saga no Saara").url("https://ex/ventos-deserto").dataPublicacao(LocalDate.now().minusDays(90)).duracaoSegundos(5500).categoria(drama).build(),
                    Video.builder().titulo("Missão Fantasma").descricao("Infiltração secreta").url("https://ex/missao-fantasma").dataPublicacao(LocalDate.now().minusDays(4)).duracaoSegundos(5100).categoria(acao).build(),
                    Video.builder().titulo("Doc: A Arte de Persistir").descricao("Histórias reais").url("https://ex/arte-persistir").dataPublicacao(LocalDate.now().minusDays(300)).duracaoSegundos(4200).categoria(doc).build(),
                    Video.builder().titulo("Missão Relâmpago").descricao("Operação relâmpago").url("https://ex/missao-relampago").dataPublicacao(LocalDate.now().minusDays(1)).duracaoSegundos(4700).categoria(acao).build()
            };
            videoRepo.saveAll(List.of(vids));

            Random rnd = new Random(42);
            for (var v : vids) {
                int plays = 20 + rnd.nextInt(200);
                for (int i = 0; i < plays; i++) {
                    var user = switch (i % 3) {
                        case 0 -> alice;
                        case 1 -> bob;
                        default -> carol;
                    };
                    visualizacaoRepo.save(Visualizacao.builder()
                            .usuario(user)
                            .video(v)
                            .instante(java.time.Instant.now().minusSeconds(rnd.nextInt(60*60*24*30)))
                            .build());
                }
                int ratings = 5 + rnd.nextInt(20);
                for (int i = 0; i < ratings; i++) {
                    var user = (i % 2 == 0) ? alice : bob;
                    double nota = 2.5 + rnd.nextDouble() * 2.5;
                    avaliacaoRepo.save(Avaliacao.builder()
                            .usuario(user)
                            .video(v)
                            .nota(Math.round(nota * 10.0) / 10.0)
                            .comentario(null)
                            .instante(java.time.Instant.now().minusSeconds(rnd.nextInt(60*60*24*30)))
                            .build());
                }
            }

            System.out.println("=== Busca por título contendo 'Missão' (ordenado) ===");
            videoRepo.findByTituloContainingIgnoreCaseOrderByTituloAsc("Missão")
                    .forEach(v -> System.out.println(" - " + v.getTitulo()));

            System.out.println("\n=== Vídeos da categoria Ação (ordenados por título) ===");
            videoRepo.findByCategoria_NomeOrderByTituloAsc("Ação")
                    .forEach(v -> System.out.println(" - " + v.getTitulo()));

            System.out.println("\n=== Top 10 mais bem avaliados ===");
            videoRepo.top10MaisBemAvaliados(PageRequest.of(0, 10))
                    .forEach(v -> System.out.println(" - " + v.getTitulo()));

            System.out.println("\n=== Top 10 mais assistidos ===");
            videoRepo.top10MaisAssistidos(PageRequest.of(0, 10))
                    .forEach(v -> System.out.println(" - " + v.getTitulo()));

            System.out.println("\n=== Usuário que mais assistiu vídeos ===");
            var topUser = usuarioRepo.usuarioQueMaisAssistiu();
            System.out.println("Usuário: " + topUser.getNome() + " (" + topUser.getEmail() + ")");
        };
    }
}
