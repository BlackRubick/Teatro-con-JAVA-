package com.example.teatro2.Teatro;


public class Asiento {
    private boolean ocupado;

    public Asiento() {
        this.ocupado = false;
    }

    public synchronized void ocupar() {
        ocupado = true;
    }

    public synchronized void desocupar() {
        ocupado = false;
    }

    public synchronized boolean estaOcupado() {
        return ocupado;
    }
}
