package br.edu.ifma.dcomp.lbd.laboratorio04.model;

import javax.persistence.*;

@Embeddable
public class EnderecoCliente {

    @Column(name = "endereco_cep", length = 8, nullable = false)
    private String cep;

    @Lob
    @Column(name = "endereco_logradouro", nullable = false)
    private String logradouro;

    @Column(name = "endereco_numero", columnDefinition = "smallint", nullable = false)
    private Integer numero;

    @Column(name = "endereco_bairro", nullable = false)
    private String bairro;

    @Column(name = "endereco_cidade", nullable = false)
    private String cidade;

    @Column(name = "endereco_uf", length = 2, nullable = false)
    private String uf;

    @Lob
    @Column(name = "endereco_complemento")
    private String complemento;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

}
