package com.example.teatro2.Teatro;


import com.example.teatro2.Cliente.Cliente;

public class Teatro {
    private final Asiento[] asientos;

    public Teatro(int numeroDeAsientos) {
        this.asientos = new Asiento[numeroDeAsientos];
        for (int i = 0; i < numeroDeAsientos; i++) {
            asientos[i] = new Asiento();
        }
    }

    public synchronized int asignarAsiento(Cliente cliente) {
        for (int i = 0; i < asientos.length; i++) {
            if (!asientos[i].estaOcupado()) {
                asientos[i].ocupar();
                return i; // Retorna el número de asiento asignado
            }
        }
        return -1; // Retorna -1 si no hay asientos disponibles
    }

    public synchronized void liberarAsiento(int numeroDeAsiento) {
        if (numeroDeAsiento >= 0 && numeroDeAsiento < asientos.length) {
            asientos[numeroDeAsiento].desocupar();
        }
    }
    public boolean estaLleno() {
        // Suponiendo que tienes un array o una estructura similar para los asientos
        for (Asiento asiento : asientos) {
            if (!asiento.estaOcupado()) {
                return false; // Si encuentra un asiento no ocupado, devuelve false
            }
        }
        return true; // Todos los asientos están ocupados
    }
}

