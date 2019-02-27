#!/usr/bin/env node

const mongo = require('mongodb')

const MongoClient = mongo.MongoClient

const dsn = 'mongodb://127.0.0.1:27017'

const inserir = (nome, cpf, endereco, telefone) => {
  MongoClient.connect(dsn, (error, client) => {
    if (error) {
      console.error(error)
    }

    const banco = client.db('lab-06')

    const clientes = banco.collection('clientes')

    clientes.insertOne({
      'nome': nome,
      'cpf': cpf,
      'endereco': endereco,
      'telefone': telefone
    })
  })
}

inserir('Jon', 12345678910, 'Rua A, 11, Turu, São Luis, MA', '(98) 988885577')
inserir('James', 12345678911, 'Rua B, 36, Turu, São Luis, MA', '(98) 988885588')
inserir('Antônio', 12345678912, 'Rua C, 67, Turu, São Luis, MA', '(98) 988885533')
