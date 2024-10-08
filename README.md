# API de autenticação

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)

Este projeto é uma API construída utilizando **Java, Java Spring, Flyway Migrations, PostgresSQL como base de dados, e Spring Security e JWT para controlo de autenticação.**

## Índice

- [Instalação](#instalação)
- [Utilização](#Utilização)
- [API Endpoints](#api-endpoints)
- [Authentication](#authentication)
- [Database](#database)


## Instalação

1. Clonar o repositório:

```bash
git clone https://github.com/Lostednft/auth-api-security.git
```

2. Instalar as dependências com o Maven

3. Instalar o [PostgresSQL](https://www.postgresql.org/)

## Utilização

1. Iniciar a aplicação com Maven
2. A API estará acessível em http://localhost:8080


## API Endpoints
A API fornece as seguintes endpoints:

```markdown
GET /product - Recupera uma lista de todos os produtos. (todos os utilizadores autenticados).

POST /product - Registar um novo produto (é necessário acesso ADMIN).

POST /auth/login - Iniciar sessão na aplicação.

POST /auth/register - Registar um novo usuário na aplicação Autenticação.
```

## Authentication
A API utiliza o Spring Security para o controlo da autenticação. Estão disponíveis as seguintes funções:

```
USER -> Função de utilizador padrão para utilizadores com sessão iniciada.
ADMIN -> Função de administrador para gerir parceiros (registo de novos parceiros).
```
Para acessar endpoint protegidos é só como um utilizador ADMIN, forneça as credenciais de autenticação adequadas no cabeçalho do pedido.

## Database
O projeto utiliza o [PostgresSQL](https://www.postgresql.org/) como banco de dados. As migrações necessárias da base de dados são geridas utilizando o Flyway.

