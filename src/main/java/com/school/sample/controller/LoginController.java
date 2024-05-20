package com.school.sample.controller;

import com.school.sample.JVclass.Login;
import com.school.sample.connection.LoginDB;
import com.school.sample.connection.MyConnection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class LoginController {
    @FXML
    private AnchorPane Log_in;
    @FXML
    private Button New_Conta;
    @FXML
    private TextField txt_Username;
    @FXML
    private PasswordField txt_password;
    @FXML
    private Button Login;
    @FXML
    private Button btn_sair;
    @FXML
    private AnchorPane Criar_Conta;
    @FXML
    private TextField txt_new_Username;
    @FXML
    private Button btn_Criar;
    @FXML
    private PasswordField txt_new_Password;
    @FXML
    private Button btn_login;
    public void sair (ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Log out");
        alert.setHeaderText("Deseja mesmo Sair?");
        // Adiciona botões personalizados em português
        ButtonType botaoSim = new ButtonType("Sim");
        ButtonType botaoNao = new ButtonType("Não");
        alert.getButtonTypes().setAll(botaoSim, botaoNao);
        // Exibe o alerta e aguarda a escolha do utilizador
        Optional<ButtonType> choose = alert.showAndWait();
        // Verifica se o utilizador escolheu "Sim"
        if ( choose.get() == botaoSim ) {
            Stage stage = (Stage) btn_sair.getScene().getWindow(); //Obtendo a janela atual
            stage.close(); //Fechando o Stage
        }
    }
    @FXML
    public void btn_Criar_On_Action(ActionEvent actionEvent) {
        if ( txt_new_Username.getText().isEmpty() || txt_new_Password.getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText(null);
            alert.setContentText("Preencha todos os espaços em branco");
            alert.showAndWait();
        } else {
            try {
                Connection conn = MyConnection.openDB();
                if ( conn != null ) {
                    String Conta = txt_new_Username.getText();
                    String pass = txt_new_Password.getText();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("CONFIRMAÇÃO");
                    alert.setHeaderText("Deseja mesmo adicionar?");
                    ButtonType btnSim = new ButtonType("SIM");
                    ButtonType btnNao = new ButtonType("NÃO");
                    alert.getButtonTypes().setAll(btnSim, btnNao);

                    Optional<ButtonType> escolha = alert.showAndWait();
                    if ( escolha.isPresent() && escolha.get() == btnSim ) {
                        LoginDB.adicionar(Conta, pass);
                        ObservableList<Login> logins = LoginDB.listalogin();
                        txt_new_Username.clear();
                        txt_new_Password.clear();
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
                alertErro.setHeaderText("Erro ao adicionar a Conta: " + e.getMessage());
                alertErro.showAndWait();
            } finally {
                MyConnection.closeDB();
            }
        }
    }

    public void Login_On_Action(ActionEvent actionEvent) {
        if ( txt_Username.getText().isEmpty() || txt_password.getText().isEmpty() ) {
            Connection conn = MyConnection.openDB();

            String verificarLogin = "SELECT count(1) FROM Login WHERE Conta_Login = '" + txt_Username.getText() + "' AND password = '" + txt_password.getText() + "'";

            try{
                Statement stmt = conn.createStatement();
                ResultSet resultado = stmt.executeQuery(verificarLogin);

                while (resultado.next()){

                    if(resultado.getInt(1) == 1) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("INFORMAÇÃO");
                        alert.setHeaderText(null);
                        alert.setContentText("O Login foi um sucesso");
                        alert.showAndWait();

                        // Cria uma nova janela
                        Stage back = new Stage();

                        // Obtém a janela atual através do botão de login e esconde-a
                        Window window = Login.getScene().getWindow();
                        window.hide();
                        back.close();
                        Parent parent = FXMLLoader.load(getClass().getResource("Principal.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(parent);

                        // Define a nova cena na janela e exibe-a
                        stage.setScene(scene);
                        stage.show();
                    }else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Alerta");
                        alert.setHeaderText(null);
                        alert.setContentText("O Login Invalido");
                        alert.showAndWait();

                    }
                }
            } catch (SQLException | IOException e) {
                // Imprime a stack trace do erro, se ocorrer
                e.printStackTrace();
            }finally {
                //Fecha a conexão com a base de dados
                MyConnection.closeDB();
            }
        }
    }
    public void New_Conta_On_Action(ActionEvent actionEvent) {
        Log_in.setVisible(false);
        Criar_Conta.setVisible(true);
    }

    public void btn_login_On_Action(ActionEvent actionEvent) {
        Log_in.setVisible(true);
        Criar_Conta.setVisible(false);
    }
}
