#!/bin/bash

psql -U postgres -w -d locadora_db -a -f migracoes/pgsql/01__criar_tabela_filmes.sql
psql -U postgres -w -d locadora_db -a -f migracoes/pgsql/02__criar_tabela_clientes.sql
psql -U postgres -w -d locadora_db -a -f migracoes/pgsql/03__criar_tabela_videos.sql
psql -U postgres -w -d locadora_db -a -f migracoes/pgsql/04__criar_tabela_emprestimos.sql
psql -U postgres -w -d locadora_db -a -f migracoes/pgsql/05__criar_tabela_pivo_emprestimo_video.sql
