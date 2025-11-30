package concesionario;
public class Camioneta extends Vehiculo {
    private String tipoCarroceria;

    public Camioneta(String marca, String modelo, int anio, boolean condicionNuevo, String color, String patente, String tipoCarroceria) {
        super(marca, modelo, anio, condicionNuevo, color, patente);
        this.tipoCarroceria = tipoCarroceria;
    }
    public String getTipoCarroceria() {
        return tipoCarroceria;
    }
    @Override
    public String toString() {
        return "Camioneta{" +
                "marca='" + getMarca() + '\'' +
                ", modelo='" + getModelo() + '\'' +
                ", anio=" + getAnio() +
                ", condicionNuevo=" + esNuevo() +
                ", color='" + getColor() + '\'' +
                ", tipoCarroceria='" + tipoCarroceria + '\'' +
                '}';
    }
}
