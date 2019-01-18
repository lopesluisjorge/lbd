package br.edu.ifma.dcomp.lbd.laboratorio04.model;

public enum StatusEmprestimo {

    ATIVO("Ativo"),
    PAGO("Pago");

    private String status;

    private StatusEmprestimo(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
