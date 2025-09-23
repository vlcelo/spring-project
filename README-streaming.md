# 🎬 Streaming de Vídeos — Spring Data JPA

> Projeto acadêmico desenvolvido em **Java 17 + Spring Boot 3 + Spring Data JPA + H2**.  
> Entrega modelada a partir de um **MER de Sistema de Streaming de Vídeos** (Univille – POO II).

---

## 🚀 Objetivo

Implementar um **sistema de streaming** com:
- Entidades e relacionamentos (Categoria, Vídeo, Usuário, Visualização, Avaliação);
- Inserção de registros via JPA;
- Consultas (query methods + JPQL) para relatórios;
- UML completo (JPG + PDF);
- Projeto pronto para **“dar play e executar”**.

---

## 📂 Estrutura de Pacotes

```
com.example.streaming
├── entity/        # Entidades JPA (modelagem do MER)
├── repository/    # Interfaces Spring Data JPA (queries prontas)
├── DataLoader.java# Seed inicial + execução das consultas
└── StreamingApplication.java  # Main class
```

---

## 🗄️ Modelagem (MER → Classes)

- `Categoria (1) — (N) Video`  
- `Usuario (1) — (N) Visualizacao (N) — (1) Video`  
- `Usuario (1) — (N) Avaliacao (N) — (1) Video`

### Entidades e atributos
- **Categoria** → id, nome, descricao  
- **Video** → id, titulo, descricao, url, dataPublicacao, duracaoSegundos, categoria  
- **Usuario** → id, nome, email  
- **Visualizacao** → id, usuario, video, instante  
- **Avaliacao** → id, usuario, video, nota, comentario, instante  

---

## 🔎 Consultas implementadas

1. **Buscar vídeos pelo título com ordenação**  
   `findByTituloContainingIgnoreCaseOrderByTituloAsc("Missão")`

2. **Todos os vídeos de uma categoria ordenados pelo título**  
   `findByCategoria_NomeOrderByTituloAsc("Ação")`

3. **Top 10 vídeos mais bem avaliados (média da nota)**  
   `top10MaisBemAvaliados(PageRequest.of(0,10))`

4. **Top 10 vídeos mais assistidos (contagem de visualizações)**  
   `top10MaisAssistidos(PageRequest.of(0,10))`

5. **Usuário que mais assistiu vídeos**  
   `usuarioQueMaisAssistiu()`

> As consultas são executadas no **startup** da aplicação, dentro de `DataLoader`.

---

## ⚙️ Como executar

### Requisitos
- **Java 17+**
- **Maven**

### IntelliJ IDEA
1. **File > Open…** → selecione a pasta do projeto (raiz)  
2. **Project SDK = 17** e **Language level = 17**  
3. Rode `StreamingApplication.java`

### Terminal
```bash
mvn spring-boot:run
```

---

## 🖥️ Saída esperada (console)

```
=== Busca por título contendo 'Missão' (ordenado) ===
 - Missão Aurora
 - Missão Eclipse
 - Missão Fantasma
 - Missão Final
 - Missão Relâmpago
 - Missão Submersa

=== Vídeos da categoria Ação (ordenados por título) ===
 - Coração de Ferro
 - Missão Aurora
 - Missão Eclipse
 - Missão Fantasma
 - Missão Final
 - Missão Relâmpago
 - Missão Submersa

=== Top 10 mais bem avaliados ===
 - ...

=== Top 10 mais assistidos ===
 - ...

=== Usuário que mais assistiu vídeos ===
Usuário: Alice (alice@ex.com)
```

---

## 🧩 Banco de Dados (H2 em memória)

- URL: `jdbc:h2:mem:streamingdb`  
- Usuário: `sa`  
- Senha: *(vazio)*  
- Console: `http://localhost:8080/h2-console`

---

## 📊 Diagramas

- **UML (JPG)** → `docs/uml-class-diagram.jpg`  
- **UML (PDF)** → `docs/uml-class-diagram.pdf`

---

## 🛠️ Detalhes técnicos

- `pom.xml` com **maven-compiler-plugin** (release 17) para evitar erro de `-source 8`.
- **Lombok** para reduzir boilerplate (`@Getter`, `@Setter`, `@Builder`, …).
- **H2** em memória para execução rápida e reprodutível.
- **JPQL** com agregações e `Pageable` para limitar/ordenar (top 10).

---

## 🎯 Avaliação (checklist)

| Item                                   | Peso | Status |
|----------------------------------------|------|--------|
| Diagrama de classe (PDF/JPG)           | 1    | ✅ |
| Organização em pacotes                 | 1    | ✅ |
| Query methods nos repository           | 2    | ✅ |
| Modelagem das entidades                | 3    | ✅ |
| Execução correta do código             | 3    | ✅ |
| **Total**                              | 10   | **✅** |

---

## 🌟 Próximos passos (extras sugeridos)

- **Endpoints REST** (Spring Web) para expor as consultas via HTTP.
- **LEFT JOIN** nos rankings para incluir vídeos sem avaliação (nota 0).
- **Validações** com Bean Validation (`@NotBlank`, `@Email`, `@Size`).
- **Projeções/DTOs** para relatórios mais leves.
- **Migração** para Postgres/MariaDB (produção).

---

> Qualquer dúvida, abra uma *issue* ou mande um ping. Boa avaliação! 🚀
