package com.school.sample.JVclass;

public class Fornecedor extends pessoa{
    private String morada;
    private String email;
    public Fornecedor(int Id,String nome,int telefone,String morada,String email){
        super(Id,nome,telefone);
        this.morada = morada;
        this.email = email;
    }
    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
