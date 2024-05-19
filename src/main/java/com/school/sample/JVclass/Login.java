package com.school.sample.JVclass;

public class Login {
    private String Conta_login;
    private String pass;
    private int guessId;

    public Login (String Conta_login,String pass,int guessId){
        this.Conta_login = Conta_login;
        this.pass = pass;
        this.guessId = guessId;
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

    public int getGuessId() {
        return guessId;
    }

    public void setGuessId(int guessId) {
        this.guessId = guessId;
    }
}
