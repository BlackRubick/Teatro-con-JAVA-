package com.example.teatro2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.image.Image;
import java.util.Random;
import com.example.teatro2.Cliente.Cliente;
import com.example.teatro2.Recepcion.Recepcionista;
import com.example.teatro2.Teatro.Teatro;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

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

    @FXML
    private Pane miPanel;

    @FXML
    private ImageView imagen1;
    @FXML
    private ImageView imagen2;

    private int filaActual = 0;
    private int columnaActual = 0;

    private Teatro teatro;
    private Recepcionista recepcionista;
    private Cliente ultimoCliente;

    @FXML
    private void initialize() {
        teatro = new Teatro(90);
        recepcionista = new Recepcionista(teatro);

        //Image image1 = new Image("/Image/mesero.png");
        //imagen1.setImage(image1);

        //Image image2 = new Image("/ruta/a/tu/imagen2.png");
        //imagen2.setImage(image2);

        Rectangle rectanguloNegro = new Rectangle(miPanel.getPrefWidth() / 4, miPanel.getPrefHeight());
        rectanguloNegro.setFill(Color.BLACK);

        // Posicionar el rectángulo en el lado derecho del panel
        rectanguloNegro.setLayoutX(3 * miPanel.getPrefWidth() / 4);

        // Añadir el rectángulo al panel
        miPanel.getChildren().add(rectanguloNegro);

        // Crear un texto y posicionarlo en el centro del rectángulo negro
        Text texto = new Text("Teatro");
        texto.setFill(Color.WHITE); // Color del texto
        texto.setFont(Font.font("Verdana", FontWeight.BOLD, 20)); // Fuente del texto

        // Necesitamos añadir el texto al panel para poder obtener sus dimensiones
        miPanel.getChildren().add(texto);

        // Centrar el texto en el rectángulo
        texto.setX(rectanguloNegro.getLayoutX() + rectanguloNegro.getWidth() / 2 - texto.getBoundsInParent().getWidth() / 2);
        texto.setY(rectanguloNegro.getLayoutY() + rectanguloNegro.getHeight() / 2 + texto.getBoundsInParent().getHeight() / 4);
    }


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Iniciando Teatro Demo...");
        TeatroDemo.iniciarDemo();

        bolitasContenedor.getChildren().clear();

        int numFilas = 10; // Número de filas en la cuadrícula
        int numColumnas = 9; // Número de columnas en la cuadrícula

        double anchoCelda = bolitasContenedor.getPrefWidth() / numColumnas;
        double altoCelda = bolitasContenedor.getPrefHeight() / numFilas;

        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numColumnas; j++) {
                Circle bolita = new Circle(5, javafx.scene.paint.Color.BLACK);
                bolita.setLayoutX(j * anchoCelda + anchoCelda / 2); // Centra la bolita en la celda
                bolita.setLayoutY(i * altoCelda + altoCelda / 2); // Centra la bolita en la celda
                bolitasContenedor.getChildren().add(bolita);
            }
        }
    }

    @FXML
    protected void onReservaButtonClick() {
        Cliente cliente = new Cliente("Cliente " + System.currentTimeMillis(), recepcionista);
        new Thread(cliente).start();

        int numFilas = 10; // Número de filas en la cuadrícula
        int numColumnas = 9; // Número de columnas en la cuadrícula

        // Comprobar si hay asientos disponibles antes de dibujar una bolita
        if (!teatro.estaLleno() && filaActual < numFilas && columnaActual < numColumnas) {
            Circle bolita = new Circle(5, javafx.scene.paint.Color.BLACK);



            double anchoCelda = bolitasContenedor.getPrefWidth() / numColumnas;
            double altoCelda = bolitasContenedor.getPrefHeight() / numFilas;

            bolita.setLayoutX(columnaActual * anchoCelda + anchoCelda / 2); // Centra la bolita en la celda
            bolita.setLayoutY(filaActual * altoCelda + altoCelda / 2); // Centra la bolita en la celda

            bolitasContenedor.getChildren().add(bolita);

            // Actualizar la fila y la columna para la próxima bolita
            columnaActual++;
            if (columnaActual >= numColumnas) {
                columnaActual = 0;
                filaActual++;
            }
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
