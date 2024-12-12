# Lista de Tarefas - Backend

Este é um projeto backend para gerenciamento de tarefas. Ele permite criar, listar, atualizar, excluir e buscar tarefas com base em sua prioridade ou status de realização. A aplicação foi desenvolvida utilizando **Spring Boot** e utiliza um banco de dados relacional para armazenar as informações das tarefas.

## Funcionalidades

- **Criar Tarefas**: Permite ao usuário criar novas tarefas, definindo seu nome, descrição, prioridade e status de conclusão.
- **Listar Tarefas**: Exibe todas as tarefas cadastradas, com suas respectivas informações.
- **Atualizar Tarefas**: Permite atualizar os dados de uma tarefa, incluindo o nome, descrição, prioridade e o status de realização.
- **Deletar Tarefas**: Permite excluir uma tarefa específica com base no seu ID.
- **Buscar Tarefas por Prioridade**: Permite filtrar tarefas por prioridade (alta, média ou baixa).
- **Buscar Tarefas por Status**: Permite filtrar tarefas que já foram realizadas ou não.

## Endpoints da API

A aplicação expõe os seguintes endpoints para gerenciar as tarefas:

### 1. **GET /tarefas**

Retorna todas as tarefas.

### 2. **POST /tarefas**

Cria uma nova tarefa.

### 3. **PUT /tarefas/{id}**

Atualiza uma tarefa existente

### 4. **DELETE /tarefas/{id}**

Deleta uma tarefa pelo ID.

### 5. **GET /tarefas/search/prioridade**

Busca tarefas por prioridade

### 6. **GET /tarefas/search/realizado**

Busca tarefas por status de realização.

## Dependências

O projeto usa as seguintes dependências:

- **Spring Boot**: Para desenvolvimento rápido de aplicações Java.
- **Spring Data JPA**: Para persistência de dados utilizando o JPA (Java Persistence API).
- **Spring Web**: Para criação da API RESTful.
- **Spring Security**: Para autenticação e configuração de segurança.

