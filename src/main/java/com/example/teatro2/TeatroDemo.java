package com.example.teatro2;

import com.example.teatro2.acomodacion.PersonalAcomodacion;
import com.example.teatro2.Actores.Actores;
import com.example.teatro2.Cliente.Cliente;
import com.example.teatro2.Recepcion.Recepcionista;
import com.example.teatro2.Teatro.Teatro;

public class TeatroDemo {

    public static void iniciarDemo() {
        Teatro teatro = new Teatro(90); // Teatro con 90 asientos
        Recepcionista recepcionista = new Recepcionista(teatro);
        PersonalAcomodacion acomodador = new PersonalAcomodacion(teatro);

        Actores actor1 = new Actores("John Doe", "Hamlet");
        actor1.recibirHorario("Viernes 8:00 PM");
        Actores actriz1 = new Actores("Jane Smith", "Ophelia");
        actriz1.recibirHorario("Viernes 8:00 PM");

        // Aquí puedes añadir más actores y asignarles horarios según sea necesario

        // Crear e iniciar hilos de clientes
        Thread[] hilosClientes = new Thread[90];
        for (int i = 0; i < 90; i++) {
            Cliente cliente = new Cliente("Cliente " + (i + 1), recepcionista);
            hilosClientes[i] = new Thread(cliente);
            hilosClientes[i].start();
        }

        // Inicia el personal de acomodación
        Thread acomodadorThread = new Thread(acomodador);
        acomodadorThread.start();

        // Simulación: espera un tiempo antes de finalizar
        try {
            Thread.sleep(100000); // Espera 100 segundos antes de finalizar
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Detener todos los hilos de clientes
        for (Thread hilo : hilosClientes) {
            hilo.interrupt();
        }

        acomodador.detener();
        acomodadorThread.interrupt();
    }
}
