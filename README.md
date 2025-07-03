# Avaliação Prática – Java Backend com Persistência de Dados

Este projeto é uma aplicação de console interativa para gerenciar pessoas, funcionários e projetos de uma empresa. A aplicação utiliza Java puro com JDBC para todas as operações de persistência de dados com um banco de dados MySQL.

## Requisitos

* Java 17 ou superior
* Maven 3.8 ou superior
* MySQL 8 ou superior

## 1. Configuração do Banco de Dados

Antes de executar a aplicação, o banco de dados precisa ser configurado.

1.  Certifique-se de que o seu serviço MySQL está em execução.
2.  Use um cliente de banco de dados (como HeidiSQL, DBeaver, etc.) para executar o script SQL localizado em:
    arquivo 'sql.sql'
3.  Este script irá criar o banco de dados `empresa`, o usuário `devuser` e todas as tabelas necessárias, deixando-as prontas e vazias para a aplicação.
4.  As credenciais padrão usadas pela aplicação são:
    * **Usuário:** `devuser`
    * **Senha:** `devpass123`

    Caso tenha alterado essas credenciais no script, lembre-se de atualizá-las também no arquivo `src/main/java/br/com/empresa/util/ConnectionFactory.java`.

## 2. Como Executar o Projeto

1.  Clone este repositório para a sua máquina local.
2.  Abra o projeto na sua IDE favorita (recomendado: IntelliJ IDEA) como um projeto Maven. A IDE irá baixar as dependências automaticamente.
3.  Para iniciar a aplicação, localize a classe principal:
    `src/main/java/br/com/empresa/main/Main.java`
4.  Execute o método `main()` nesta classe.
5.  O menu interativo da aplicação aparecerá no console, pronto para receber seus comandos.
