package com.example.streaming;
import com.example.streaming.entity.*; import com.example.streaming.repository.*;
import org.springframework.boot.CommandLineRunner; import org.springframework.context.annotation.Bean; import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import java.time.Instant; import java.time.LocalDate; import java.util.*;

@Configuration
public class DataLoader {
    @Bean CommandLineRunner seed(CategoriaRepository categoriaRepo, VideoRepository videoRepo, UsuarioRepository usuarioRepo,
                                 VisualizacaoRepository visualizacaoRepo, AvaliacaoRepository avaliacaoRepo) {
        return args -> {
            System.out.println("[DataLoader] Iniciando seed...");
            if (usuarioRepo.count() > 0 || videoRepo.count() > 0 || categoriaRepo.count() > 0) {
                System.out.println("[DataLoader] Dados já existentes — seed ignorado."); imprimirConsultas(videoRepo, usuarioRepo); return;
            }
            var acao=categoriaRepo.save(Categoria.builder().nome("Ação").descricao("Filmes e séries de ação").build());
            var drama=categoriaRepo.save(Categoria.builder().nome("Drama").descricao("Dramas e biografias").build());
            var doc=categoriaRepo.save(Categoria.builder().nome("Documentário").descricao("Documentários").build());
            var serie=categoriaRepo.save(Categoria.builder().nome("Série").descricao("Séries de TV/streaming").build());
            var anime=categoriaRepo.save(Categoria.builder().nome("Anime").descricao("Animações japonesas").build());
            var marcelo=usuarioRepo.save(Usuario.builder().nome("Marcelo V").email("marcelo.v@ex.com").build());
            var joao=usuarioRepo.save(Usuario.builder().nome("João Rosera").email("joao.rosera@ex.com").build());
            var vinicius=usuarioRepo.save(Usuario.builder().nome("Vinicius Werner").email("vinicius.werner@ex.com").build());
            List<Video> vids=new ArrayList<>();
            vids.add(Video.builder().titulo("Missão Impossível – Efeito Fallout").descricao("Ethan Hunt tenta impedir um ataque nuclear.").url("https://example.com/missao-impossivel-fallout").dataPublicacao(LocalDate.of(2018,7,27)).duracaoSegundos(8970).categoria(acao).build());
            vids.add(Video.builder().titulo("Missão Impossível – Protocolo Fantasma").descricao("IMF é desautorizada após um atentado no Kremlin.").url("https://example.com/missao-impossivel-protocolo-fantasma").dataPublicacao(LocalDate.of(2011,12,16)).duracaoSegundos(7980).categoria(acao).build());
            vids.add(Video.builder().titulo("John Wick").descricao("Ex-assassino busca vingança.").url("https://example.com/john-wick").dataPublicacao(LocalDate.of(2014,10,24)).duracaoSegundos(6060).categoria(acao).build());
            vids.add(Video.builder().titulo("Mad Max: Estrada da Fúria").descricao("Furiosa e Max fogem de Immortan Joe.").url("https://example.com/mad-max-fury-road").dataPublicacao(LocalDate.of(2015,5,15)).duracaoSegundos(7200).categoria(acao).build());
            vids.add(Video.builder().titulo("Oppenheimer").descricao("Projeto Manhattan.").url("https://example.com/oppenheimer").dataPublicacao(LocalDate.of(2023,7,21)).duracaoSegundos(10800).categoria(drama).build());
            vids.add(Video.builder().titulo("Interestelar").descricao("Buscar novo lar pra humanidade.").url("https://example.com/interestelar").dataPublicacao(LocalDate.of(2014,11,7)).duracaoSegundos(10140).categoria(drama).build());
            vids.add(Video.builder().titulo("Clube da Luta").descricao("Clube secreto.").url("https://example.com/clube-da-luta").dataPublicacao(LocalDate.of(1999,10,15)).duracaoSegundos(8340).categoria(drama).build());
            vids.add(Video.builder().titulo("Planeta Terra II").descricao("Vida selvagem pelo mundo.").url("https://example.com/planeta-terra-2").dataPublicacao(LocalDate.of(2016,11,6)).duracaoSegundos(3600).categoria(doc).build());
            vids.add(Video.builder().titulo("The Last Dance").descricao("Era Michael Jordan no Bulls.").url("https://example.com/the-last-dance").dataPublicacao(LocalDate.of(2020,4,19)).duracaoSegundos(3000).categoria(doc).build());
            vids.add(Video.builder().titulo("Breaking Bad — S05E14: Ozymandias").descricao("Ápice trágico.").url("https://example.com/breaking-bad-ozymandias").dataPublicacao(LocalDate.of(2013,9,15)).duracaoSegundos(3000).categoria(serie).build());
            vids.add(Video.builder().titulo("Stranger Things — S01E01").descricao("Desaparecimento de Will.").url("https://example.com/stranger-things-s01e01").dataPublicacao(LocalDate.of(2016,7,15)).duracaoSegundos(3000).categoria(serie).build());
            vids.add(Video.builder().titulo("Demon Slayer: Mugen Train").descricao("Demônio no trem.").url("https://example.com/demon-slayer-mugen-train").dataPublicacao(LocalDate.of(2020,10,16)).duracaoSegundos(7020).categoria(anime).build());
            vids.add(Video.builder().titulo("Attack on Titan — S03E17: Hero").descricao("Sacrifício em Shiganshina.").url("https://example.com/aot-s03e17-hero").dataPublicacao(LocalDate.of(2019,5,19)).duracaoSegundos(1440).categoria(anime).build());
            vids.add(Video.builder().titulo("Spirited Away (A Viagem de Chihiro)").descricao("Mundo espiritual.").url("https://example.com/spirited-away").dataPublicacao(LocalDate.of(2001,7,20)).duracaoSegundos(7500).categoria(anime).build());
            vids.add(Video.builder().titulo("One Piece — Marineford (recap)").descricao("Grande guerra pelo Ace.").url("https://example.com/one-piece-marineford").dataPublicacao(LocalDate.of(2010,1,31)).duracaoSegundos(1500).categoria(anime).build());
            vids.add(Video.builder().titulo("Naruto Shippuden — S10E175: Herói da Folha").descricao("Naruto reconhecido herói.").url("https://example.com/naruto-shippuden-heroi-da-folha").dataPublicacao(LocalDate.of(2010,9,30)).duracaoSegundos(1380).categoria(anime).build());
            videoRepo.saveAll(vids);
            Random rnd=new Random(42);
            for (var v:vids){ int plays=50+rnd.nextInt(400);
                for(int i=0;i<plays;i++){ Usuario user=(i%3==0)?marcelo:(i%3==1)?joao:vinicius;
                    visualizacaoRepo.save(Visualizacao.builder().usuario(user).video(v).instante(Instant.now().minusSeconds(rnd.nextInt(60*60*24*180))).build()); }
                int ratings=8+rnd.nextInt(30);
                for(int i=0;i<ratings;i++){ var user=(i%2==0)?marcelo:joao; double nota=3.0+rnd.nextDouble()*2.0;
                    avaliacaoRepo.save(Avaliacao.builder().usuario(user).video(v).nota(Math.round(nota*10.0)/10.0).comentario(null).instante(Instant.now().minusSeconds(rnd.nextInt(60*60*24*180))).build()); } }
            System.out.println("[DataLoader] Seed concluído."); imprimirConsultas(videoRepo, usuarioRepo);
        }; }
    private void imprimirConsultas(VideoRepository videoRepo, UsuarioRepository usuarioRepo){
        System.out.println("=== Busca por título contendo 'Missão' (ordenado) ===");
        videoRepo.findByTituloContainingIgnoreCaseOrderByTituloAsc("Missão").forEach(v->System.out.println(" - "+v.getTitulo()));
        System.out.println("\n=== Vídeos da categoria Ação (ordenados por título) ===");
        videoRepo.findByCategoria_NomeOrderByTituloAsc("Ação").forEach(v->System.out.println(" - "+v.getTitulo()));
        System.out.println("\n=== Top 10 mais bem avaliados ===");
        videoRepo.top10MaisBemAvaliados(PageRequest.of(0,10)).forEach(v->System.out.println(" - "+v.getTitulo()));
        System.out.println("\n=== Top 10 mais assistidos ===");
        videoRepo.top10MaisAssistidos(PageRequest.of(0,10)).forEach(v->System.out.println(" - "+v.getTitulo()));
        System.out.println("\n=== Usuário que mais assistiu vídeos ===");
        var topUser=usuarioRepo.usuarioQueMaisAssistiu(); System.out.println("Usuário: "+topUser.getNome()+" ("+topUser.getEmail()+")");
    }
}