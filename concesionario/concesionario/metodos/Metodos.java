package concesionario.metodos;

import concesionario.Vehiculo;

public interface Metodos {

    // CRUD para la Concesionaria y el taller
    void agregarVehiculo(Vehiculo nuevoVehiculo);
    void buscarVehiculo(String patenteBuscada);
    void eliminarVehiculo(String patente);

}
