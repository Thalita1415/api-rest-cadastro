# API de Controle de Cadastro de Profissionais e Contatos

## Schema do Banco de Dados

### Tabela `contatos`

Campos:
- `nome`: Varchar
  - Ex.: fixo casa, celular, escritório
- `contato`: Varchar
- `created_date`: Date
- `profissional`: Chave estrangeira com a tabela profissional

### Tabela `profissionais`

Campos:
- `nome`: Varchar
- `cargo`: Varchar
  - Valores possíveis: Desenvolvedor, Designer, Suporte, Tester
- `nascimento`: Date
- `created_date`: Date

Mapeamentos:
- A tabela de contatos deve possuir um mapeamento N para 1 com a tabela profissionais.

  `contatos N -> 1 profissionais`

## Endpoints

### Contacts

- **GET** `/contatos`
  - **Response:** Lista de contatos com base nos critérios definidos em Params
  - **Params:**
    - `q` - String: Filtro para buscar contatos que contenham o texto em qualquer um de seus atributos
    - `fields` - List<String>: Opcional. Quando presente, apenas os campos listados em fields deverão ser retornados

- **GET** `/contatos/:id`
  - **Response:** Todos os dados do contato que possui o ID passado na URL

- **POST** `/contatos`
  - **Body:** Content-type: Json
  - **Response:** Sucesso contato com id {ID} cadastrado

- **PUT** `/contatos/:id`
  - **Body:** Content-type: Json
  - **Response:** Sucesso cadastrado alterado

- **DELETE** `/contatos/:id`
  - **Response:** Sucesso contato excluído

### Profissionais

- **GET** `/profissionais`
  - **Response:** Lista de profissionais com base nos critérios definidos em Params
  - **Params:**
    - `q` - String: Filtro para buscar profissionais que contenham o texto em qualquer um de seus atributos
    - `fields` - List<String>: Opcional. Quando presente, apenas os campos listados em fields deverão ser retornados

- **GET** `/profissionais/:id`
  - **Response:** Todos os dados do profissional que possui o ID passado na URL

- **POST** `/profissionais`
  - **Body:** Content-type: Json
  - **Response:** Sucesso profissional com id {ID} cadastrado

- **PUT** `/profissionais/:id`
  - **Body:** Content-type: Json
  - **Response:** Sucesso cadastrado alterado

- **DELETE** `/profissionais/:id`
  - **Response:** Sucesso contato excluído (Importante! Este método deve realizar uma exclusão lógica do registro)
