package concesionario;
public class Moto extends Vehiculo {
    private String tipoMoto;
    public Moto(String marca, String modelo, int anio, boolean condicionNuevo, String color, String patente, String tipoMoto) {
        super(marca, modelo, anio, condicionNuevo, color, patente);
        this.tipoMoto = tipoMoto;
    }
    public String getTipoMoto() {
        return tipoMoto;
    }
    @Override
    public String toString() {
        return "Moto{" +
                "marca='" + getMarca() + '\'' +
                ", modelo='" + getModelo() + '\'' +
                ", anio=" + getAnio() +
                ", 0 KM=" + esNuevo() +
                ", color='" + getColor() + '\'' +
                ", tipoMoto='" + tipoMoto + '\'' +
                '}';
    }

}
