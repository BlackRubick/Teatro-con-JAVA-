package com.example.teatro2.Actores;

public class Actores {
    private String nombre;
    private String rol;
    private String horario; // Atributo para almacenar el horario

    public Actores(String nombre, String rol) {
        this.nombre = nombre;
        this.rol = rol;
        this.horario = ""; // Inicializa el horario como una cadena vacía
    }

    public void recibirHorario(String horario) {
        this.horario = horario;
        System.out.println("El actor " + nombre + " (" + rol + ") tiene el horario: " + horario);
    }

    public String obtenerHorario() {
        return horario;
    }

}
