package com.example.teatro2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.Parent;


import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Carga el archivo FXML que contiene la vista
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));

        // Configura el título de la ventana
        stage.setTitle("Sistema de Reservas de Teatro");

        // Crea la escena con la vista cargada y establece el tamaño de la ventana
        Scene scene = new Scene(root, 800, 600);

        // Establece la escena en el escenario y la muestra
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}