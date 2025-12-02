package concesionario.utilidades;

import concesionario.modelo.Vehiculo;

public interface utilidadCrud {

    // CRUD para la Concesionaria y el taller
    void agregarVehiculo(Vehiculo nuevoVehiculo);
    void buscarVehiculo(String patenteBuscada);
    void eliminarVehiculo(String patente);

}
