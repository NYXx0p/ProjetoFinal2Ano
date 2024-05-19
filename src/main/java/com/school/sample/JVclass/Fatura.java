package com.school.sample.JVclass;

import java.time.LocalDate;
import java.time.LocalTime;

public class Fatura {
    private int Id;
    private double total;
    private int qtd_comp;
    private LocalDate data;
    private LocalTime hora;

    public Fatura(int Id,double total,int qtd_comp,LocalDate data, LocalTime hora){
        this.Id = Id;
        this.total = total;
        this.qtd_comp = qtd_comp;
        this.data = data;
        this.hora = hora;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getQtd_comp() {
        return qtd_comp;
    }

    public void setQtd_comp(int qtd_comp) {
        this.qtd_comp = qtd_comp;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
}
