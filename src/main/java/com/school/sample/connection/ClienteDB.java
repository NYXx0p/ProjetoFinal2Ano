package com.school.sample.connection;

import com.school.sample.JVclass.Cliente;
import com.school.sample.JVclass.Settings;
import javafx.collections.ObservableList;

import java.sql.*;

public class ClienteDB {
    public static ObservableList<Cliente> listaCliente() {
        Connection conn = MyConnection.openDB();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ObservableList<Cliente> clientes = Settings.getListaCliente();
        String sql = "SELECT * FROM lojadb.cliente;";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int Id = rs.getInt("idCliente");
                String nome = rs.getString("nomeCliente");
                int telefone = rs.getInt("telefone");
                int numContribuinte = rs.getInt("numContribuinte");
                Cliente c = new Cliente(Id, nome, telefone, numContribuinte);
                clientes.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar os clientes: " + ex.getMessage());
        } finally {
            MyConnection.closeDB();
        }
        return clientes;
    }
    public static int getUltimoIdCliente() {
        Connection conn = MyConnection.openDB();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int ultimoId = 0;
        String sql = "SELECT MAX(idCliente) AS ultimoId FROM cliente";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                ultimoId = rs.getInt("ultimoId");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao obter o último ID do cliente: " + ex.getMessage());
        } finally {
            MyConnection.closeDB();
        }
        return ultimoId;
    }



    public static void adicionar(String nomeCliente, int numContribuinte, int telefone) {
        Connection conn = MyConnection.openDB();
        PreparedStatement stmt = null;
        try {
            // Obter o último ID e incrementá-lo
            int ultimoId = getUltimoIdCliente();
            int novoId = ultimoId + 1;

            // Inserir o novo cliente com o ID gerado
            String sql = "INSERT INTO cliente (idCliente, nomeCliente, numContribuinte, telefone) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, novoId);
            stmt.setString(2, nomeCliente);
            stmt.setInt(3, numContribuinte);
            stmt.setInt(4, telefone);
            stmt.executeUpdate();

            System.out.println("Cliente adicionado com ID: " + novoId);
        } catch (SQLException ex) {
            System.out.println("Erro ao adicionar o cliente: " + ex.getMessage());
        } finally {
            MyConnection.closeDB();
        }
    }


    public static void remover(int idCliente) {
        Connection conn = MyConnection.openDB();
        PreparedStatement stmt = null;

        String sql = "DELETE FROM lojadb.cliente WHERE idCliente = ?;";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            stmt.executeUpdate();
            System.out.println("Cliente removido com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao remover o cliente: " + ex.getMessage());
        } finally {
            MyConnection.closeDB();
        }
    }
}
