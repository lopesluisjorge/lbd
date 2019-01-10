package br.edu.ifma.dcomp.lbd.laboratorio04.test.builder;

import br.edu.ifma.dcomp.lbd.laboratorio04.model.Cliente;

public class ClienteBuilder {

    final private Cliente cliente = new Cliente();

    public ClienteBuilder comNome(String nome) {
        cliente.setNome(nome);
        return this;
    }

    public ClienteBuilder comCpf(String cpf) {
        cliente.setCpf(cpf);
        return this;
    }

    public ClienteBuilder comEndereco(String endereco) {
        cliente.setEndereco(endereco);
        return this;
    }

    public ClienteBuilder comTelefone(String telefone) {
        cliente.setTelefone(telefone);
        return this;
    }

    public Cliente build() {
        if (null == cliente.getNome()) cliente.setNome("Jon");
        if (null == cliente.getCpf()) cliente.setCpf("12345678910");
        if (null == cliente.getEndereco()) cliente.setEndereco("R. S. Antonio, 67");
        if (null == cliente.getTelefone()) cliente.setTelefone("(98) 988887777");

        return cliente;
    }

}
