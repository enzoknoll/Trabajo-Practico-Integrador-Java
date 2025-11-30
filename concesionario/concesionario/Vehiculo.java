package concesionario;

public class Vehiculo {
    private String marca;
    private String modelo;
    private int anio;
    private boolean condicionNuevo;
    private String color;
    

    public Vehiculo(String marca, String modelo, int anio, boolean condicionNuevo, String color) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.condicionNuevo = condicionNuevo;
        this.color = color;
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

}