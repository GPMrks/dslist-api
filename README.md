# DSList - Gestão de Games
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/devsuperior/sds1-wmazoni/blob/master/LICENSE) 

# Sobre o projeto

https://dslist-api-production-94b4.up.railway.app/swagger-ui/index.html

DSList é uma aplicação back-end web construída durante o **Intensivão Java Spring**, evento organizado pela [DevSuperior](https://devsuperior.com "Site da DevSuperior").

A aplicação consiste em uma REST API para gestão de games, onde games podem ser criados, consultados, atualizados e deletados, bem como ser inseridos em listas categorizadas e ordenados nestas listas.

## Modelo de Domínio

![Modelo de domínio DSList](src/main/resources/static/dslist-model.png)

# Tecnologias utilizadas
## Back end
- Java 17
- Spring Boot 3.1.0
- JPA / Hibernate
- H2 Database
- PostgreSQL
- Maven
- Swagger
- Model Mapper
- Docker

## Implantação em Produção
- Back end: Railway
- Banco de dados: PostgreSQL on Railway

# Como executar o projeto

## Back end
Pré-requisitos: 
- Java 17
- Maven

```bash
# clonar repositório
git clone git@github.com:GPMrks/dslist-api.git

# entrar na pasta do projeto back end
cd dslist-api

# executar o projeto
./mvnw spring-boot:run
```

# Autor

Guilherme Pereira Marques

https://www.linkedin.com/in/guilherme-p-marques