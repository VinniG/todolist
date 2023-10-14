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

A aplicação é um To-Do List comandado por requisições HTTP, `GET`, `POST` e `PUT` através do API Dog com os dados sendo adicionados via JSON

Ex:

```JSON
{
    "name": "Vinicius Gomes",
    "username": "gomesvini",
    "password": "12345"
}
```

---
