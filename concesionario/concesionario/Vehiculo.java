package concesionario;

import java.util.Scanner;

public abstract class Vehiculo {
    private String marca;
    private String modelo;
    private int anio;
    private boolean condicionNuevo;
    private String color;
    private String patente; // aunque no se especifique en el TPI, se agrega el atributo patente para sobreescribir el metodo equals
    

    public Vehiculo(String marca, String modelo, int anio, boolean condicionNuevo, String color, String patente) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.condicionNuevo = condicionNuevo;
        this.color = color;
        this.patente = patente;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAnio() {
        return anio;
    }

    public boolean esNuevo() {
        return condicionNuevo;
    }

    public String getColor() {
        return color;
    }
    public String getPatente() {
        return patente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Vehiculo)) return false;
        
        Vehiculo vehiculo = (Vehiculo) o;
        return patente.equalsIgnoreCase(vehiculo.patente);
    }

    @Override
    public int hashCode() {
        return patente.toUpperCase().hashCode();
    }

}