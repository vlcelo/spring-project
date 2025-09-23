# Streaming de Vídeos — Spring Data JPA

Projeto acadêmico que implementa um **sistema de streaming** com **Spring Boot 3 + Spring Data JPA + H2**.

> O enunciado/briefing base foi disponibilizado via PDF (Univille/POO II).

## Entidades (MER → Classes)
- `Categoria (1) — (N) Video`
- `Usuario (1) — (N) Visualizacao (N) — (1) Video`
- `Usuario (1) — (N) Avaliacao (N) — (1) Video`

## Consultas solicitadas (query methods)
1. **Buscar vídeos pelo título com ordenação** — `VideoRepository#findByTituloContainingIgnoreCaseOrderByTituloAsc("Missão")`
2. **Todos os vídeos de uma categoria ordenado pelo título** — `VideoRepository#findByCategoria_NomeOrderByTituloAsc("Ação")`
3. **Top 10 vídeos mais bem avaliados** — `VideoRepository#top10MaisBemAvaliados(PageRequest.of(0,10))`
4. **Top 10 vídeos mais assistidos** — `VideoRepository#top10MaisAssistidos(PageRequest.of(0,10))`
5. **Usuário que mais assistiu vídeos** — `UsuarioRepository#usuarioQueMaisAssistiu()`

> As saídas são exibidas no console via `DataLoader` (um `CommandLineRunner`) ao subir a aplicação.

## Como executar
```bash
# Requisitos: Java 17+ e Maven
mvn spring-boot:run
```

A base em memória (H2) é carregada com **dados de exemplo** via JPA no `DataLoader`.

## Diagrama de Classes (UML)
O diagrama está em `docs/uml-class-diagram.jpg`.
Inclua este arquivo no seu repositório público no GitHub e faça referência no README.

