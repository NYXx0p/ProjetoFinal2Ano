package com.school.sample.connection;

import com.school.sample.JVclass.Cliente;
import com.school.sample.JVclass.Fornecedor;
import com.school.sample.JVclass.Settings;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

public class FornecedorDB {
    public static ObservableList<Fornecedor> listaFornecedor() {
        Connection conn = MyConnection.openDB();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ObservableList<Fornecedor> fornecedores = Settings.getListaFornecedor();
        String sql = "SELECT * FROM lojadb.fornecedor;";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int Id = rs.getInt("idFornecedor");
                String nome = rs.getString("nomeFornecedor");
                String morada = rs.getString("moradaFornecedor");
                int telefone = rs.getInt("telefoneFornecedor");
                String email = rs.getString("emailFornecedor");
                Fornecedor f = new Fornecedor(Id, nome,morada,telefone, email);
                fornecedores.add(f);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar os Fornecedores: " + ex.getMessage());
        } finally {
            MyConnection.closeDB();
        }
        return fornecedores;
    }
    public static int getUltimoIdFornecedor() {
        Connection conn = MyConnection.openDB();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int ultimoId = 0;
        String sql = "SELECT MAX(idFornecedor) AS ultimoId FROM fornecedor";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                ultimoId = rs.getInt("ultimoId");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao obter o último ID do Fornecedor: " + ex.getMessage());
        } finally {
            MyConnection.closeDB();
        }
        return ultimoId;
    }
    public static void adicionar(String nomeFornecedor, String moradaFornecedor, int telefone, String email) {
        Connection conn = MyConnection.openDB();
        PreparedStatement stmt = null;
        try {
            // Obter o último ID e incrementá-lo
            int ultimoId = getUltimoIdFornecedor();
            int novoId = ultimoId + 1;

            // Inserir o novo Fornecedor com o ID gerado
            String sql = "INSERT INTO fornecedor (idFornecedor, nomeFornecedor, moradaFornecedor, telefoneFornecedor,emailFornecedor) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, novoId);
            stmt.setString(2, nomeFornecedor);
            stmt.setString(3, moradaFornecedor);
            stmt.setInt(4, telefone);
            stmt.setString(5, email);
            stmt.executeUpdate();

            System.out.println("Fornecedor adicionado com ID: " + novoId);
        } catch (SQLException ex) {
            System.out.println("Erro ao adicionar o Fornecedor: " + ex.getMessage());
        } finally {
            MyConnection.closeDB();
        }
    }
    public static void remover(int idFornecedor){
        Connection conn = MyConnection.openDB();
        PreparedStatement stmt = null;

        String sql = "DELETE FROM lojadb.fornecedor WHERE idFornecedor = ?;";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idFornecedor);
            stmt.executeUpdate();
            System.out.println("Fornecedor adicionado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao remover o Fornecedor: " + ex.getMessage());
        } finally {
            MyConnection.closeDB();
        }
    }
}
