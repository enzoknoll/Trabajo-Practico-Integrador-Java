package concesionario.utilidades;

import concesionario.modelo.Vehiculo;

public interface UtilidadCrud {

    // CRUD para la Concesionaria y el taller
    void agregarVehiculo(Vehiculo nuevoVehiculo);
    void buscarVehiculo(String patenteBuscada);
    void eliminarVehiculo(String patente);
    void mostrarVehiculos();


}
