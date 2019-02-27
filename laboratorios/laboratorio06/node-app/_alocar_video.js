const mongo = require('mongodb')

const MongoClient = mongo.MongoClient

const dsn = 'mongodb://127.0.0.1:27017'

MongoClient.connect(dsn, (error, client) => {
  if (error) {
    console.error(error)
  }

  const database = client.db('laboratorio06')

  const customers = database.collection('clientes')

  const customer = customers.find({ cpf: '27267644015' })

  const videos = database.collection('videos')

  const videoUm = videos.find({ id: 1 })
  const videoDois = videos.find({ id: 2 })

  const dataUpdate = {
    emprestimos: [
      {
        data_locacao: new Date(),
        videos: [
          videoUm,
          videoDois
        ]
      }
    ]
  }

  console.log(dataUpdate)

  customers.update(
    { _id: customer._id },
    { '$set': dataUpdate }
  )

  client.close()
})

JSON.parse(JSON.stringify())
