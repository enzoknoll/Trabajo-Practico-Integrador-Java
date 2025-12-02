package concesionario.modelo;

public class Auto extends Vehiculo {
    private TipoCarroceria tipoCarroceria;

    public Auto(String marca, String modelo, int anio, boolean condicionNuevo, String color, String patente, TipoCarroceria tipoCarroceria) {
        super(marca, modelo, anio, condicionNuevo, color, patente);
        this.tipoCarroceria = tipoCarroceria;
    }
    public TipoCarroceria getTipoCarroceria() {
        return tipoCarroceria;
    }

    public enum TipoCarroceria {
        SEDAN,
        HATCHBACK,
        COUPE,
        CONVERTIBLE
    }

    @Override
    public String toString() {
        String linea = " patente: " + getPatente() +
                   " | Año: " + getAnio() +
                   " | Marca: " + getMarca() +
                   " | Modelo: " + getModelo() +
                   " | Color: " + getColor() +
                   " | Condición: " + (esNuevo() ? "0 KM" : "Usado") +
                   " | Tipo de carrocería: " + tipoCarroceria + " ";

        String borde = "═".repeat(linea.length());

        return "╔" + borde + "╗\n" +
               "║" + linea + "║\n" +
               "╚" + borde + "╝";
    }
}
