# Laboratório 06

## NoSQL com MongoDB

Estrutura de documento json completo utilizado prática do laboratório:

``` javascript
{
    "nome": "Fulano",
    "cpf": "70498541401",
    "endereco": {
        "logradouro": "Cummings Vista",
        "numero": 8888,
        "complemento": "Apt. 101",
        "bairro": "Vila Cafeteira",
        "cidade": "São Luís",
        "estado": "MA",
        "cep": 65000000
    },
    "telefone": "(98) 988887777",
    "ativo": true,
    "emprestimos": [
        {
            "data_locacao": new Date(2018, 8, 23),
            "data_devolucao": new Date(2018, 8, 25),
            "videos": [
                {
                    "id": 1,
                    "tipo": "DVD",
                    "valor_diaria": 1.99,
                    "filme": {
                        "titulo": "Até que a Sorte nos Separe",
                        "ano_lancamento": 2012,
                        "duracao": 90,
                        "genero": "Comédia",
                        "atores": [
                            "Leandro Hassum",
                            "Danielle Winits",
                            "Kiko Mascarenhas",
                            "Rita Elmôr",
                            "Rodrigo Sant' Anna"
                        ],
                        "diretor": "Roberto Santucci"
                    }
                }
            ]
        }
    ]
}
```

## Operações de CRUD

para usar o banco de dados `laboratorio06`:

``` bash
mongo laboratorio06
```

Caso o banco não exista o mongo irá criá-lo.

### Criação de documento

[Criação de dados](01_insert.md)

### Listagem e busca de documento

[Listagem de dados](02_find.md)

### Edição de documento

[Atualização de dados](03_update.md)

### Deleção de documento

[Remoção de documentos](04_delete.md)
