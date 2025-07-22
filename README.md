# 💻 Task Board - Desafio de Projeto Santander | DIO

<p align="center">
  <img alt="License" src="https://img.shields.io/badge/license-MIT-blue.svg">
  <img alt="Java" src="https://img.shields.io/badge/Java-21-blue.svg?logo=openjdk">
  <img alt="Spring Boot" src="https://img.shields.io/badge/Spring_Boot-3.3.1-blue.svg?logo=spring">
</p>

<p align="center">
  <a href="https://github.com/dougluciano2/bootcamp-dio-santander-lista-tarefas-java/actions/workflows/ci.yml">
    <img src="https://github.com/dougluciano2/bootcamp-dio-santander-lista-tarefas-java/actions/workflows/ci.yml/badge.svg" alt="Java CI with Maven"/>
  </a>
</p>


**Desenvolvido por:** [dougluciano2](https://github.com/dougluciano2)

## 📫 Como me encontrar

[![LinkedIn](https://img.shields.io/badge/LinkedIn-DouglasLuciano-blue?style=for-the-badge&logo=linkedin)](https://www.linkedin.com/in/douglasluciano/)
[![GitHub](https://img.shields.io/badge/GitHub-douglasluciano-black?style=for-the-badge&logo=github)](https://github.com/douglasluciano)
[![Portfólio](https://img.shields.io/badge/Portf%C3%B3lio-GitHub%20Pages-blueviolet?style=for-the-badge&logo=github)](https://dougluciano2.github.io)

## 🚀 Sobre o Projeto

Esta aplicação é a solução para o desafio **"Criando seu board de tarefas com Java"**, parte da trilha **"Integração Java com Banco de dados"** do bootcamp Santander | DIO. O projeto consiste em um "Board de Tarefas" Full-Stack onde usuários podem se registrar e gerenciar suas atividades através de um CRUD completo e seguro.

Foi desenvolvido com um stack moderno baseado em Java e Spring, com ênfase em práticas de código limpo, segurança e uma infraestrutura totalmente containerizada com Docker.

## ✨ Funcionalidades

-   [✔] **Gerenciamento de Usuários:** Cadastro e autenticação segura de usuários.
-   [✔] **Segurança:** Sistema de Login/Logout com Spring Security, com senhas criptografadas usando BCrypt.
-   [✔] **CRUD de Tarefas:**
    -   Criação de novas tarefas com título, descrição, status, prioridade e data de vencimento.
    -   Visualização de todas as tarefas associadas ao usuário logado.
    -   Edição de tarefas existentes.
    -   Exclusão de tarefas com confirmação.
-   [✔] **Interface Web:** Interface de usuário reativa construída com Thymeleaf e estilizada com Bootstrap 5.
-   **Infraestrutura como Código:**
    -   Banco de dados MySQL containerizado com Docker.
    -   Migrations de banco de dados gerenciadas pelo Flyway.
    -   Dockerfile multi-estágio para uma imagem de produção otimizada.
    -   Orquestração completa da aplicação e do banco com Docker Compose.
-   [✔] **CI/CD:** Workflow de Integração Contínua com GitHub Actions para garantir a qualidade do código a cada commit.

## 🛠️ Tecnologias Utilizadas

A tabela abaixo lista as principais tecnologias e frameworks usados na construção do projeto:

| Categoria | Tecnologia |
| :--- | :--- |
| **Backend** | ![Java](https://img.shields.io/badge/Java-21-blue?logo=openjdk) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.1-blue?logo=spring) ![Spring Security](https://img.shields.io/badge/Spring_Security-6-blue?logo=spring) ![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-blue?logo=spring) |
| **Frontend** | ![Thymeleaf](https://img.shields.io/badge/Thymeleaf-green?logo=leaf) ![Bootstrap](https://img.shields.io/badge/Bootstrap-5-purple?logo=bootstrap) |
| **Banco de Dados** | ![MySQL](https://img.shields.io/badge/MySQL-8.0-orange?logo=mysql) ![Flyway](https://img.shields.io/badge/Flyway-red?logo=flyway) |
| **Build & Ferramentas** | ![Maven](https://img.shields.io/badge/Maven-4-red?logo=apachemaven) ![Lombok](https://img.shields.io/badge/Lombok-purple) |
| **DevOps & Infra** | ![Docker](https://img.shields.io/badge/Docker-blue?logo=docker) ![GitHub Actions](https://img.shields.io/badge/GitHub_Actions-black?logo=githubactions) |

## 🗺️ Diagrama de Classes

```mermaid
classDiagram
    class AbstractEntity {
        +Long id
    }
    class AbstractFullEntity {
        +LocalDateTime createdAt
        +LocalDateTime updatedAt
    }
    AbstractFullEntity --|> AbstractEntity

    class User {
        +String name
        +String username
        +String password
    }
    User --|> AbstractFullEntity

    class Task {
        +String title
        +String description
        +TaskPriority priority
        +TaskStatus status
        +LocalDate dueDate
    }
    Task --|> AbstractFullEntity

    User "1" *-- "0..*" Task : gerencia

    class TaskController {
        -TaskService taskService
        -UserService userService
        +showTaskBoard()
        +showCreateTaskForm()
        +createTask()
        +deleteTask()
    }

    class TaskService {
        -TaskRepository taskRepository
        +findTasksByUserId(Long)
        +createTask(Task, User)
        +deleteTask(Long)
    }

    class TaskRepository {
        <<Interface>>
        +findByUserId(Long)
    }

    TaskController ..> TaskService
    TaskService ..> TaskRepository
    TaskController ..> UserService

    class UserController {
        -UserService userService
        +showRegistrationForm()
        +registerUser()
    }

    class UserService {
        -UserRepository userRepository
        +findByUsername(String)
        +createUser(User)
    }

    class UserRepository {
        <<Interface>>
        +findByUsername(String)
    }

    UserController ..> UserService
    UserService ..> UserRepository
```
## 💾 Diagrama de Entidade-Relacionamento (ERD)

O diagrama abaixo representa a estrutura do banco de dados da aplicação, incluindo as tabelas `users` e `tasks` e o relacionamento entre elas.

```mermaid
erDiagram
    users {
        BIGINT id PK
        VARCHAR(255) name
        VARCHAR(20) user_name UK
        VARCHAR(255) password
        DATETIME created_at
        DATETIME updated_at
    }

    tasks {
        BIGINT id PK
        VARCHAR(255) title
        TEXT description
        VARCHAR(20) priority
        VARCHAR(20) status
        DATE due_date
        BIGINT user_id FK
        DATETIME created_at
        DATETIME updated_at
    }

    users ||--|{ tasks : "possui"
```

## 🏁 Como Executar

Existem duas maneiras de executar este projeto: em modo de desenvolvimento (via IDE) ou como uma aplicação containerizada (via Docker Compose).

### 1. Modo de Desenvolvimento (IDE + Docker)

**Pré-requisitos:**
* JDK 21
* Maven
* Docker Desktop ou CLI

**Passos:**
1.  Clone o repositório: `git clone https://github.com/dougluciano2/bootcamp-dio-santander-lista-tarefas-java.git`
2.  Abra o projeto na sua IDE de preferência (IntelliJ, VS Code, etc.).
3.  Garanta que o Docker Desktop esteja em execução.
4.  Execute a classe principal `BoadtaskjavaApplication.java`.

A dependência `spring-boot-docker-compose` irá detectar o arquivo `compose.dev.yml` e iniciará automaticamente o contêiner do banco de dados MySQL para a aplicação. A aplicação estará acessível em `http://localhost:8080`.

### 2. Modo Standalone (Docker Compose)
Este modo simula um ambiente de "produção", empacotando a aplicação em uma imagem Docker e orquestrando tudo com um único comando.

**Pré-requisitos:**
* Docker e Docker Compose

**Passos:**
1.  Clone o repositório.
2.  Abra um terminal na raiz do projeto.
3.  Execute o seguinte comando:
    ```bash
    docker compose up --build
    ```
4.  Aguarde o Docker construir a imagem da aplicação e iniciar os contêineres do app e do banco de dados.
5.  Acesse a aplicação no seu navegador em **[http://localhost:8080](http://localhost:8080)**.

Para parar a aplicação, pressione `Ctrl + C` no terminal e execute `docker compose down`.
