package com.school.sample.JVclass;

public class Login {
    private int Id_login;
    private String Conta_login;
    private String pass;

    public Login (int Id_login,String Conta_login,String pass){
        this.Id_login = Id_login;
        this.Conta_login = Conta_login;
        this.pass = pass;
    }

    public String getConta_login() {
        return Conta_login;
    }

    public void setConta_login(String conta_login) {
        Conta_login = conta_login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    public int getId_login() {
        return Id_login;
    }

    public void setId_login(int Id_login) {
        this.Id_login = Id_login;
    }
}
