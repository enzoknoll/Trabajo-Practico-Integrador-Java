package concesionario.negocio;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import concesionario.utilidades.*;
import concesionario.modelo.*;
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
                    String titulo = "      ¡VEHÍCULO ENCONTRADO! ";
                    String datosVehiculo = v.toString() + " "; 
                    
                    int ancho = datosVehiculo.length();
                    String padTitulo = " ".repeat(ancho - titulo.length());
                    String borde = "═".repeat(ancho);

                    System.out.println("╔" + borde + "╗");
                    System.out.println("║" + titulo + padTitulo + "║");
                    System.out.println("╠" + borde + "╣");
                    System.out.println("║" + datosVehiculo + "║");
                    System.out.println("╚" + borde + "╝");
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

    public void atenderSiguienteVehiculo(Scanner scanner, Concesionaria vehiculo) { // se pasa por parametro la lista de la concesionaria para consultar si hay duplicidad
        try{
            if (colaDeEspera == null) {
                throw new NullPointerException();
            }
            boolean yaExiste = vehiculo.getListaVehiculos().stream().anyMatch(v -> v.getPatente().equalsIgnoreCase(colaDeEspera.peek().getPatente())); // .peek() devuelve el primer elemento sin sacarlo de la cola
            if (!yaExiste){
                vehiculo.agregarVehiculo(colaDeEspera.peek()); // se agrega el vehiculo a la concesionaria antes de sacarlo de la cola
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
            } else {
                System.out.println("El vehículo con patente " + colaDeEspera.peek().getPatente() + " ya existe en la concesionaria. No se puede atender.");
                String linea1 = " 1 - Eliminar vehículo del taller sin atenderlo. ";
                String linea2 = " 2 - Modificar patente. ";
                String linea3 = " 3 - Volver al menú del taller. ";
                int anchoMaximo = Math.max(linea1.length(), Math.max(linea2.length(), linea3.length()));
                String pad1 = " ".repeat(anchoMaximo - linea1.length());
                String pad2 = " ".repeat(anchoMaximo - linea2.length());
                String pad3 = " ".repeat(anchoMaximo - linea3.length());
                String borde = "═".repeat(anchoMaximo);
                System.out.println("╔" + borde + "╗");
                System.out.println("║" + linea1 + pad1 + "║");
                System.out.println("║" + linea2 + pad2 + "║");
                System.out.println("║" + linea3 + pad3 + "║");
                System.out.println("╚" + borde + "╝");
                System.out.print("Elige una opción: ");
                switch(scanner.nextInt()){
                    case 1:
                        eliminarVehiculo(colaDeEspera.peek().getPatente());
                        break;
                    case 2:
                        scanner.nextLine(); // Limpiar el buffer del scanner para que no haya pulsadores fantasmas
                        System.out.print("Ingrese nueva patente: ");
                        String nuevaPatente = scanner.nextLine().toUpperCase();
                        boolean existeNuevaPatente = vehiculo.getListaVehiculos().stream().anyMatch(v -> v.getPatente().equalsIgnoreCase(nuevaPatente)) ||
                                                    colaDeEspera.stream().anyMatch(v -> v.getPatente().equalsIgnoreCase(nuevaPatente));
                        if (!existeNuevaPatente){
                            Vehiculo v = colaDeEspera.peek();
                            v.setPatente(nuevaPatente);
                            System.out.println("Patente modificada exitosamente. Ahora atendiendo el vehículo:");
                            atenderSiguienteVehiculo(scanner, vehiculo);
                        } else {
                            System.out.println("La patente " + nuevaPatente + " ya existe. No se puede modificar.");
                        }
                        break;
                    case 3:
                        System.out.println("Volviendo al menú del taller.");
                        break;
                    default:
                        System.out.println("Opción inválida. Volviendo al menú del taller.");
                        break;
                }
                
            }
        } catch (NullPointerException e){
            System.out.println("¡La cola está vacía!");
        } catch (Exception e){
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    public void verProximoTurno() { // muestra el siguiente vehiculo en la cola sin sacarlo
        Vehiculo siguiente = colaDeEspera.peek();
        
        if (siguiente != null) {
            String titulo = "      El próximo turno es para: ";
            String datosVehiculo = siguiente.toString() + " "; 
            int ancho = datosVehiculo.length();
            String padTitulo = " ".repeat(ancho - titulo.length());
            String borde = "═".repeat(ancho);
            System.out.println("╔" + borde + "╗");
            System.out.println("║" + titulo + padTitulo + "║");
            System.out.println("╠" + borde + "╣");
            System.out.println("║" + datosVehiculo + "║");
            System.out.println("╚" + borde + "╝");
        } else {
            System.out.println("¡La cola está vacía!");
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
