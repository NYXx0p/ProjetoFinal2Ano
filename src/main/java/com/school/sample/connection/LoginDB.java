package com.school.sample.connection;

import com.school.sample.JVclass.Cliente;
import com.school.sample.JVclass.Login;
import com.school.sample.JVclass.Settings;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDB {
    public static ObservableList<Login> listalogin() {
        Connection conn = MyConnection.openDB();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ObservableList<Login> logins = Settings.getListaLogin();
        String sql = "SELECT * FROM lojadb.login;";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int Id = rs.getInt("idLogin");
                String Conta_login = rs.getString("Conta_Login");
                String pass = rs.getString("Pass");
                Login l = new Login(Id, Conta_login, pass);
                logins.add(l);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar os logins: " + ex.getMessage());
        } finally {
            MyConnection.closeDB();
        }
        return logins;
    }
    public static int getUltimoIdCliente() {
        Connection conn = MyConnection.openDB();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int ultimoId = 0;
        String sql = "SELECT MAX(idLogin) AS ultimoId FROM login";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                ultimoId = rs.getInt("ultimoId");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao obter o último ID do Login: " + ex.getMessage());
        } finally {
            MyConnection.closeDB();
        }
        return ultimoId;
    }



    public static void adicionar(String Conta_Login, String Pass) {
        Connection conn = MyConnection.openDB();
        PreparedStatement stmt = null;
        try {
            // Obter o último ID e incrementá-lo
            int ultimoId = getUltimoIdCliente();
            int novoId = ultimoId + 1;

            // Inserir o novo cliente com o ID gerado
            String sql = "INSERT INTO login (idLogin,Conta_Login,Pass) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, novoId);
            stmt.setString(2, Conta_Login);
            stmt.setString(3, Pass);
            stmt.executeUpdate();

            System.out.println("Conta adicionado com ID: " + novoId);
        } catch (SQLException ex) {
            System.out.println("Erro ao adicionar o Conta: " + ex.getMessage());
        } finally {
            MyConnection.closeDB();
        }
    }
}
