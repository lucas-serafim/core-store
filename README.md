# Core-Store API 🛒

O **Core-Store** é uma API REST desenvolvida para gerenciar as operações de uma loja virtual. O foco do projeto é oferecer um sistema robusto para controle de estoque, organização de produtos por categorias, processamento de pedidos e uma camada de segurança para usuários.

## 🚀 Tecnologias Utilizadas

* **Linguagem:** Java 17+
* **Framework:** Spring Boot 3
* **Banco de Dados:** PostgreSQL
* **Segurança:** Spring Security com JWT (JSON Web Token)
* **Persistência:** Spring Data JPA
* **Arquitetura:** Stateless

---

## 🏗️ Funcionalidades

O projeto foi estruturado para suportar o fluxo completo de uma e-commerce:

* **Gestão de Catálogo:** Cadastro e organização de categorias e produtos.
* **Controle de Estoque:** Monitoramento de produtos disponíveis para venda.
* **Fluxo de Pedidos:** Criação de pedidos com múltiplos itens e funcionalidade de cancelamento.
* **Segurança:** Sistema de cadastro e login de usuários protegido por autenticação JWT, garantindo que operações sensíveis sejam restritas.

---

## 📊 Modelo de Dados (Entidades)

Abaixo, as principais entidades que compõem a inteligência de negócio do sistema:

1.  **User:** Armazena credenciais e perfis de acesso.
2.  **Category:** Agrupa os produtos por nichos.
3.  **Product:** Detalhes do produto, preço e quantidade em estoque.
4.  **Order (Pedido):** Registro da venda, data, status e o usuário comprador.
5.  **OrderItem:** Tabela associativa que relaciona produtos a um pedido, armazenando o preço no momento da compra e a quantidade.

---

## 🔒 Autenticação

A segurança utiliza o modelo **Stateless** com tokens JWT:
1. O usuário envia suas credenciais para o endpoint de autenticação.
2. Após a validação, a API retorna um token.
3. O cliente deve incluir esse token no cabeçalho `Authorization: Bearer <TOKEN>` para acessar as rotas protegidas de produtos e pedidos.

---

## 🛠️ Como Executar o Projeto

### Pré-requisitos
* JDK 17 ou superior
* Maven
* Instância do PostgreSQL ativa

### Passo a passo
1. **Clone o repositório:**
   
   ```bash
   git clone [https://github.com/seu-usuario/core-store.git](https://github.com/seu-usuario/core-store.git)

2. **Configure o application.properties:**
   
   ```bash
   spring.datasource.url=jdbc:postgresql://localhost:5432/core_store
   spring.datasource.username=username
   spring.datasource.password=password

   spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
   spring.jpa.hibernate.ddl-auto=update

   api.security.token.secret=${JWT_SECRET:sua_secret}

3. **Abre o projeto em sua IDE de escolha e rode o projeto**
   
   caso queira rodar o projeto via linha de comando
   `````bash
   
   mvn spring-boot:run
