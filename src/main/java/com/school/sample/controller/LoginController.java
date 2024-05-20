package com.school.sample.controller;

import com.school.sample.JVclass.Login;
import com.school.sample.JVclass.Settings;
import com.school.sample.connection.LoginDB;
import com.school.sample.connection.MyConnection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
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
    @FXML
    public void Login_On_Action(ActionEvent actionEvent) {
        if ( txt_Username.getText().isEmpty() || txt_password.getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, preencha todos os campos");
            alert.showAndWait();
        }
        else{
            String sql = "SELECT Conta_Login, Pass FROM login WHERE Conta_Login = ? and Pass = ?";

                Connection conn = MyConnection.openDB();
                PreparedStatement stmt = null;
                ResultSet rs = null;

                try {

                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, txt_Username.getText());
                    stmt.setString(2, txt_password.getText());

                    rs = stmt.executeQuery();
                    // IF SUCCESSFULLY LOGIN, THEN PROCEED TO ANOTHER FORM WHICH IS OUR MAIN FORM
                    if ( rs.next() ) {
                        // LINK YOUR MAIN FORM
                        Parent scene = FXMLLoader.load(getClass().getResource("src/main/resources/com/school/sample/Principal.fxml"));
                        Stage voltar = new Stage();
                        voltar.initOwner(Settings.getPrimaryStage());
                        voltar.initModality(Modality.WINDOW_MODAL);
                        voltar.setScene(new Scene(scene));
                        // Obtém a janela atual e a oculta
                        Window window = Login.getScene().getWindow();
                        window.hide();
                        // Exibe a nova janela "Principal1.fxml"
                        voltar.show();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("INFORMAÇÃO");
                        alert.setHeaderText(null);
                        alert.setContentText("O Login foi um sucesso");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Alerta");
                        alert.setHeaderText(null);
                        alert.setContentText("O Login Invalido");
                        alert.showAndWait();

                    }
                } catch (SQLException | IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    //Fecha a conexão com a base de dados
                    MyConnection.closeDB();
                }
            }
        }

        public void New_Conta_On_Action (ActionEvent actionEvent){
            Log_in.setVisible(false);
            Criar_Conta.setVisible(true);
        }

        public void btn_login_On_Action (ActionEvent actionEvent){
            Log_in.setVisible(true);
            Criar_Conta.setVisible(false);
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
