package com.school.sample.JVclass;

public class pessoa {
    private int Id;
    private String nome;
    private int telefone;

    public pessoa(int Id, String nome, int telefone){
        this.Id = Id;
        this.nome = nome;
        this.telefone = telefone;
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

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }
}
