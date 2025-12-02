package concesionario.modelo;

public class Moto extends Vehiculo {
    private TipoMoto tipoMoto;

    public Moto(String marca, String modelo, int anio, boolean condicionNuevo, String color, String patente, TipoMoto tipoMoto) {
        super(marca, modelo, anio, condicionNuevo, color, patente);
        this.tipoMoto = tipoMoto;
    }
    public TipoMoto getTipoMoto() {
        return tipoMoto;
    }
    public enum TipoMoto {
        DEPORTIVA,
        CRUISER,
        TOURING,
        ESTANDAR,
        DUAL_SPORT
    }

    @Override
    public String toString() {
        String linea = " patente: " + getPatente() +
                   " | Año: " + getAnio() +
                   " | Marca: " + getMarca() +
                   " | Modelo: " + getModelo() +
                   " | Color: " + getColor() +
                   " | Condición: " + (esNuevo() ? "0 KM" : "Usado") +
                   " | Tipo de moto: " + tipoMoto + " ";

        String borde = "═".repeat(linea.length());

        return "╔" + borde + "╗\n" +
               "║" + linea + "║\n" +
               "╚" + borde + "╝";
    }

}
