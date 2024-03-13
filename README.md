# API de Controle de Cadastro de Profissionais e Contatos

## Schema do Banco de Dados

### Tabela `contacts`

Campos:
- `name`: Varchar
  - Ex.: fixo casa, celular, escritório
- `contact`: Varchar
- `created_date`: Date
- `professional`: Chave estrangeira com a tabela profissional

### Tabela `professional`

Campos:
- `name`: Varchar
- `office`: Varchar
  - Valores possíveis: Desenvolvedor, Designer, Suporte, Tester
- `birth`: Date
- `created_date`: Date

Mapeamentos:
- A tabela de contacts deve possuir um mapeamento N para 1 com a tabela professional.

  `contacts N -> 1 professionals`

## Endpoints

### Contacts

- **GET** `/contacts`
  - **Response:** Lista de contatos com base nos critérios definidos em Params
  - **Params:**
    - `q` - String: Filtro para buscar contatos que contenham o texto em qualquer um de seus atributos
    - `fields` - List<String>: Opcional. Quando presente, apenas os campos listados em fields deverão ser retornados

- **GET** `/contacts/:id`
  - **Response:** Todos os dados do contato que possui o ID passado na URL

- **POST** `/contacts`
  - **Body:** Content-type: Json
  - **Response:** Sucesso contato com id {ID} cadastrado

- **PUT** `/contacts/:id`
  - **Body:** Content-type: Json
  - **Response:** Sucesso cadastrado alterado

- **DELETE** `/contacts/:id`
  - **Response:** Sucesso contato excluído

### Profissionais

- **GET** `/professionals`
  - **Response:** Lista de profissionais com base nos critérios definidos em Params
  - **Params:**
    - `q` - String: Filtro para buscar profissionais que contenham o texto em qualquer um de seus atributos
    - `fields` - List<String>: Opcional. Quando presente, apenas os campos listados em fields deverão ser retornados

- **GET** `/professionals/:id`
  - **Response:** Todos os dados do profissional que possui o ID passado na URL

- **POST** `/professionals`
  - **Body:** Content-type: Json
  - **Response:** Sucesso profissional com id {ID} cadastrado

- **PUT** `/professionals/:id`
  - **Body:** Content-type: Json
  - **Response:** Sucesso cadastrado alterado

- **DELETE** `/professionals/:id`
  - **Response:** Sucesso contato excluído (Importante! Este método deve realizar uma exclusão lógica do registro)


### Autor
Este projeto foi desenvolvido por [Thalita Gonçalves].

