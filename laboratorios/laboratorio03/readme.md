# Laboratório 03

Laboratório 03 de Laboratório de Banco de dados

## Pré requisitos

- maven
- postgres
- java8

## Setup

Foi utilizado para desenvolvimento o banco de dados postgres, juntamente com o docker

### Ambiente Docker. 

- Instalação: [CliqueAqui](https://docs.docker.com/install/) e siga as instruções.

Inicie seu ambiente docker, e siga as instruções:

- Para iniciar o banco de dados postgres.
```bash
docker compose up -d
```

- Para Testes manuais:

Realizar a migração e população da base de dados

```bash
docker compose exec pgsql /bin/bash
./rollback && ./migracao-pgsql.sql && populacao.sql
 ```

- Para Testes automatizados:

 ```bash
 docker compose exec pgsql /bin/bash
 ./rollback && ./migracao-pgsql.sql
  ```

### Ambiente com postgres nativo

Executar os arquivos correspondentes à ação desejada (migração, rollback ou população) disponiveis no pacote `br.edu.ifma.dcomp.laboratorio03.infra` do projeto