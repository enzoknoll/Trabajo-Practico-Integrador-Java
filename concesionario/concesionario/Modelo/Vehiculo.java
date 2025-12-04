package concesionario.modelo;

import java.io.Serializable;

public abstract class Vehiculo implements Serializable {
    // ID de version para la serialización
    private static final long serialVersionUID = 1L;

    private String marca;
    private String modelo;
    private int anio;
    private boolean condicionNuevo;
    private String color;
    private String patente; // aunque no se especifique en el TPI, se agrega el atributo patente para sobreescribir el metodo equals
    
    public Vehiculo() { // constructor vacío

    }

    public Vehiculo(String marca, String modelo, int anio, boolean condicionNuevo, String color, String patente) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.condicionNuevo = condicionNuevo;
        this.color = color;
        this.patente = patente;
    }

    // Setters
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setCondicionNuevo(boolean condicionNuevo) {
        this.condicionNuevo = condicionNuevo;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    // Getters
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
    // sobreescritura de equals y hashCode basados en la patente
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