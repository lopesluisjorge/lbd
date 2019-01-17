package br.edu.ifma.dcomp.lbd.laboratorio04.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(length = 64, nullable = false)
    private String nome;


    @Column(length = 11, nullable = false, unique = true)
    private String cpf;


    @Column(length = 20)
    private String telefone;


    @Column(columnDefinition = "smallint")
    private Boolean ativo;


    @OneToMany(mappedBy = "cliente")
    final private List<Emprestimo> emprestimos = new ArrayList<>();


    @Embedded
    private EnderecoCliente enderecoCliente;


    @PrePersist
    private void prePersist() {
        ativar();
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public EnderecoCliente getEndereco() {
        return enderecoCliente;
    }

    public void setEndereco(EnderecoCliente enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public void ativar() {
        ativo = true;
    }

    public void desativar() {
        ativo = false;
    }

    public void adicionar(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
