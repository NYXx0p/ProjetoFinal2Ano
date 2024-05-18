package com.school.sample;

public class pessoa {
    private int Id;
    private String nome;
    private int telemovel;

    public pessoa(int Id, String nome, int telemovel){
        this.Id = Id;
        this.nome = nome;
        this.telemovel = telemovel;
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

    public int getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(int telemovel) {
        this.telemovel = telemovel;
    }
}
