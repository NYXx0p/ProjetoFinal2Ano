package com.school.sample.controller;
import com.school.sample.JVclass.Cliente;
import com.school.sample.JVclass.Fornecedor;
import com.school.sample.JVclass.Produtos;
import com.school.sample.JVclass.Settings;
import com.school.sample.connection.ClienteDB;
import com.school.sample.connection.FornecedorDB;
import com.school.sample.connection.MyConnection;
import com.school.sample.connection.ProdutosDB;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Optional;
import java.util.ResourceBundle;


public class SampleController implements Initializable {
    @FXML
    private TableView <Fornecedor> TableListaFornecedor;
    @FXML
    private TableColumn <Fornecedor,Integer> FornecedorID;
    @FXML
    private TableColumn <Fornecedor,String> FornecedorNome;
    @FXML
    private TableColumn <Fornecedor,String> FornecedorMorada;
    @FXML
    private TableColumn <Fornecedor,Integer> FornecedorTelefone;
    @FXML
    private TableColumn <Fornecedor,String> FornecedorEmail;
    @FXML
    private TextField txtIdFornecedor;
    @FXML
    private TextField txtNomeFornecedor;
    @FXML
    private TextField txtMoradaFornecedor;
    @FXML
    private TextField txtEmailFornecedor;
    @FXML
    private TextField txtTelefoneFornecedor;
    @FXML
    private TableColumn <Produtos,Integer> ProdutoID;
    @FXML
    private TableColumn <Produtos,String> ProdutoNome;
    @FXML
    private TableColumn <Produtos, BigDecimal>ProdutoPreco;
    @FXML
    private TableColumn <Produtos,Integer>ProdutoQuantidade;
    @FXML
    private TableView <Produtos> TableListaProduto;
    @FXML
    private TextField txtIdProduto;
    @FXML
    private TextField txtNomeProduto;
    @FXML
    private TextField txtPrecoProduto;
    @FXML
    private TextField txtQtdProduto;
    @FXML
    private AnchorPane MenuTela;
    @FXML
    private TextField txtIdCliente;
    @FXML
    private TextField txtContribuinteCliente;
    @FXML
    private TextField txtNomeCliente;
    @FXML
    private TextField txtTelemovelCliente;
    @FXML
    private AnchorPane ClientesTela;
    @FXML
    private AnchorPane FornecedoresTela;
    @FXML
    private AnchorPane SobreMimTela;
    @FXML
    private TableView<Cliente> TableListaCliente;
    @FXML
    private TableColumn<Cliente, Integer> ClienteID;
    @FXML
    private TableColumn<Cliente, String> ClienteNome;
    @FXML
    private TableColumn<Cliente, Integer> ClienteContribuinte;
    @FXML
    private TableColumn<Cliente, Integer> ClienteTelemovel;
    @FXML
    private GridPane produtos_grid;

    @FXML
    private AnchorPane HomeTela;

    @FXML
    private AnchorPane ProdutosTela;

    @FXML
    public void ProdutosButton(ActionEvent actionEvent) {
        HomeTela.setVisible(false);
        ProdutosTela.setVisible(true);
        MenuTela.setVisible(false);
        ClientesTela.setVisible(false);
        FornecedoresTela.setVisible(false);
        SobreMimTela.setVisible(false);
    }
    @FXML
    void ClientesButton(ActionEvent event) {
        HomeTela.setVisible(false);
        ProdutosTela.setVisible(false);
        ClientesTela.setVisible(true);
        MenuTela.setVisible(false);
        FornecedoresTela.setVisible(false);
        SobreMimTela.setVisible(false);
    }

    @FXML
    void FornecedoresButton(ActionEvent event) {
        HomeTela.setVisible(false);
        ProdutosTela.setVisible(false);
        MenuTela.setVisible(false);
        ClientesTela.setVisible(false);
        FornecedoresTela.setVisible(true);
        SobreMimTela.setVisible(false);
    }

    @FXML
    void HomeButton(ActionEvent event) {
        HomeTela.setVisible(true);
        ProdutosTela.setVisible(false);
        MenuTela.setVisible(false);
        ClientesTela.setVisible(false);
        FornecedoresTela.setVisible(false);
        SobreMimTela.setVisible(false);
    }

    @FXML
    void MenuButton(ActionEvent event) {
        HomeTela.setVisible(false);
        MenuTela.setVisible(true);
        ProdutosTela.setVisible(false);
        ClientesTela.setVisible(false);
        FornecedoresTela.setVisible(false);
        SobreMimTela.setVisible(false);
    }


    @FXML
    void SobreButton(ActionEvent event) {
        HomeTela.setVisible(false);
        ProdutosTela.setVisible(false);
        MenuTela.setVisible(false);
        ClientesTela.setVisible(false);
        FornecedoresTela.setVisible(false);
        SobreMimTela.setVisible(true);
    }

    public void TableListaCliente() {
        ClienteID.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("Id"));
        ClienteNome.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
        ClienteContribuinte.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("numContribuinte"));
        ClienteTelemovel.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("telefone"));
        TableListaCliente.setItems(ClienteDB.listaCliente());
        TableListaCliente.refresh();
    }


    public void verCliente(MouseEvent mouseEvent) {
        Cliente clienteVer = TableListaCliente.getSelectionModel().getSelectedItem();
        if(clienteVer != null){
            txtIdCliente.setText(String.valueOf(clienteVer.getId()));
            txtNomeCliente.setText(clienteVer.getNome());
            txtContribuinteCliente.setText(String.valueOf(clienteVer.getNumContribuinte()));
            txtTelemovelCliente.setText(String.valueOf(clienteVer.getTelefone()));}
        else{
            System.out.println("Nenhum cliente foi selecionado.");
        }
    }

    public void btnAdicionarCliente(ActionEvent actionEvent) {
        if (txtNomeCliente.getText().isEmpty() || txtContribuinteCliente.getText().isEmpty() || txtTelemovelCliente.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText(null);
            alert.setContentText("Preencha todos os espaços em branco");
            alert.showAndWait();
        } else {
            try {
                Connection conn = MyConnection.openDB();
                if (conn != null) {
                    String nome = String.valueOf(txtNomeCliente.getText());
                    int numContribuinte = Integer.parseInt(String.valueOf(txtContribuinteCliente.getText()));
                    int telefone = Integer.parseInt(String.valueOf(txtTelemovelCliente.getText()));
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("CONFIRMAÇÃO");
                    alert.setHeaderText("Deseja mesmo adicionar?");
                    alert.setContentText("Nome: " + nome + "\nContribuinte: " + numContribuinte + "\nTelemovel" + telefone);
                    ButtonType btnSim = new ButtonType("SIM");
                    ButtonType btnNao = new ButtonType("NÃO");
                    alert.getButtonTypes().setAll(btnSim, btnNao);
                    txtIdCliente.clear();

                    Optional<ButtonType> escolha = alert.showAndWait();
                    if (escolha.isPresent() && escolha.get() == btnSim) {
                        TableListaCliente.getItems().clear();
                        ClienteDB.adicionar(nome, numContribuinte, telefone);
                        Cliente clint = (Cliente) TableListaCliente.getSelectionModel().getSelectedItem();
                        ObservableList<Cliente> clientes = ClienteDB.listaCliente();
                        clientes.add(clint);
                        TableListaCliente.setItems(clientes);
                        TableListaCliente.refresh();
                        txtIdCliente.clear();
                        txtNomeCliente.clear();
                        txtContribuinteCliente.clear();
                        txtTelemovelCliente.clear();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Nao foi possivel estabelecer uma conexão com a base de dados");
                    alert.setContentText(null);
                    alert.showAndWait();
                }
            } catch (Exception e) {
                Alert alertErro = new Alert(Alert.AlertType.ERROR);
                alertErro.setTitle("ERRO");
                alertErro.setHeaderText("Erro ao adicionar o Cliente: " + e.getMessage());
                alertErro.showAndWait();
            } finally {
                MyConnection.closeDB();
            }
        }
    }

    public void btnEditarCliente(ActionEvent actionEvent) {
        Alert alert = null;
        if (txtIdCliente.getText().isEmpty() || txtNomeCliente.getText().isEmpty() || txtContribuinteCliente.getText().isEmpty() || txtTelemovelCliente.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText(null);
            alert.setContentText("Preencha todos os espaços em branco");
            alert.showAndWait();
        } else {
            int novoId = Integer.parseInt(txtIdCliente.getText());
            Cliente ClienteEdit = null;
            for (Cliente c : Settings.getListaCliente()) {
                if (c.getId() == novoId) {
                    ClienteEdit = c;
                    break;
                }
            }
            if (ClienteEdit != null) {
                ClienteEdit.setNome((String) txtNomeCliente.getText());
                ClienteEdit.setNumContribuinte(Integer.parseInt(String.valueOf(txtContribuinteCliente.getText())));
                ClienteEdit.setTelefone(Integer.parseInt(txtTelemovelCliente.getText()));
                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                alert1.setTitle("CONFIRMAÇÃO");
                alert1.setHeaderText("Deseja mesmo editar "+"\nID: " +txtIdCliente.getText() + "\nNome: " + txtNomeCliente.getText() + "\nTelemóvel:" + txtTelemovelCliente.getText() + "\nContribuinte: " + txtContribuinteCliente.getText());
                alert1.setContentText(null);
                ButtonType btnSim = new ButtonType("SIM");
                ButtonType btnNao = new ButtonType("NÃO");
                alert1.getButtonTypes().setAll(btnSim, btnNao);

                Optional<ButtonType> escolha = alert1.showAndWait();
                if (escolha.isPresent() && escolha.get() == btnSim) {
                    Connection conn = null;
                    try {
                        conn = MyConnection.openDB();
                        if (conn != null) {
                            String Atualizar = "UPDATE cliente SET nomeCliente = ?, numContribuinte = ?, telefone = ? WHERE idCliente = ?;";
                            PreparedStatement stmt = conn.prepareStatement(Atualizar);
                            stmt.setString(1, txtNomeCliente.getText());
                            stmt.setString(2, txtContribuinteCliente.getText());
                            stmt.setString(3, txtTelemovelCliente.getText());
                            stmt.setInt(4, novoId);
                            int atualizarBD = stmt.executeUpdate();
                            if (atualizarBD > 0) {
                                for (Cliente c : Settings.getListaCliente()) {
                                    if (c.getId() == ClienteEdit.getId()) {
                                        int clint = Settings.getListaCliente().indexOf(c);
                                        Settings.getListaCliente().set(clint, ClienteEdit);
                                        break;
                                    }
                                }

                                Alert alertAddFun = new Alert(Alert.AlertType.CONFIRMATION);
                                alertAddFun.setTitle("Informação");
                                alertAddFun.setHeaderText("Cliente Editado com sucesso");
                                alertAddFun.setContentText(null);
                                alertAddFun.showAndWait();
                                Settings.setEditarCliente(null);

                                TableListaCliente.refresh();
                                txtIdCliente.clear();
                                txtNomeCliente.clear();
                                txtContribuinteCliente.clear();
                                txtTelemovelCliente.clear();
                            } else {
                                Alert alertEdit = new Alert(Alert.AlertType.ERROR);
                                alertEdit.setTitle("ERRO!");
                                alertEdit.setHeaderText("Nao foi possivel atualizar o Cliente na base de dados");
                                alertEdit.setContentText(null);
                                alertEdit.showAndWait();
                            }
                        } else {
                            Alert alertEdit = new Alert(Alert.AlertType.ERROR);
                            alertEdit.setTitle("ERRO!");
                            alertEdit.setHeaderText("Não foi possível estabelecer uma conexão com a base de dados.");
                            alertEdit.setContentText(null);
                            alertEdit.showAndWait();
                        }
                    } catch (Exception e) {
                        Alert alertErro = new Alert(Alert.AlertType.ERROR);
                        alertErro.setTitle("ERRO");
                        alertErro.setHeaderText("Erro ao Editar o Cliente: " + e.getMessage());
                        alertErro.showAndWait();
                    } finally {
                        if (conn != null) {
                            try {
                                conn.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }
    public void btnEliminarCliente(ActionEvent actionEvent) {
        if (txtIdCliente.getText().isEmpty()
                || txtNomeCliente.getText().isEmpty()
                || txtContribuinteCliente.getText().isEmpty()
                || txtTelemovelCliente.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Selecione algum Cliente da tabela");
            alert.showAndWait();}
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminar");
            alert.setHeaderText("Deseja mesmo Eliminar?"+"\n"+"ID: " +txtIdCliente.getText() + "\n" + "Nome: " + txtNomeCliente.getText() + "\n" + "Contribuinte: " + txtContribuinteCliente.getText() + "\n" + "Telemóvel: " + txtTelemovelCliente.getText());
            ButtonType botaoSim = new ButtonType("Sim");
            ButtonType botaoNao = new ButtonType("Não");
            alert.getButtonTypes().setAll(botaoSim, botaoNao);
            Optional<ButtonType> choose = alert.showAndWait();
            if (choose.get() == botaoSim) {
                int novoId = Integer.parseInt(txtIdCliente.getText());
                for (Cliente c : Settings.getListaCliente()) {
                    if (c.getId() == novoId) {
                        Settings.getListaCliente().remove(c);
                        ClienteDB.remover(novoId);
                        txtIdCliente.setText("");
                        txtNomeCliente.setText("");
                        txtContribuinteCliente.setText("");
                        txtTelemovelCliente.setText("");
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Information");
                        alert1.setHeaderText(null);
                        alert1.setContentText("O Cliente foi Eliminado");
                        alert1.showAndWait();
                        break;
                    }
                }
            }
            else{
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Information");
                alert2.setHeaderText(null);
                alert2.setContentText("Cancelado com Sucesso");
                alert2.showAndWait();

            }
        }
    }

    public void TableListaProduto(){
        ProdutoID.setCellValueFactory(new PropertyValueFactory<Produtos, Integer>("Id"));
        ProdutoNome.setCellValueFactory(new PropertyValueFactory<Produtos, String>("nome"));
        ProdutoPreco.setCellValueFactory(new PropertyValueFactory<Produtos, BigDecimal>("preco"));
        ProdutoQuantidade.setCellValueFactory(new PropertyValueFactory<Produtos, Integer>("qtd"));
        TableListaProduto.setItems(ProdutosDB.listaProduto());
        TableListaProduto.refresh();
    }
    public void verProduto(MouseEvent mouseEvent) {
        Produtos produtosVer = (Produtos) TableListaProduto.getSelectionModel().getSelectedItem();
        if (produtosVer != null) {
            txtIdProduto.setText(String.valueOf(produtosVer.getId()));
            txtNomeProduto.setText(produtosVer.getNome());
            txtPrecoProduto.setText(String.valueOf(produtosVer.getPreco()));
            txtQtdProduto.setText(String.valueOf(produtosVer.getQtd()));
        } else {
            System.out.println("Nenhum Produto foi selecionado.");
        }
    }

    public void btnAdicionarProduto(ActionEvent actionEvent) {
        if (txtNomeProduto.getText().isEmpty() || txtPrecoProduto.getText().isEmpty() || txtQtdProduto.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText(null);
            alert.setContentText("Preencha todos os espaços em branco");
            alert.showAndWait();
        } else {
            try {
                Connection conn = MyConnection.openDB();
                if (conn != null) {
                    String nome = String.valueOf(txtNomeProduto.getText());
                    BigDecimal preco = BigDecimal.valueOf(Double.parseDouble(txtPrecoProduto.getText()));;
                    int qtd = Integer.parseInt(String.valueOf(txtQtdProduto.getText()));
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("CONFIRMAÇÃO");
                    alert.setHeaderText("Deseja mesmo adicionar?");
                    alert.setContentText("Nome: " + nome + "\nPreço: " + preco + "\nQuantidade" + qtd);
                    ButtonType btnSim = new ButtonType("SIM");
                    ButtonType btnNao = new ButtonType("NÃO");
                    alert.getButtonTypes().setAll(btnSim, btnNao);
                    txtIdProduto.clear();

                    Optional<ButtonType> escolha = alert.showAndWait();
                    if (escolha.isPresent() && escolha.get() == btnSim) {
                        TableListaProduto.getItems().clear();
                        ProdutosDB.adicionar(nome, preco, qtd);
                        Produtos prod = (Produtos) TableListaProduto.getSelectionModel().getSelectedItem();
                        ObservableList<Produtos> produtos = ProdutosDB.listaProduto();
                        produtos.add(prod);
                        TableListaProduto.setItems(produtos);
                        TableListaProduto.refresh();
                        txtIdProduto.clear();
                        txtNomeProduto.clear();
                        txtPrecoProduto.clear();
                        txtQtdProduto.clear();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Nao foi possivel estabelecer uma conexão com a base de dados");
                    alert.setContentText(null);
                    alert.showAndWait();
                }
            } catch (Exception e) {
                Alert alertErro = new Alert(Alert.AlertType.ERROR);
                alertErro.setTitle("ERRO");
                alertErro.setHeaderText("Erro ao adicionar o Produto: " + e.getMessage());
                alertErro.showAndWait();
            } finally {
                MyConnection.closeDB();
            }
        }
    }

    public void btnEditarProduto(ActionEvent actionEvent) {
        Alert alert = null;
        if (txtIdProduto.getText().isEmpty() || txtNomeProduto.getText().isEmpty() || txtPrecoProduto.getText().isEmpty() || txtQtdProduto.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText(null);
            alert.setContentText("Preencha todos os espaços em branco");
            alert.showAndWait();
        } else {
            int novoId = Integer.parseInt(txtIdProduto.getText());
            Produtos ProdutoEdit = null;
            for (Produtos p : Settings.getListaProduto()) {
                if (p.getId() == novoId) {
                    ProdutoEdit = p;
                    break;
                }
            }
            if (ProdutoEdit!= null) {
                ProdutoEdit.setNome((String) txtNomeProduto.getText());
                ProdutoEdit.setPreco(BigDecimal.valueOf(Double.parseDouble(txtPrecoProduto.getText())));
                ProdutoEdit.setQtd(Integer.parseInt(txtQtdProduto.getText()));
                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                alert1.setTitle("CONFIRMAÇÃO");
                alert1.setHeaderText("Deseja mesmo editar ?"+"\n"+"ID: " +txtIdProduto.getText() + "\n" + "Nome: " + txtNomeProduto.getText() + "\n" + "Preço: " + txtPrecoProduto.getText() + "\n" + "Quantidade: " + txtQtdProduto.getText());
                alert1.setContentText(null);
                ButtonType btnSim = new ButtonType("SIM");
                ButtonType btnNao = new ButtonType("NÃO");
                alert1.getButtonTypes().setAll(btnSim, btnNao);

                Optional<ButtonType> escolha = alert1.showAndWait();
                if (escolha.isPresent() && escolha.get() == btnSim) {
                    Connection conn = null;
                    try {
                        conn = MyConnection.openDB();
                        if (conn != null) {
                            String Atualizar = "UPDATE produto SET nomeProduto = ?, preco = ?, qtd = ? WHERE idProduto = ?;";
                            PreparedStatement stmt = conn.prepareStatement(Atualizar);
                            stmt.setString(1, txtNomeProduto.getText());
                            stmt.setDouble(2, Double.parseDouble(txtPrecoProduto.getText()));
                            stmt.setString(3, txtQtdProduto.getText());
                            stmt.setInt(4, novoId);
                            int atualizarBD = stmt.executeUpdate();
                            if (atualizarBD > 0) {
                                for (Produtos p : Settings.getListaProduto()) {
                                    if (p.getId() == ProdutoEdit.getId()) {
                                        int prod = Settings.getListaProduto().indexOf(p);
                                        Settings.getListaProduto().set(prod, ProdutoEdit);
                                        break;
                                    }
                                }

                                Alert alertAddFun = new Alert(Alert.AlertType.CONFIRMATION);
                                alertAddFun.setTitle("Informação");
                                alertAddFun.setHeaderText("Produto Editado com sucesso");
                                alertAddFun.setContentText(null);
                                alertAddFun.showAndWait();
                                Settings.setEditarCliente(null);

                                TableListaProduto.refresh();
                                txtIdProduto.clear();
                                txtNomeProduto.clear();
                                txtPrecoProduto.clear();
                                txtQtdProduto.clear();
                            } else {
                                Alert alertEdit = new Alert(Alert.AlertType.ERROR);
                                alertEdit.setTitle("ERRO!");
                                alertEdit.setHeaderText("Nao foi possivel atualizar o Produto na base de dados");
                                alertEdit.setContentText(null);
                                alertEdit.showAndWait();
                            }
                        } else {
                            Alert alertEdit = new Alert(Alert.AlertType.ERROR);
                            alertEdit.setTitle("ERRO!");
                            alertEdit.setHeaderText("Não foi possível estabelecer uma conexão com a base de dados.");
                            alertEdit.setContentText(null);
                            alertEdit.showAndWait();
                        }
                    } catch (Exception e) {
                        Alert alertErro = new Alert(Alert.AlertType.ERROR);
                        alertErro.setTitle("ERRO");
                        alertErro.setHeaderText("Erro ao Editar o Produto: " + e.getMessage());
                        alertErro.showAndWait();
                    } finally {
                        if (conn != null) {
                            try {
                                conn.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    public void btnEliminarProduto(ActionEvent actionEvent) {
        if (txtIdProduto.getText().isEmpty()
                || txtNomeProduto.getText().isEmpty()
                || txtPrecoProduto.getText().isEmpty()
                || txtQtdProduto.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Selecione algum Produto da tabela");
            alert.showAndWait();}
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminar");
            alert.setHeaderText("Deseja mesmo Eliminar?"+"\n"+"ID: " +txtIdProduto.getText() + "\n" + "Nome: " + txtNomeProduto.getText() + "\n" + "Preço: " + txtPrecoProduto.getText() + "\n" + "Quantidade: " + txtQtdProduto.getText());
            ButtonType botaoSim = new ButtonType("Sim");
            ButtonType botaoNao = new ButtonType("Não");
            alert.getButtonTypes().setAll(botaoSim, botaoNao);
            Optional<ButtonType> choose = alert.showAndWait();
            if (choose.get() == botaoSim) {
                int novoId = Integer.parseInt(txtIdProduto.getText());
                for (Produtos p : Settings.getListaProduto()) {
                    if (p.getId() == novoId) {
                        Settings.getListaProduto().remove(p);
                        ProdutosDB.remover(novoId);
                        txtIdCliente.setText("");
                        txtNomeCliente.setText("");
                        txtContribuinteCliente.setText("");
                        txtTelemovelCliente.setText("");
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Information");
                        alert1.setHeaderText(null);
                        alert1.setContentText("O Produtos foi Eliminado");
                        alert1.showAndWait();
                        break;
                    }
                }
            }
            else{
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Information");
                alert2.setHeaderText(null);
                alert2.setContentText("Cancelado com Sucesso");
                alert2.showAndWait();

            }
        }
    }
    public void TableListaFornecedor(){
        FornecedorID.setCellValueFactory(new PropertyValueFactory<Fornecedor, Integer>("Id"));
        FornecedorNome.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("nome"));
        FornecedorMorada.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("morada"));
        FornecedorTelefone.setCellValueFactory(new PropertyValueFactory<Fornecedor, Integer>("telefone"));
        FornecedorEmail.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("email"));
        TableListaFornecedor.setItems(FornecedorDB.listaFornecedor());
        TableListaFornecedor.refresh();
    }
    public void verFornecedor(MouseEvent mouseEvent) {
        Fornecedor fornecedorVer = (Fornecedor) TableListaFornecedor.getSelectionModel().getSelectedItem();
        if (fornecedorVer != null) {
            txtIdFornecedor.setText(String.valueOf(fornecedorVer.getId()));
            txtNomeFornecedor.setText(fornecedorVer.getNome());
            txtMoradaFornecedor.setText(fornecedorVer.getMorada());
            txtTelefoneFornecedor.setText(String.valueOf(fornecedorVer.getTelefone()));
            txtEmailFornecedor.setText(fornecedorVer.getEmail());
        } else {
            System.out.println("Nenhum Fornecedor foi selecionado.");
        }
    }

    public void btnAdicionarFornecedor(ActionEvent actionEvent) {
        if (txtNomeFornecedor.getText().isEmpty() || txtMoradaFornecedor.getText().isEmpty() || txtTelefoneFornecedor.getText().isEmpty() || txtEmailFornecedor.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText(null);
            alert.setContentText("Preencha todos os espaços em branco");
            alert.showAndWait();
        } else {
            try {
                Connection conn = MyConnection.openDB();
                if (conn != null) {
                    String nome = txtNomeFornecedor.getText();
                    String morada = txtMoradaFornecedor.getText();
                    int telefone = Integer.parseInt(txtTelefoneFornecedor.getText());
                    String email = txtEmailFornecedor.getText();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("CONFIRMAÇÃO");
                    alert.setHeaderText("Deseja mesmo adicionar?");
                    alert.setContentText("Nome: " + nome + "\nMorada: " + morada + "\nTelemovel: " + telefone + "\nEmail: "+email);
                    ButtonType btnSim = new ButtonType("SIM");
                    ButtonType btnNao = new ButtonType("NÃO");
                    alert.getButtonTypes().setAll(btnSim, btnNao);
                    txtIdFornecedor.clear();

                    Optional<ButtonType> escolha = alert.showAndWait();
                    if (escolha.isPresent() && escolha.get() == btnSim) {
                        TableListaFornecedor.getItems().clear();
                        FornecedorDB.adicionar(nome,morada,telefone,email);
                        Fornecedor forn = (Fornecedor) TableListaFornecedor.getSelectionModel().getSelectedItem();
                        ObservableList<Fornecedor> fornecedores = FornecedorDB.listaFornecedor();
                        fornecedores.add(forn);
                        TableListaFornecedor.setItems(fornecedores);
                        TableListaFornecedor.refresh();
                        txtIdFornecedor.clear();
                        txtNomeFornecedor.clear();
                        txtMoradaFornecedor.clear();
                        txtTelefoneFornecedor.clear();
                        txtEmailFornecedor.clear();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Nao foi possivel estabelecer uma conexão com a base de dados");
                    alert.setContentText(null);
                    alert.showAndWait();
                }
            } catch (Exception e) {
                Alert alertErro = new Alert(Alert.AlertType.ERROR);
                alertErro.setTitle("ERRO");
                alertErro.setHeaderText("Erro ao adicionar o Fornecedor: " + e.getMessage());
                alertErro.showAndWait();
            } finally {
                MyConnection.closeDB();
            }
        }

    }

    public void btnEditarFornecedor(ActionEvent actionEvent) {
        Alert alert = null;
        if (txtIdFornecedor.getText().isEmpty() || txtNomeFornecedor.getText().isEmpty() || txtMoradaFornecedor.getText().isEmpty() || txtTelefoneFornecedor.getText().isEmpty() || txtEmailFornecedor.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText(null);
            alert.setContentText("Preencha todos os espaços em branco");
            alert.showAndWait();
        } else {
            int novoId = Integer.parseInt(txtIdFornecedor.getText());
            Fornecedor FornecedorEdit = null;
            for (Fornecedor f : Settings.getListaFornecedor()) {
                if (f.getId() == novoId) {
                    FornecedorEdit = f;
                    break;
                }
            }
            if (FornecedorEdit != null) {
                FornecedorEdit.setNome((String) txtNomeFornecedor.getText());
                FornecedorEdit.setMorada((txtMoradaFornecedor.getText()));
                FornecedorEdit.setTelefone(Integer.parseInt(txtTelefoneFornecedor.getText()));
                FornecedorEdit.setEmail(txtEmailFornecedor.getText());
                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                alert1.setTitle("CONFIRMAÇÃO");
                alert1.setHeaderText("Deseja mesmo editar ?");
                alert1.setContentText(null);
                ButtonType btnSim = new ButtonType("SIM");
                ButtonType btnNao = new ButtonType("NÃO");
                alert1.getButtonTypes().setAll(btnSim, btnNao);

                Optional<ButtonType> escolha = alert1.showAndWait();
                if (escolha.isPresent() && escolha.get() == btnSim) {
                    Connection conn = null;
                    try {
                        conn = MyConnection.openDB();
                        if (conn != null) {
                            String Atualizar = "UPDATE fornecedor SET nomeFornecedor = ?, moradaFornecedor = ?, telefoneFornecedor = ?, emailFornecedor = ? WHERE idFornecedor = ?;";
                            PreparedStatement stmt = conn.prepareStatement(Atualizar);
                            stmt.setString(1, txtNomeFornecedor.getText());
                            stmt.setString(2, txtMoradaFornecedor.getText());
                            stmt.setInt(3, Integer.parseInt(txtTelefoneFornecedor.getText()));
                            stmt.setString(4,txtEmailFornecedor.getText());
                            stmt.setInt(5, novoId);
                            int atualizarBD = stmt.executeUpdate();
                            if (atualizarBD > 0) {
                                for (Fornecedor f : Settings.getListaFornecedor()) {
                                    if (f.getId() == FornecedorEdit.getId()) {
                                        int forn = Settings.getListaFornecedor().indexOf(f);
                                        Settings.getListaFornecedor().set(forn,FornecedorEdit);
                                        break;
                                    }
                                }

                                Alert alertAddFun = new Alert(Alert.AlertType.CONFIRMATION);
                                alertAddFun.setTitle("Informação");
                                alertAddFun.setHeaderText("Fornecedor Editado com sucesso");
                                alertAddFun.setContentText(null);
                                alertAddFun.showAndWait();
                                Settings.setEditarCliente(null);

                                TableListaFornecedor.refresh();
                                txtIdFornecedor.clear();
                                txtNomeFornecedor.clear();
                                txtMoradaFornecedor.clear();
                                txtTelefoneFornecedor.clear();
                                txtEmailFornecedor.clear();
                            } else {
                                Alert alertEdit = new Alert(Alert.AlertType.ERROR);
                                alertEdit.setTitle("ERRO!");
                                alertEdit.setHeaderText("Nao foi possivel atualizar o Fornecedor na base de dados");
                                alertEdit.setContentText(null);
                                alertEdit.showAndWait();
                            }
                        } else {
                            Alert alertEdit = new Alert(Alert.AlertType.ERROR);
                            alertEdit.setTitle("ERRO!");
                            alertEdit.setHeaderText("Não foi possível estabelecer uma conexão com a base de dados.");
                            alertEdit.setContentText(null);
                            alertEdit.showAndWait();
                        }
                    } catch (Exception e) {
                        Alert alertErro = new Alert(Alert.AlertType.ERROR);
                        alertErro.setTitle("ERRO");
                        alertErro.setHeaderText("Erro ao Editar o Fornecedor: " + e.getMessage());
                        alertErro.showAndWait();
                    } finally {
                        if (conn != null) {
                            try {
                                conn.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    public void btnEliminarFornecedor(ActionEvent actionEvent) {
        if (txtIdFornecedor.getText().isEmpty()
                || txtNomeFornecedor.getText().isEmpty()
                || txtMoradaFornecedor.getText().isEmpty()
                || txtTelefoneFornecedor.getText().isEmpty()
                || txtEmailFornecedor.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Selecione algum Fornecedor da tabela");
            alert.showAndWait();}
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminar");
            alert.setHeaderText("Deseja mesmo Eliminar?"+"\n"+"ID: " +txtIdFornecedor.getText() + "\n" + "Nome: " + txtNomeFornecedor.getText() + "\n" + "Morada: " + txtMoradaFornecedor.getText() + "\n" + "Telemóvel: " + txtTelefoneFornecedor.getText() + "\nEmail: "+ txtEmailFornecedor.getText());
            ButtonType botaoSim = new ButtonType("Sim");
            ButtonType botaoNao = new ButtonType("Não");
            alert.getButtonTypes().setAll(botaoSim, botaoNao);
            Optional<ButtonType> choose = alert.showAndWait();
            if (choose.get() == botaoSim) {
                int novoId = Integer.parseInt(txtIdFornecedor.getText());
                for (Fornecedor f : Settings.getListaFornecedor()) {
                    if (f.getId() == novoId) {
                        Settings.getListaFornecedor().remove(f);
                        FornecedorDB.remover(novoId);
                        txtIdFornecedor.setText("");
                        txtNomeFornecedor.setText("");
                        txtMoradaFornecedor.setText("");
                        txtTelefoneFornecedor.setText("");
                        txtEmailFornecedor.setText("");
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Information");
                        alert1.setHeaderText(null);
                        alert1.setContentText("O Fornecedor foi Eliminado");
                        alert1.showAndWait();
                        break;
                    }
                }
            }
            else{
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Information");
                alert2.setHeaderText(null);
                alert2.setContentText("Cancelado com Sucesso");
                alert2.showAndWait();

            }
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableListaCliente();
        TableListaProduto();
        TableListaFornecedor();
    }



}

