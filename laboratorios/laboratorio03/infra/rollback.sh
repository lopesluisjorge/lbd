#!/bin/bash

psql -U postgres -w -d locadora_db -a -f migracoes/pgsql/rollback.sql
