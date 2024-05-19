package com.school.sample.connection;
import com.school.sample.JVclass.Cliente;
import com.school.sample.JVclass.Produtos;
import com.school.sample.JVclass.Settings;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.sql.*;

public class ProdutosDB {
    public static ObservableList<Produtos> listaProduto() {
        Connection conn = MyConnection.openDB();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ObservableList<Produtos> produtos = Settings.getListaProduto();
        String sql = "SELECT * FROM lojadb.produto;";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int Id = rs.getInt("idProduto");
                String nome = rs.getString("nomeProduto");
                BigDecimal preco = rs.getBigDecimal("preco");
                int qtd = rs.getInt("qtd");
                Produtos p = new Produtos(Id, nome, preco, qtd);
                produtos.add(p);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar os Produtos: " + ex.getMessage());
        } finally {
            MyConnection.closeDB();
        }
        return produtos;
    }
    public static int getUltimoIdProduto() {
        Connection conn = MyConnection.openDB();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int ultimoId = 0;
        String sql = "SELECT MAX(idProduto) AS ultimoId FROM produto";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                ultimoId = rs.getInt("ultimoId");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao obter o último ID do Produto: " + ex.getMessage());
        } finally {
            MyConnection.closeDB();
        }
        return ultimoId;
    }
    public static void adicionar(String nomeProduto, BigDecimal preco, int qtd){
        Connection conn = MyConnection.openDB();
        PreparedStatement stmt = null;
        try {
            // Obter o último ID e incrementá-lo
            int ultimoId = getUltimoIdProduto();
            int novoId = ultimoId + 1;

            // Inserir o novo cliente com o ID gerado
            String sql = "INSERT INTO produto (idProduto, nomeProduto, preco, qtd) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, novoId);
            stmt.setString(2, nomeProduto);
            stmt.setBigDecimal(3, preco);
            stmt.setInt(4, qtd);
            stmt.executeUpdate();

            System.out.println("Produto adicionado com ID: " + novoId);
        } catch (SQLException ex) {
            System.out.println("Erro ao adicionar o Produto: " + ex.getMessage());
        } finally {
            MyConnection.closeDB();
        }
    }
    public static void remover(int idProduto) {
        Connection conn = MyConnection.openDB();
        PreparedStatement stmt = null;

        String sql = "DELETE FROM lojadb.produto WHERE idProduto = ?;";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idProduto);
            stmt.executeUpdate();
            System.out.println("Produto removido com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao remover o Produto: " + ex.getMessage());
        } finally {
            MyConnection.closeDB();
        }
    }
}

