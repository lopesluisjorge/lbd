package edu.ifma.dcomp.laboratorio03.modelo;

import java.util.ArrayList;

final public class Cliente {

    private Integer id;
    private String nome;
    private String cpf;
    private String endereco;
    private String telefone;

    private ArrayList<Cliente> clientes = new ArrayList<>();

    public Cliente(String nome, String cpf, String endereco, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void adiciona(Cliente cliente) {
        clientes.add(cliente);
    }

}
