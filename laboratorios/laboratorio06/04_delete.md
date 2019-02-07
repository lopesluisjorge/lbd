# Deletando um documento

Procurando **Beltrano** por cpf e verificando o tamanho da coleção `clientes`

``` javascript
db.clientes.find({ "cpf": "25684669018" }).pretty()

db.clientes.count()
```

Removendo o **Beltrano** da coleção `clientes`

``` javascript
db.clientes.remove({ "cpf": "25684669018" })
```

Verifiacando se **Beltrano** foi removido

``` javascript
db.clientes.find({ "cpf": "25684669018" }).pretty()

db.clientes.count()
```
