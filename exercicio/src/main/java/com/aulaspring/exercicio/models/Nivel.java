package com.aulaspring.exercicio.models;

public enum Nivel {
    NORMAL(0, "Normal"),
    PRIORIDADE(1, "Prioridade"),
    URGENCIA(2, "Urgência");

    private final Integer valor;
    private final String descricao;

    Nivel(Integer valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public Integer getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }
}
