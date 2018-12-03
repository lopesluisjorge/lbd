package br.edu.ifma.dcomp.laboratorio03.construcao;

import br.edu.ifma.dcomp.laboratorio03.modelo.Cliente;

final public class ConstrutorCliente {

    public static Cliente constroi(String nome, String cpf, String endereco, String telefone) {
        return new Cliente(nome, cpf, endereco, telefone);
    }

    public static Cliente constroi(String nome, String cpf, String endereco, String telefone, int id) {
        final Cliente cliente = constroi(nome, cpf, endereco, telefone);
        cliente.setId(id);

        return cliente;
    }

}
