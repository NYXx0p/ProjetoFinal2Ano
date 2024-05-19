package com.school.sample.JVclass;

import java.math.BigDecimal;

public class Produtos {
    private int Id;
    private String nome;
    private BigDecimal preco;

    private int qtd;
    public Produtos (int Id,String nome,BigDecimal preco,int qtd){
        this.Id = Id;
        this.nome = nome;
        this.preco = preco;
        this.qtd = qtd;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
}
