# Pequisar registros

Listar todos os `clientes`

``` javascript
db.clientes.find()
```

Deixando mais bonitinho

``` javascript
db.clientes.find().pretty()
```

Somente o primeiro

``` javascript
db.clientes.findOne()
```

Pesquisando elemento

``` js
db.clientes.find({ "nome": "Fulano" }).pretty()
```

Exibir campos específicos

``` js
db.clientes.find({ "nome": "Fulano" }, { "nome": true, "endereco": true }).pretty()
```
