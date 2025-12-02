package concesionario.negocio;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import concesionario.utilidades.*;
import concesionario.modelo.Auto;
import concesionario.modelo.Camioneta;
import concesionario.modelo.Moto;
import concesionario.modelo.Vehiculo;
import concesionario.excepciones.*;

public class Taller implements UtilidadCrud { 
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

    @Override
    public void mostrarVehiculos() {
        if (colaDeEspera == null || colaDeEspera.isEmpty()) {
            System.out.println("No hay vehículos en espera.");
            return;
        }

        System.out.println("\n=== LISTA DE ESPERA (ORDEN DE LLEGADA) ===");

        // 1. Separamos por tipo para visualizar mejor, PERO mantenemos el orden original
        //    para saber qué número de turno real le toca a cada uno.
        
        List<Vehiculo> autos = new ArrayList<>();
        List<Vehiculo> motos = new ArrayList<>();
        List<Vehiculo> camionetas = new ArrayList<>();

        // Usamos una lista auxiliar para guardar el número de turno de cada vehículo
        // Así, si el Auto llegó 1ro y la Moto 2da, conservan esos números aunque los mostremos en tablas separadas.
        List<Integer> turnosAutos = new ArrayList<>();
        List<Integer> turnosMotos = new ArrayList<>();
        List<Integer> turnosCamionetas = new ArrayList<>();

        int contadorGlobal = 1;

        for (Vehiculo v : colaDeEspera) {
            if (v instanceof Auto) {
                autos.add(v);
                turnosAutos.add(contadorGlobal);
            } else if (v instanceof Moto) {
                motos.add(v);
                turnosMotos.add(contadorGlobal);
            } else if (v instanceof Camioneta) {
                camionetas.add(v);
                turnosCamionetas.add(contadorGlobal);
            }
            contadorGlobal++; // El turno siempre avanza
        }

        // 2. Imprimimos las tablas pasando también la lista de números de turno
        if (!autos.isEmpty()) {
            imprimirTablaConTurnos("AUTOS EN ESPERA", autos, turnosAutos);
        }
        if (!motos.isEmpty()) {
            imprimirTablaConTurnos("MOTOS EN ESPERA", motos, turnosMotos);
        }
        if (!camionetas.isEmpty()) {
            imprimirTablaConTurnos("CAMIONETAS EN ESPERA", camionetas, turnosCamionetas);
        }
    }

    // --- MÉTODO AUXILIAR PRIVADO MEJORADO ---
    private void imprimirTablaConTurnos(String titulo, List<Vehiculo> grupo, List<Integer> turnos) {
        System.out.println("\n=== " + titulo + " ===");

        // A. Calculamos el ancho máximo considerando también el texto del turno
        int anchoMaximo = 0;
        
        // Primero vemos qué tan ancho es el contenido (Vehiculo + Turno)
        for (int i = 0; i < grupo.size(); i++) {
            String linea = "ORDEN " + turnos.get(i) + " | " + grupo.get(i).toString();
            if (linea.length() > anchoMaximo) {
                anchoMaximo = linea.length();
            }
        }

        // B. Dibujamos la tabla
        String borde = "═".repeat(anchoMaximo);
        System.out.println("╔" + borde + "╗");

        for (int i = 0; i < grupo.size(); i++) {
            // Construimos la línea: "ORDEN X | Datos del vehículo..."
            String texto = "ORDEN " + turnos.get(i) + " | " + grupo.get(i).toString();
            
            String relleno = " ".repeat(anchoMaximo - texto.length());
            System.out.println("║" + texto + relleno + "║");
        }
        System.out.println("╚" + borde + "╝");
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
