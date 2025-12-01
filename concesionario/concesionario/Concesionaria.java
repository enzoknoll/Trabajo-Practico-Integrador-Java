package concesionario;

import concesionario.exceptions.*;
import concesionario.metodos.*;
import java.util.ArrayList;
import java.util.OptionalInt;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Concesionaria implements Metodos{

    private ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();

    @Override
    public void agregarVehiculo(Vehiculo nuevoVehiculo) {
        try {
            if (listaVehiculos == null) {
                throw new NullPointerException("La lista no está inicializada.");
            }
            if (listaVehiculos.contains(nuevoVehiculo)) {
                throw new VehiculoDuplicadoException("La patente " + nuevoVehiculo.getPatente() + " ya existe.");
            }
            listaVehiculos.add(nuevoVehiculo);
            System.out.println("Vehículo agregado exitosamente.");
        } catch (VehiculoDuplicadoException e) {
            System.out.println("Aviso: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void actualizarVehiculo(String patenteBuscada, Vehiculo nuevosDatos) {
        try {
            if (listaVehiculos == null) {
                throw new NullPointerException("La lista de vehiculos no está inicializada.");
            }
            OptionalInt indiceEncontrado = IntStream.range(0, listaVehiculos.size())
                .filter(i -> listaVehiculos.get(i).getPatente().equalsIgnoreCase(patenteBuscada))
                .findFirst();
            if (indiceEncontrado.isPresent()) {
                int indice = indiceEncontrado.getAsInt();
                Vehiculo actual = listaVehiculos.get(indice);
                if (!nuevosDatos.getPatente().equalsIgnoreCase(actual.getPatente()) 
                    && listaVehiculos.contains(nuevosDatos)) {
                      throw new IllegalArgumentException("No se puede actualizar: La nueva patente ya existe en otro vehículo.");
                }
                listaVehiculos.set(indice, nuevosDatos);
                System.out.println("Éxito: Vehículo con patente " + patenteBuscada + " actualizado.");

            } else {
                throw new IllegalArgumentException("No se pudo actualizar: Patente " + patenteBuscada + " no encontrada.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error de actualización: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Error grave: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    @Override
    public void buscarVehiculo(String patenteBuscada) {
        try {
            if (listaVehiculos == null) {
                throw new NullPointerException("La lista no está inicializada.");
            }
            boolean encontrada = false;
            for (Vehiculo v : listaVehiculos) {
                if (v.getPatente().equalsIgnoreCase(patenteBuscada)) {
                    System.out.println("---------------------------");
                    System.out.println("¡VEHÍCULO ENCONTRADO!");
                    System.out.println(v.toString()); 
                    System.out.println("---------------------------");
                    encontrada = true;
                    break;
                }
            }
            if (!encontrada) {
                System.out.println("No se encontró ningún vehículo con la patente: " + patenteBuscada);
            }
        } catch (NullPointerException e) {
            System.out.println("Error grave: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado al buscar: " + e.getMessage());
        }
    }

    @Override
    public void eliminarVehiculo(String patente) {
        try {
            if (listaVehiculos == null) {
                throw new NullPointerException("La lista no está inicializada.");
            }
            boolean fueEliminado = listaVehiculos.removeIf(v -> v.getPatente().equalsIgnoreCase(patente));
            if (!fueEliminado) {
                throw new IllegalArgumentException("No se encontró ningún vehículo con la patente: " + patente);
            }
            System.out.println("Éxito: Vehículo con patente " + patente + " eliminado.");
        } catch (IllegalArgumentException e) {
            System.out.println("Aviso: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Error grave: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    private static Vehiculo pedirDatosVehiculo(Scanner scanner) {
        int anio = 0;
        Menu.mostrarMenuVehiculo();
        System.out.print("Ingrese un vehiculo nuevo: ");
        int tipoVehiculo = scanner.nextInt();
        
        System.out.print("Ingrese marca del vehiculo: ");
        String marca = scanner.nextLine();

        System.out.print("Ingrese modelo del vehiculo: ");
        String modelo = scanner.nextLine();

        while (true) {
            try {
                System.out.print("Ingrese año del vehiculo: ");
                anio = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un año válido (solo números).");
            }
        }

        System.out.print("El auto es 0 KM? (True/False): ");
        boolean esNuevo = scanner.nextBoolean();

        System.out.print("Ingrese color del vehiculo: ");
        String color = scanner.nextLine();

        System.out.print("Ingrese patente del vehiculo: ");
        String patente = scanner.nextLine().toUpperCase();

        switch (tipoVehiculo){
            case 1:
                System.out.print("Ingrese carrocería del auto: ");
                String carroceria = scanner.nextLine();
                return new Auto(marca, modelo, anio, esNuevo, color, patente, carroceria);
            case 2:
                System.out.print("Ingrese tipo de moto: ");
                String tipoMoto = scanner.nextLine();
                return new Moto(marca, modelo, anio, esNuevo, color, patente, tipoMoto);
            case 3:
                System.out.print("Ingrese tipo de camioneta: ");
                String tipoCamioneta = scanner.nextLine();
                return new Camioneta(marca, modelo, anio, esNuevo, color, patente, tipoCamioneta);
            default:
                System.out.println("Tipo de vehículo inválido. Se cancelará la operación.");
                return null;
        }
    }

    public void mostrarVehiculos() {
        if (listaVehiculos == null || listaVehiculos.isEmpty()) {
            System.out.println("No hay vehículos registrados.");
            return;
        }
        for (Vehiculo v : listaVehiculos) {
            System.out.println(v.toString());
            System.out.println("---------------------------");
        }
    }
}