package concesionario;

import java.util.LinkedList;
import java.util.Queue;

import concesionario.exceptions.VehiculoDuplicadoException;
import concesionario.metodos.*;

public class Taller implements Metodos { 
    Queue<Vehiculo> colaDeEspera = new LinkedList<>();

    @Override
    public void agregarVehiculo(Vehiculo nuevoVehiculo) {
        try {
            if (colaDeEspera == null) {
                throw new NullPointerException("La lista no está inicializada.");
            }
            if (colaDeEspera.contains(nuevoVehiculo)) {
                throw new VehiculoDuplicadoException("La patente " + nuevoVehiculo.getPatente() + " ya existe.");
            }
            colaDeEspera.offer(nuevoVehiculo);
            System.out.println("Vehículo agregado exitosamente.");
        } catch (VehiculoDuplicadoException e) {
            System.out.println("Aviso: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void mostrarVehiculos() {
        if (colaDeEspera == null || colaDeEspera.isEmpty()) {
            System.out.println("No hay vehículos registrados.");
            return;
        }
        for (Vehiculo v : colaDeEspera) {
            System.out.println(v.toString());
            System.out.println("---------------------------");
        }
    }

    @Override
    public void buscarVehiculo(String patenteBuscada) {
        try {
            if (colaDeEspera == null) {
                throw new NullPointerException("La lista no está inicializada.");
            }
            boolean encontrada = false;
            for (Vehiculo v : colaDeEspera) {
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
            if (colaDeEspera == null) {
                throw new NullPointerException("La cola no está inicializada.");
            }
            boolean fueEliminado = colaDeEspera.removeIf(v -> v.getPatente().equalsIgnoreCase(patente));
            if (!fueEliminado) {
                throw new IllegalArgumentException("No se encontró ningún vehículo con la patente: " + patente);
            }
            System.out.println("Éxito: Vehículo con patente " + patente + " sacado de la cola.");
        } catch (IllegalArgumentException e) {
            System.out.println("Aviso: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void atenderSiguienteVehiculo(Concesionaria vehiculo) {
        vehiculo.agregarVehiculo(colaDeEspera.peek());
        Vehiculo atendido = colaDeEspera.poll();

        if (atendido != null) {
            String linea1 = " Atendiendo y despachando a: " + atendido.getMarca() + " " + atendido.getModelo() + " ";
            String linea2 = " Patente: " + atendido.getPatente() + " ";
            String linea3 = " ¡Vehiculo lavado y detallado para la exhibición! ";
            String linea4 = " ¡Vehículo listo y enviado a la concesionaria para su venta! ";

            int anchoMaximo = Math.max(linea1.length(), Math.max(linea2.length(), Math.max(linea3.length(), linea4.length())));

            String pad1 = " ".repeat(anchoMaximo - linea1.length());
            String pad2 = " ".repeat(anchoMaximo - linea2.length());
            String pad3 = " ".repeat(anchoMaximo - linea3.length());
            String pad4 = " ".repeat(anchoMaximo - linea4.length());
            
            String borde = "═".repeat(anchoMaximo);

            System.out.println("╔" + borde + "╗");
            System.out.println("║" + linea1 + pad1 + "║");
            System.out.println("║" + linea2 + pad2 + "║");
            System.out.println("║" + linea3 + pad3 + "║");
            System.out.println("║" + linea4 + pad4 + "║");
            System.out.println("╚" + borde + "╝");
        } else {
            System.out.println("No hay vehículos en espera. El taller está libre.");
        }
    }

    public void verProximoTurno() {
        Vehiculo siguiente = colaDeEspera.peek();
        
        if (siguiente != null) {
            System.out.println("El próximo turno es para: ");
            System.out.println(siguiente.toString());
        } else {
            System.out.println("La cola está vacía.");
        }
    }

    public void cantidadEnEspera() {
        try{
            
            if (colaDeEspera == null) {
                throw new NullPointerException("La lista no está inicializada.");
            }
            String linea = " Vehículos esperando: " + colaDeEspera.size() + " ";

            String borde = "═".repeat(linea.length());

            System.out.println("╔" + borde + "╗\n" +
                               "║" + linea + "║\n" +
                               "╚" + borde + "╝");
        } catch (NullPointerException e) {
            System.out.println("Error grave: " + e.getMessage());
            return;
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            return;
        }
    }
}
