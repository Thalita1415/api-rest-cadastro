# API REST para Controle de Cadastro de Profissionais e Contatos

Este é um projeto de API REST desenvolvido em Java para o controle de cadastro de profissionais e seus respectivos números de contato. A API oferece endpoints para operações CRUD (Create, Read, Update, Delete) tanto para profissionais quanto para contatos.

## Schema do Banco de Dados

A API utiliza dois modelos de dados: profissionais e contatos.

### Tabela `contatos`

- **nome:** Varchar
    - Exemplo: fixo casa, celular, escritório
- **contato:** Varchar
- **created_date:** Date
- **profissional:** Chave estrangeira com a tabela profissional

### Tabela `profissionais`

- **nome:** Varchar
- **cargo:** Varchar
    - Valores possíveis: Desenvolvedor, Designer, Suporte, Tester
- **nascimento:** Date
- **created_date:** Date

## Endpoints

### Contatos

- **GET /contatos**
    - Lista de contatos com base nos critérios definidos em Params
    - Params
        - q: String (Filtro para buscar contatos que contenham o texto em qualquer um de seus atributos)
        - fields: List<String> (Opcional. Quando presente, apenas os campos listados em fields serão retornados)

- **GET /contatos/:id**
    - Retorna todos os dados do contato que possui o ID passado na URL

- **POST /contatos**
    - Insere no banco de dados os dados do contato enviados via body
    - Body
        - Content-type: Json
    - Response
        - Sucesso: Contato com id {ID} cadastrado

- **PUT /contatos/:id**
    - Atualiza os dados do contato que possui o ID passado via URL com os dados enviados no Body
    - Body
        - Content-type: Json
    - Response
        - Sucesso: Cadastro alterado

- **DELETE /contatos/:id**
    - Exclui logicamente o contato que possui o ID passado via URL
    - Response
        - Sucesso: Contato excluído

### Profissionais

- **GET /profissionais**
    - Lista de profissionais com base nos critérios definidos em Params
    - Params
        - q: String (Filtro para buscar profissionais que contenham o texto em qualquer um de seus atributos)
        - fields: List<String> (Opcional. Quando presente, apenas os campos listados em fields serão retornados)

- **GET /profissionais/:id**
    - Retorna todos os dados do profissional que possui o ID passado na URL

- **POST /profissionais**
    - Insere no banco de dados os dados do profissional enviados via body
    - Body
        - Content-type: Json
    - Response
        - Sucesso: Profissional com id {ID} cadastrado

- **PUT /profissionais/:id**
    - Atualiza os dados do profissional que possui o ID passado via URL com os dados enviados no Body
    - Body
        - Content-type: Json
    - Response
        - Sucesso: Cadastro alterado

- **DELETE /profissionais/:id**
    - Exclui logicamente o profissional que possui o ID passado via URL
    - Response
        - Sucesso: Profissional excluído

**Importante!** O método DELETE para profissionais realiza uma exclusão lógica do registro.

## Instruções de Uso

1. Faça uma solicitação HTTP para os endpoints conforme necessário, utilizando as operações GET, POST, PUT e DELETE conforme a ação desejada.
2. Forneça os dados necessários no corpo da solicitação em formato JSON, quando aplicável.
3. Utilize os parâmetros de consulta (query parameters) para filtrar os resultados, quando necessário.

## Requisitos

- Java
- Framework Spring Boot
- Banco de dados compatível com o Hibernate

## Autor

Este projeto foi desenvolvido por [Thalita Gonçalves].
