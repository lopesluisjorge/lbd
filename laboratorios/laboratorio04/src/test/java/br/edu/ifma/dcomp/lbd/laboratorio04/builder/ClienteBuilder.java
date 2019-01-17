package br.edu.ifma.dcomp.lbd.laboratorio04.builder;

import br.edu.ifma.dcomp.lbd.laboratorio04.model.Cliente;
import br.edu.ifma.dcomp.lbd.laboratorio04.model.EnderecoCliente;

public class ClienteBuilder {

    final private Cliente cliente = new Cliente();

    private ClienteBuilder() {}

    public static ClienteBuilder umcliente() {
        return new ClienteBuilder();
    }

    public static EnderecoCliente umEndereco() {
        final EnderecoCliente endereco = new EnderecoCliente();

        endereco.setCep("65000000");
        endereco.setCidade("São Luis");
        endereco.setUf("MA");
        endereco.setLogradouro("Rua A");
        endereco.setBairro("Bairro de Fárima");
        endereco.setNumero(12);

        return endereco;
    }

    public ClienteBuilder comNome(String nome) {
        cliente.setNome(nome);
        return this;
    }

    public ClienteBuilder comCpf(String cpf) {
        cliente.setCpf(cpf);
        return this;
    }

    public ClienteBuilder comEndereco(EnderecoCliente endereco) {
        cliente.setEndereco(endereco);
        return this;
    }

    public ClienteBuilder comTelefone(String telefone) {
        cliente.setTelefone(telefone);
        return this;
    }

    public Cliente constroi() {
        if (null == cliente.getNome()) cliente.setNome("Jon");
        if (null == cliente.getCpf()) cliente.setCpf("12345678910");
        if (null == cliente.getEndereco()) cliente.setEndereco(umEndereco());
        if (null == cliente.getTelefone()) cliente.setTelefone("(98) 988887777");

        return cliente;
    }

}
