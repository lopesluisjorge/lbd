# Adicionar registros

Para adicionar um único documento à coleção `clientes`:

``` js
db.clientes.insert({
    "nome": "Jorge",
    "cpf": "27267644015",
    "endereco": "Rua São Sebastião, 67, Anil, 65000000 São Luis, MA",
    "telefone": "(98) 988777777",
    "ativo": true,
})
```

Para adicionar uma lista de documentos à coleção `clientes`:

``` js
db.clientes.insert([
    {
        "nome": "Fulano",
        "cpf": "31079087036",
        "endereco": {
            "logradouro": "Rua 3",
            "numero": 20,
            "bairro": "Matões Turu",
            "cidade": "São Luís",
            "estado": "MA",
            "cep": 65000000
        },
        "telefone": "(98) 988777777",
        "ativo": true,
    },
    {
        "nome": "Ciclano",
        "cpf": "75443910000",
        "telefone": "(98) 988778877",
        "ativo": true,
    },
    {
        "nome": "Beltrano",
        "cpf": "25684669018",
        "endereco": {
            "logradouro": "Rua ABCD",
            "numero": 20,
            "bairro": "Alemanha",
            "cidade": "São Luís",
            "estado": "MA",
            "cep": 65000000
        },
        "telefone": "(98) 988777799",
        "ativo": false,
    }
])
```

Inserir cliente com vários emprestimos.

``` javascript
db.clientes.insert({
    "nome": "Fulano Júnior",
    "cpf": "25684669218",
    "endereco": {
        "logradouro": "Rua L",
        "numero": 77,
        "bairro": "Centro",
        "cidade": "São Luís",
        "estado": "MA",
        "cep": 65000000
    },
    "telefone": "(98) 988777799",
    "ativo": true,
    "emprestimos": [
        {
            "data_locacao": new Date(2018, 7, 22),
            "data_devolucao": new Date(2018, 7, 25),
            "videos": [
                {
                    "id": 1,
                    "tipo": "DVD",
                    "valor_diaria": 1.99,
                    "filme": {
                        "titulo": "Até que a Sorte nos Separe"
                    }
                }
            ]
        },
        {
            "data_locacao": new Date(2018, 9, 10),
            "data_devolucao": new Date(2018, 9, 13),
            "videos": [
                {
                    "id": 2,
                    "tipo": "VHS",
                    "valor_diaria": 3.99,
                    "filme": {
                        "titulo": "O Poderoso Chefão"
                    }
                },
                {
                    "id": 4,
                    "tipo": "DVD",
                    "valor_diaria": 2.99,
                    "filme": {
                        "titulo": "O Exterminador do Futuro"
                    }
                }
            ]
        },
        {
            "data_locacao": new Date(2018, 10, 2),
            "data_devolucao": new Date(2018, 10, 4),
            "videos": [
                {
                    "id": 3,
                    "tipo": "DVD",
                    "valor_diaria": 2.95,
                    "filme": {
                        "titulo": "Até que a Sorte nos Separe II"
                    }
                }
            ]
        }
    ]
})
```
