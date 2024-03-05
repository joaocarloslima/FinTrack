# FinTrack

API do Projeto FinTrack - Gestão de despesas pessoais

## Requisitos

- [ ] CRUD de Categorias
- [ ] CRUD de Movimentações
- [ ] CRUD de Usuários
- [ ] Autenticação
- [ ] Dashboard

## Documentação da API

### Endpoints 

- [Listar Categorias](#listar-categorias)
- [Cadastrar Categoria](#cadastrar-categoria)
- [Detalhar Categoria](#detalhar-categoria)
- [Apagar Categoria](#apagar-categoria)
- [Atualizar Categoria](#atualizar-categoria)


### Listar Categorias

`GET` /categoria

Retorna um array com as categorias do usuário autenticado.

#### Exemplo de Resposta

```js
[
    {
        "id": 1,
        "nome": "Alimentação",
        "icone": "fast-food"
    }
]
```

#### Códigos de Status

|código|descrição
|------|---------
|200| Lista de categorias retornada com sucesso
|401| Não autenticado. Se autentique em /login

---

### Cadastrar Categoria

`POST` /categoria

Cadastra uma categoria com os dados enviados no corpo da requisição

#### Corpo da Requisição

|campo|tipo|obrigatório|descrição
|-----|----|:-----------:|---------
|nome|string|✅|Um nome curto para a categoria
|icone|string|❌|O nome do ícone conforme Material Icons

#### Exemplo de Requisição
```js
// POST /categoria
{
    "nome": "Alimentação"
}
```

#### Exemplo de Resposta
```js
{
    "id": 1
    "nome": "Alimentação",
    "icone": "fast-food"
}
```

#### Códigos de Status

|código|descrição
|------|---------
|201| Categoria criada com sucesso
|400| Validação falhou. Verifique o corpo da requisição
|401| Não autenticado. Se autentique em /login

---

### Detalhar Categoria

`GET` /categoria/`{id}`

Retorna os detalhes da categoria com o `id` informado no path.

#### Exemplo de Resposta
```js
// GET /categoria/1
{
    "id": 1
    "nome": "Alimentação",
    "icone": "fast-food"
}
```

#### Códigos de Status

|código|descrição
|------|---------
|200| Categoria retornada com sucesso
|401| Não autenticado. Se autentique em /login
|403| Não autorizado. Essa categoria não pertence ao usuário autenticado
|404| Não existe categoria com o `id` informado
---

### Apagar Categoria

`DELETE` /categoria/`{id}`

Apaga a categoria com o `id` informado no path

#### Códigos de Status

|código|descrição
|------|---------
|204| Categoria apagado com sucesso
|401| Não autenticado. Se autentique em /login
|403| Não autorizado. Essa categoria não pertence ao usuário autenticado
|404| Não existe categoria com o `id` informado
---

### Atualizar Categoria

`PUT` /categoria/`{id}`

Atualiza os dados da categoria com o `id` informado no path, utilizando as informações do corpo da requisição


#### Corpo da Requisição

|campo|tipo|obrigatório|descrição
|-----|----|:-----------:|---------
|nome|string|✅|O novo nome curto para a categoria
|icone|string|✅|O novo nome do ícone conforme Material Icons

#### Exemplo de Requisição
```js
// PUT /categoria/1
{
    "nome": "Restaurante",
    "icone": "fast-food"
}
```

#### Exemplo de Resposta
```js
{
    "id": 1
    "nome": "Restaurante",
    "icone": "fast-food"
}
```

#### Códigos de Status

|código|descrição
|------|---------
|200| Categoria atualizada com sucesso
|400| Validação falhou. Verifique o corpo da requisição
|401| Não autenticado. Se autentique em /login
|403| Não autorizado. Essa categoria não pertence ao usuário autenticado
|404| Não existe categoria com o `id` informado
---
