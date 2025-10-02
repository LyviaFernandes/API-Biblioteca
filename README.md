
# 📚 API REST - Gerenciamento de Biblioteca

Este projeto consiste em uma **API RESTful** desenvolvida em **Spring Boot**, com integração ao banco de dados **MySQL** (via XAMPP).
O objetivo é fornecer uma forma automatizada de gerenciar o acervo de livros de uma biblioteca local, permitindo o **cadastro, listagem, atualização, exclusão, empréstimo e devolução** de livros.

---

## 🛠️ Tecnologias Utilizadas

* **Java 17+**
* **Spring Boot**
* **Spring Web**
* **Spring Data JPA**
* **MySQL Driver**
* **Spring Boot DevTools**
* **Lombok**
* **IntelliJ IDEA**
* **XAMPP (MySQL)**
* **Postman (para testes)**

---

## 🗄️ Configuração do Banco de Dados

O banco utilizado é o **MySQL** rodando pelo **XAMPP**.

### Script de criação do banco:

```sql
CREATE DATABASE biblioteca;

USE biblioteca;

CREATE TABLE livro (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    genero VARCHAR(100),
    isbn VARCHAR(20) UNIQUE NOT NULL,
    ano_publicacao INT,
    emprestado BOOLEAN DEFAULT FALSE
);
```

---

## ⚙️ Configuração do `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/biblioteca
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.application.name=api-biblioteca
server.port=8080
```

> Ajuste `username` e `password` conforme seu MySQL no XAMPP.

---

## 📂 Estrutura do Projeto

```
src/main/java/com/lyvia/biblioteca
    ├── BibliotecaApplication.java   # Classe principal
    ├── model
    │     └── Livro.java             # Entidade Livro
    ├── repository
    │     └── LivroRepository.java   # Interface JPA
    ├── service
    │     └── LivroService.java      # Regras de negócio
    └── controller
          └── LivroController.java   # Endpoints REST
```

---

## 🔑 Endpoints da API

### 📌 Livros

* **Listar todos os livros**

  ```
  GET /livros
  ```

* **Buscar livro por ID**

  ```
  GET /livros/{id}
  ```

* **Cadastrar livro**

  ```
  POST /livros
  ```

  **Exemplo JSON:**

  ```json
  {
    "titulo": "Dom Casmurro",
    "autor": "Machado de Assis",
    "genero": "Romance",
    "isbn": "1234567890",
    "anoPublicacao": 1899
  }
  ```

* **Atualizar livro**

  ```
  PUT /livros/{id}
  ```

  **Exemplo JSON:**

  ```json
  {
    "titulo": "Dom Casmurro - Edição Revisada",
    "autor": "Machado de Assis",
    "genero": "Romance",
    "isbn": "1234567890",
    "anoPublicacao": 1900
  }
  ```

* **Excluir livro**

  ```
  DELETE /livros/{id}
  ```

---

### 📌 Empréstimos

* **Emprestar livro**

  ```
  POST /livros/{id}/emprestimo
  ```

* **Devolver livro**

  ```
  POST /livros/{id}/devolucao
  ```

---

## ▶️ Como Executar o Projeto

1. Clonar o repositório:

   ```bash
   git clone https://github.com/LyviaFernandes/API-Biblioteca
   ```

2. Abrir no **IntelliJ**.

3. Configurar o banco no **XAMPP** (MySQL).

4. Rodar a classe principal:

   ```bash
   BibliotecaApplication
   ```

5. A API estará disponível em:

   ```
   http://localhost:8080
   ```

---

## 📮 Testando com Postman

* **GET** → `http://localhost:8080/livros`
* **GET** → `http://localhost:8080/livros/1`
* **POST** → `http://localhost:8080/livros` (com JSON no body)
* **PUT** → `http://localhost:8080/livros/1` (com JSON no body)
* **DELETE** → `http://localhost:8080/livros/1`
* **POST** → `http://localhost:8080/livros/1/emprestimo`
* **POST** → `http://localhost:8080/livros/1/devolucao`

---

## 🚀 Melhorias Futuras

* Registro de clientes para associar aos empréstimos.
* Relatórios de livros mais emprestados.
* Autenticação (JWT) para controle de acesso.
* Integração com front-end (React, Angular ou Vue).

---

✍️ Desenvolvido por **Lyvia**

---
