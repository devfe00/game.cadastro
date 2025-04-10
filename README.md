# UOL Host - Desafio de Programação

Este projeto implementa um sistema de cadastro de jogadores com codinomes baseados em duas listas: "Os Vingadores" e "A Liga da Justiça".

## Tecnologias Utilizadas

- Java 17
- Spring Boot 2.7.0
- Spring Data JPA
- Thymeleaf
- H2 Database (banco de dados em memória)
- Maven
- Bootstrap 5

## Funcionalidades

- Cadastro de jogadores com nome, e-mail e telefone
- Atribuição automática de codinomes de acordo com a lista escolhida
- Verificação de disponibilidade de codinomes
- Relatório de jogadores cadastrados com seus respectivos codinomes
- Persistência dos dados em banco H2 em memória

## Como Executar

### Pré-requisitos
- JDK 11 ou superior
- Maven 3.6 ou superior

### Passos para execução

1. Clone o repositório
```bash
git clone https://github.com/seu-usuario/uol-challenge.git
cd uol-challenge
```

2. Compile e execute o projeto com Maven
```bash
mvn spring-boot:run
```

3. Acesse a aplicação no navegador
```
http://localhost:8080
```

4. Para acessar o console do banco de dados H2
```
http://localhost:8080/h2-console
```
Use as credenciais configuradas em application.properties (JDBC URL: jdbc:h2:mem:uolhostdb, User: sa, Password: password)

## Estrutura do Projeto

```
uol-challenge/
├── pom.xml                  # Configuração do Maven
├── src/
│   ├── main/
│   │   ├── java/            # Código fonte Java
│   │   │   └── com/
│   │   │       └── uolhost/
│   │   │           └── challenge/
│   │   │               ├── Application.java          # Classe principal
│   │   │               ├── controller/               # Controladores REST
│   │   │               ├── model/                    # Entidades do modelo
│   │   │               ├── repository/               # Repositórios de dados
│   │   │               └── service/                  # Serviços de negócio
│   │   └── resources/       # Recursos
│   │       ├── application.properties  # Configurações da aplicação
│   │       ├── static/                 # Arquivos estáticos (CSS, JS)
│   │       └── templates/              # Templates HTML (Thymeleaf)
│   └── test/                # Testes unitários
```

## Como o sistema funciona

1. O usuário preenche o formulário de cadastro com nome, e-mail, telefone e escolhe uma lista de codinomes
2. O sistema verifica se há codinomes disponíveis na lista escolhida
3. Se houver, o sistema atribui automaticamente um codinome ao jogador e salva no banco de dados
4. Se não houver, o sistema exibe uma mensagem de erro informando que não há codinomes disponíveis
5. O usuário pode visualizar todos os jogadores cadastrados no relatório