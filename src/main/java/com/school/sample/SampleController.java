package com.school.sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class SampleController {
    public AnchorPane ClientesTela;
    public AnchorPane FornecedoresTela;
    public AnchorPane SobreMimTela;
    @FXML
    private GridPane produtos_grid;

    @FXML
    private AnchorPane HomeTela;

    @FXML
    private AnchorPane ProdutosTela;

    @FXML
    void ClientesButton(ActionEvent event) {
        HomeTela.setVisible(false);
        ProdutosTela.setVisible(false);
        ClientesTela.setVisible(true);
        FornecedoresTela.setVisible(false);
        SobreMimTela.setVisible(false);
    }

    @FXML
    void FornecedoresButton(ActionEvent event) {
        HomeTela.setVisible(false);
        ProdutosTela.setVisible(false);
        ClientesTela.setVisible(false);
        FornecedoresTela.setVisible(true);
        SobreMimTela.setVisible(false);
    }

    @FXML
    void HomeButton(ActionEvent event) {
        HomeTela.setVisible(true);
        ProdutosTela.setVisible(false);
        ClientesTela.setVisible(false);
        FornecedoresTela.setVisible(false);
        SobreMimTela.setVisible(false);
    }

    @FXML
    void ProdutosButton(ActionEvent event) {
        HomeTela.setVisible(false);
        ProdutosTela.setVisible(true);
        ClientesTela.setVisible(false);
        FornecedoresTela.setVisible(false);
        SobreMimTela.setVisible(false);
    }


    @FXML
    void SobreButton(ActionEvent event) {
        HomeTela.setVisible(false);
        ProdutosTela.setVisible(false);
        ClientesTela.setVisible(false);
        FornecedoresTela.setVisible(false);
        SobreMimTela.setVisible(true);
    }

}
