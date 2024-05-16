package com.school.sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CardController implements Initializable {
    @FXML
    private AnchorPane card;

    @FXML
    private Button prod_add_btn;

    @FXML
    private Label prod_card_nome;

    @FXML
    private Label prod_card_preco;

    @FXML
    private ImageView prod_img;

    @FXML
    private Spinner<?> prod_spinner;

@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
