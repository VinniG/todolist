# Projeto Prático - Curso Java Rocketseat

Este reposiório tem como objetivo guardar o conteúdo das aulas práticas do curso de Java produzido pela Rockeseat, ministrado pela professora Daniele Leão.

## :question: Sobre o projeto

O projeto é uma aplicação Back-End produzida em Java, que simula um To-Do List, com funções para cadastrar usuários, cadastrar tarefas, consultar tarefas cadastradas e alterar tarefas

## :hammer: Ferramentas Utilizadas

* Java 17;
* Maven;
* SpringBoot;
* API Dog (Requisições HTTP).
* Dependências (adicionadas através do `pom.xml`): `SpringBoot Dev Tools`, `Lombok`, `H2Database` e `BCrypt`.

## :point_up: Como funciona?

A aplicação é um To-Do List comandado por requisições HTTP, `GET`, `POST` e `PUT` através do API Dog com os dados sendo adicionados via JSON.

Ex:

```JSON
{
    "name": "Vinicius Gomes",
    "username": "gomesvini",
    "password": "12345"
}
```

Quando o usuário é cadastrado, os dados irão para um Entidade (Tabela) que ficará armazenada na H2 Database, acessando ela podemos realizar consultas SQL:

```SQL
SELECT * FROM TB_USERS
```

Através do API Dog também pode cadastrar tarefas, autenticando o usuário na aba de `AUTH`.

É possível também através de um método GET, consultar todas as tarefas cadastradas pelo usuário (cuja autenticação esteja no `AUTH`).

Utilizando um método `PUT` podemos realizar a alteração de alguma tarefa.

## :computer: Utilizando o API Dog
---
