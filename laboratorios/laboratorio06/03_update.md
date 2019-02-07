# Atualizar registros

Atualizar o endereco de Jorge

``` javascript
db.clientes.update(
    { "nome": "Jorge" },
    {
        $set: {
            "endereco": {
                "logradoro": "Rua São Sebastião",
                "numero": 67,
                "bairro": "Anil",
                "cep": 65000000,
                "cidade": "São Luis",
                "estado": "MA"
            }
        }
    }
)
```

Fazer Locação de Vídeo

``` javascript
db.clientes.update(
    { "cpf": "27267644015" },
    { 
        "$set": {
            "emprestimos": [
                {
                    "data_locacao": new Date(),
                    "videos": [
                        JSON.parse(JSON.stringify(db.videos.find({ id: 1 }))),
                        JSON.parse(JSON.stringify(db.videos.find({ id: 2 })))
                    ]
                }
            ]
        }
    }
)
```

Para remover campo $unset

Para renomear campo $rename
