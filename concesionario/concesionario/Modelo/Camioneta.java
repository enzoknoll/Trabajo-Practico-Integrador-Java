package concesionario.modelo;

public class Camioneta extends Vehiculo {
    private TipoCarroceria tipoCarroceria;

    public Camioneta() {

    }

    public Camioneta(String marca, String modelo, int anio, boolean condicionNuevo, String color, String patente, TipoCarroceria tipoCarroceria) {
        super(marca, modelo, anio, condicionNuevo, color, patente);
        this.tipoCarroceria = tipoCarroceria;
    }

    public void setTipoCarroceria(TipoCarroceria tipoCarroceria) {
        this.tipoCarroceria = tipoCarroceria;
    }

    public TipoCarroceria getTipoCarroceria() {
        return tipoCarroceria;
    }

    public enum TipoCarroceria {
        PICKUP,
        TRACCION_4X4,
        SUV,
        VAN,
        CROSSOVER
    }

    @Override
    public String toString() {
        return " Patente: " + getPatente() +
            " | Año: " + getAnio() +
            " | Marca: " + getMarca() +
            " | Modelo: " + getModelo() +
            " | Color: " + getColor() +
            " | Condición: " + (esNuevo() ? "0 KM" : "Usado") +
            " | Carrocería: " + getTipoCarroceria() + " ";
    }
}
