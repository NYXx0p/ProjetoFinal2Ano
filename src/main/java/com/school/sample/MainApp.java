package com.school.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //1º PASSO - O método Start associa este Layout a um objeto da classe Parent.
        Parent root = FXMLLoader.load(getClass().getResource("Principal.fxml"));
        //2º PASSO - Definição do titulo para a Stage
        primaryStage.setTitle("Minha Loja");
        //3º PASSO - Associação do Layout do Parent à Sceme e esta à Stage
        primaryStage.setScene(new Scene(root));
        //4º PASSO - Mostrar a Stage no Sistema Operativo
        primaryStage.show();
    }
}