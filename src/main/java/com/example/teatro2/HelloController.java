package com.example.teatro2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import java.util.Random;
import com.example.teatro2.Cliente.Cliente;
import com.example.teatro2.Recepcion.Recepcionista;
import com.example.teatro2.Teatro.Teatro;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button tuBotonParaDemo;
    @FXML
    private Button botonReserva;
    @FXML
    private Button botonCancelarReserva;
    @FXML
    private Pane bolitasContenedor; // Referencia al Pane para las bolitas

    private Teatro teatro;
    private Recepcionista recepcionista;
    private Cliente ultimoCliente;

    @FXML
    private void initialize() {
        teatro = new Teatro(90);
        recepcionista = new Recepcionista(teatro);
    }


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Iniciando Teatro Demo...");
        TeatroDemo.iniciarDemo(); // Llama al método estático de TeatroDemo

        // Asegúrate de que el Pane tenga suficiente espacio
        bolitasContenedor.getChildren().clear(); // Limpiar el contenedor
        Random random = new Random();
        double padding = 10; // Añade un pequeño padding para evitar que las bolitas se dibujen en el borde
        for (int i = 0; i < 90; i++) {
            Circle bolita = new Circle(5, javafx.scene.paint.Color.BLACK); // Radio de 5 y color negro
            // Asegúrate de que las bolitas se coloquen dentro de los límites del Pane
            bolita.setLayoutX(padding + random.nextDouble() * (bolitasContenedor.getPrefWidth() - 2 * padding));
            bolita.setLayoutY(padding + random.nextDouble() * (bolitasContenedor.getPrefHeight() - 2 * padding));
            bolitasContenedor.getChildren().add(bolita);
        }
    }



    @FXML
    protected void onReservaButtonClick() {
        Cliente cliente = new Cliente("Cliente " + System.currentTimeMillis(), recepcionista);
        new Thread(cliente).start();

        // Comprobar si hay asientos disponibles antes de dibujar una bolita
        if (!teatro.estaLleno()) { // Suponiendo que tienes un método estaLleno() en Teatro
            Circle bolita = new Circle(5, javafx.scene.paint.Color.BLACK); // Radio de 5 y color negro
            Random random = new Random();
            bolita.setLayoutX(random.nextDouble() * bolitasContenedor.getPrefWidth());
            bolita.setLayoutY(random.nextDouble() * bolitasContenedor.getPrefHeight());
            bolitasContenedor.getChildren().add(bolita);
        } else {
            System.out.println("El teatro está lleno, no se pueden hacer más reservas.");
        }
    }

    @FXML
    protected void onCancelarReservaButtonClick() {
        try {
            if (ultimoCliente != null && ultimoCliente.getAsientoReservado() != -1) {
                ultimoCliente.cancelarReserva();
            } else {
                System.out.println("No hay reserva activa para cancelar.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
