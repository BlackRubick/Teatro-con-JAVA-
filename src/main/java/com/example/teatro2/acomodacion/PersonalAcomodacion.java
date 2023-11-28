package com.example.teatro2.acomodacion;

import com.example.teatro2.Cliente.Cliente;
import com.example.teatro2.Teatro.Teatro;
import javafx.application.Platform;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PersonalAcomodacion implements Runnable {
    private final Teatro teatro;
    private final BlockingQueue<Cliente> colaDeClientes;
    private volatile boolean activo;

    public PersonalAcomodacion(Teatro teatro) {
        this.teatro = teatro;
        this.colaDeClientes = new LinkedBlockingQueue<>();
        this.activo = true;
    }

    public void agregarClienteACola(Cliente cliente) {
        colaDeClientes.offer(cliente);
    }

    private void guiarACliente(Cliente cliente) {
        // Simula la lógica de guiar al cliente a su asiento
        int asiento = teatro.asignarAsiento(cliente);
        if (asiento != -1) {
            // Si hay un asiento asignado, guía al cliente a él.
            // Supongamos que tienes una etiqueta o algo en la UI para mostrar el asiento asignado.
            final int asientoFinal = asiento;
            Platform.runLater(() -> {
                // Actualiza la UI con la ubicación del asiento asignado
                // Por ejemplo, actualizaría una etiqueta con el número del asiento.
                // Aquí necesitarías referenciar esa etiqueta o componente de la UI.
            });
        } else {
            // Manejar el caso en que no hay asientos disponibles
            Platform.runLater(() -> {
                // Actualizar la UI para indicar que no hay asientos disponibles
            });
        }
    }

    @Override
    public void run() {
        while (activo) {
            try {
                Cliente cliente = colaDeClientes.take();
                guiarACliente(cliente);
            } catch (InterruptedException e) {
                if (!activo) {
                    // Si el hilo se está deteniendo, sal del bucle
                    break;
                }
                Thread.currentThread().interrupt();
            }
        }
    }

    public void detener() {
        activo = false;
        // Interrumpir el hilo si está esperando tomar elementos de la cola
        Thread.currentThread().interrupt();
    }
}
