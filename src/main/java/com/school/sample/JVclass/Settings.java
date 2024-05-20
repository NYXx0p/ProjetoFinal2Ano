package com.school.sample.JVclass;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class Settings {
    private static Stage primaryStage;
    private static ObservableList<Cliente> listaCliente = FXCollections.observableArrayList();
    private static ObservableList<Fornecedor> listaFornecedor = FXCollections.observableArrayList();
    private static ObservableList<Produtos> listaProduto = FXCollections.observableArrayList();

    private static ObservableList<Login> listaLogin = FXCollections.observableArrayList();
    private static Cliente editarCliente;
    private static Fornecedor editarFornecedor;

    private static Produtos editarProduto;


    public static void setPrimaryStage(Stage primaryStage) {
        Settings.primaryStage = primaryStage;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    // Métodos para obter as listas
    public static ObservableList<Cliente> getListaCliente() {
        return listaCliente;
    }

    public static ObservableList<Fornecedor> getListaFornecedor() {
        return listaFornecedor;
    }

    public static ObservableList<Produtos> getListaProduto() {
        return listaProduto;
    }

    public static ObservableList<Login> getListaLogin(){return listaLogin;}


    // Métodos para obter os objetos de edição
    public static Cliente getEditarCliente() {
        return editarCliente;
    }

    public static Fornecedor getEditarFornecedor() {return editarFornecedor;}
    public static Produtos getEditarProduto() {return editarProduto;}

    // Métodos para definir os objetos de edição
    public static void setEditarCliente(Cliente editarCliente) {
        Settings.editarCliente = editarCliente;
    }

    public static void setEditarFornecedor(Fornecedor editarFornecedor) {
        Settings.editarFornecedor = editarFornecedor;
    }
    public static void setEditarProduto(Produtos editarProduto) {
        Settings.editarProduto = editarProduto;
    }

    // Métodos para definir as listas
    public static void setListaCliente(ObservableList<Cliente> listaCliente) {
        Settings.listaCliente = listaCliente;
    }

    public static void setListaFornecedor(ObservableList<Fornecedor> listaFornecedor) {
        Settings.listaFornecedor = listaFornecedor;
    }
    public static void setListaProduto(ObservableList<Produtos> listaProduto) {
        Settings.listaProduto = listaProduto;
    }
    public static void setListaLogin(ObservableList<Login> listaLogin) {
        Settings.listaLogin = listaLogin;
    }
}

