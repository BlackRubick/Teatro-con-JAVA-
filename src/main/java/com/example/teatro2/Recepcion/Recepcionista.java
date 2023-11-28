package com.example.teatro2.Recepcion;


import com.example.teatro2.Cliente.Cliente;
import com.example.teatro2.Teatro.Teatro;

import java.util.LinkedList;
import java.util.Queue;

public class Recepcionista {
    private Teatro teatro;
    private Queue<Cliente> listaDeEspera;

    public Recepcionista(Teatro teatro) {
        this.teatro = teatro;
        this.listaDeEspera = new LinkedList<>();
    }

    public synchronized void gestionarReserva(Cliente cliente) {
        int asiento = teatro.asignarAsiento(cliente);
        if (asiento != -1) {
            cliente.setAsientoReservado(asiento);
        } else {
            System.out.println("No hay asientos disponibles para " + cliente.getNombre() + ". Añadiendo a la lista de espera.");
            listaDeEspera.offer(cliente);
            // Lógica adicional si quieres notificar a los clientes en la lista de espera cuando haya asientos disponibles
        }
    }

    public synchronized void gestionarCancelacion(Cliente cliente) {
        if (cliente.getAsientoReservado() != -1) {
            teatro.liberarAsiento(cliente.getAsientoReservado());
            System.out.println("Reserva cancelada para el cliente: " + cliente.getNombre());
            // Actualiza el estado del cliente aquí, en lugar de llamar a cliente.cancelarReserva
            cliente.setAsientoReservado(-1);

            if (!listaDeEspera.isEmpty()) {
                Cliente siguienteEnEspera = listaDeEspera.poll();
                gestionarReserva(siguienteEnEspera);
            }
        } else {
            removerDeListaDeEspera(cliente);
        }
    }


    // Puedes añadir un método para comprobar la lista de espera y asignar asientos si están disponibles
    public void procesarListaDeEspera() {
        Cliente clienteEnEspera;
        while ((clienteEnEspera = listaDeEspera.peek()) != null) {
            int asiento = teatro.asignarAsiento(clienteEnEspera);
            if (asiento == -1) {
                break; // Si no hay asientos disponibles, sale del bucle
            }
            listaDeEspera.poll();
            clienteEnEspera.setAsientoReservado(asiento);
        }
    }
    public synchronized void removerDeListaDeEspera(Cliente cliente) {
        if (listaDeEspera.contains(cliente)) {
            listaDeEspera.remove(cliente);
            System.out.println(cliente.getNombre() + " ha sido removido de la lista de espera.");
        }
    }

}
