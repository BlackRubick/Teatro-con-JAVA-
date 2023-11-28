package com.example.teatro2.Cliente;


import com.example.teatro2.Recepcion.Recepcionista;

public class Cliente implements Runnable {
    private String nombre;
    private int asientoReservado = -1; // -1 indica que no hay asiento reservado
    private Recepcionista recepcionista;

    public Cliente(String nombre, Recepcionista recepcionista) {
        this.nombre = nombre;
        this.recepcionista = recepcionista;
    }

    public String getNombre() {
        return nombre;
    }

    public void hacerReserva() {
        // Supongamos que esta llamada interactúa con el recepcionista para hacer una reserva
        System.out.println(nombre + " está intentando hacer una reserva...");
        recepcionista.gestionarReserva(this);
    }

    public void cancelarReserva() {
        if (asientoReservado != -1) {
            recepcionista.gestionarCancelacion(this);
            asientoReservado = -1; // Asegúrate de actualizar el estado de asientoReservado antes de llamar a gestionarCancelacion
        } else {
            System.out.println(nombre + " no tiene una reserva para cancelar.");
        }
    }



    public int getAsientoReservado() {
        return asientoReservado;
    }

    public void setAsientoReservado(int asientoReservado) {
        this.asientoReservado = asientoReservado;
        if (asientoReservado == -1) {
            System.out.println(nombre + " ha cancelado su reserva.");
        } else {
            System.out.println(nombre + " ha reservado el asiento " + asientoReservado);
        }
    }

    @Override
    public void run() {
        hacerReserva();
    }
}

