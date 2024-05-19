package com.school.sample.JVclass;

public class Cliente extends pessoa{
    private int numContribuinte;

    public Cliente(int Id,String nome,int telefone,int numContribuinte){
        super(Id,nome,telefone);
        this.numContribuinte = numContribuinte;
    }

    public int getNumContribuinte() {
        return numContribuinte;
    }

    public void setNumContribuinte(int numContribuinte) {
        this.numContribuinte = numContribuinte;
    }
}