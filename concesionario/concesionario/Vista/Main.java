package concesionario.vista;

import java.util.Scanner;

import concesionario.modelo.*;
import concesionario.negocio.*;
import concesionario.utilidades.*;

public class Main {
    public static void main(String[] args) {
        
        Concesionaria vehiculo = new Concesionaria();
        Taller taller = new Taller();

        Scanner scanner = new Scanner(System.in);

        // 2. CARGA DE DATOS DESDE TXT
        System.out.println("Iniciando carga de datos...");
        
        // Asegúrate de que los archivos .txt estén en la carpeta raíz del proyecto
        CargaDatos.cargarConcesionaria("Trabajo-Practico-Integrador-Java/vehiculos_concesionaria.txt", vehiculo);
        CargaDatos.cargarTaller("Trabajo-Practico-Integrador-Java/vehiculos_taller.txt", taller);
        
        System.out.println("\nSistema inicializado correctamente.\n");

        while (true){
            Menu.mostrarMenu();
            try {
                switch (scanner.nextInt()) {
                case 1:
                    Vehiculo nuevoVehiculo = pedirDatosVehiculo(scanner, false);
                    vehiculo.agregarVehiculo(nuevoVehiculo);
                    System.out.println("Vehículo agregado exitosamente.");
                    continue;
                case 2:
                    System.out.print("\n================================ Lista de vehículos =====================================\n");
                    vehiculo.mostrarVehiculos();
                    continue;
                case 3:
                    System.out.print("Ingrese patente para buscar: ");
                    scanner.nextLine(); // Limpiar el buffer del scanner
                    vehiculo.buscarVehiculo(scanner.nextLine().toUpperCase());
                    continue;
                case 4:
                    scanner.nextLine(); // Limpiar el buffer del scanner
                    System.out.print("Ingrese la patente del vehiculo que quiere modificar: ");
                    String patenteModificada = scanner.nextLine().toUpperCase();

                    Vehiculo VehiculoModificado = pedirDatosVehiculo(scanner, false);

                    vehiculo.actualizarVehiculo(patenteModificada, VehiculoModificado);
                    vehiculo.mostrarVehiculos();
                    continue;
                case 5:
                    scanner.nextLine(); // Limpiar el buffer del scanner
                    System.out.print("Ingrese patente del vehiculo a eliminar: ");
                    vehiculo.eliminarVehiculo(scanner.nextLine().toUpperCase());
                    vehiculo.mostrarVehiculos();
                    continue;
                case 6:
                    menuTaller(scanner, vehiculo, taller);
                    continue;
                case 7:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción del menú.");
                    continue;
                }
                break; // Salir del bucle si la opción es 6
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, seleccione una opción del menú: ");
                scanner.next(); // Limpiar el buffer de entrada
            }
        }
            scanner.close();
    }

    private static Vehiculo pedirDatosVehiculo(Scanner scanner, boolean paraTaller) {
        int anio;
        Menu.mostrarMenuVehiculo();
        
        System.out.print("Ingrese un vehiculo nuevo: ");
        int tipoVehiculo = scanner.nextInt();
        scanner.nextLine(); // Se limpia el buffer del scanner
        if (tipoVehiculo == 4){
            System.out.println("Volviendo al menú principal");
            return null;
        }
        
        System.out.print("Ingrese marca del vehiculo: ");
        String marca = scanner.nextLine();

        System.out.print("Ingrese modelo del vehiculo: ");
        String modelo = scanner.nextLine();

        while (true) {
            try {
                System.out.print("Ingrese año del vehiculo: ");
                anio = Integer.parseInt(scanner.nextLine()); // Usamos el método parseInt de la clase Wrapper Integer
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un año válido (solo números).");
            }
        }
        boolean esNuevo = false;
        while (true && !paraTaller) {
            try {
                // Se hace la pregunta de si es 0km o no porque existe la posibilidad de que un auto usado no necesite pasar por el taller para ir a la concesionaria
                System.out.print("El auto es 0 KM? (True/False): ");
                esNuevo = scanner.nextBoolean();
                scanner.nextLine(); // Limpiar el buffer del scanner
                break;
            } catch (Exception e) {
                System.out.println("Error: Ingrese True o False.");
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        }
        System.out.print("Ingrese color del vehiculo: ");
        String color = scanner.nextLine();

        System.out.print("Ingrese patente del vehiculo: ");
        String patente = scanner.nextLine().toUpperCase();
        
        while (true){
            try{
                switch (tipoVehiculo){
                    case 1:
                        System.out.print("Ingrese carrocería del auto (SEDAN | HATCHBACK | COUPE | CONVERTIBLE): ");
                        String carroceriaAutoInput = scanner.nextLine().toUpperCase();
                        Auto.TipoCarroceria carroceria = Auto.TipoCarroceria.valueOf(carroceriaAutoInput);
                        return new Auto(marca, modelo, anio, esNuevo, color, patente, carroceria);
                    case 2:
                        System.out.print("Ingrese tipo de moto (DEPORTIVA | CRUISER | TOURING | ESTANDAR | DUAL_SPORT): ");
                        String TipoMotoInput = scanner.nextLine().toUpperCase();
                        Moto.TipoMoto TipoMoto = Moto.TipoMoto.valueOf(TipoMotoInput);
                        return new Moto(marca, modelo, anio, esNuevo, color, patente, TipoMoto);
                    case 3:
                        System.out.print("Ingrese tipo de camioneta (PICKUP | TRACCION_4X4 | SUV | VAN | CROSSOVER): ");
                        String carroceriaCamionetaInput = scanner.nextLine().toUpperCase();
                        Camioneta.TipoCarroceria tipoCamioneta = Camioneta.TipoCarroceria.valueOf(carroceriaCamionetaInput);
                        return new Camioneta(marca, modelo, anio, esNuevo, color, patente, tipoCamioneta);
                    default:
                        System.out.println("Tipo de vehículo inválido. Se cancelará la operación.");
                        return null;
                }
            } catch (IllegalArgumentException e){
                System.out.println("Error: Tipo inválido. Por favor, ingrese un tipo válido.");
            } 
        }      
    }

    public static void menuTaller(Scanner scanner, Concesionaria vehiculo, Taller taller) {
        while (true){
            Menu.mostrarMenuTaller();
            try{
                switch(scanner.nextInt()){
                    case 1:
                        Vehiculo nuevoVehiculo = pedirDatosVehiculo(scanner, true);
                        taller.agregarVehiculo(nuevoVehiculo);
                        System.out.println("Vehículo agregado al taller exitosamente.");
                        continue;
                    case 2:
                        taller.mostrarVehiculos();
                        continue;
                    case 3:
                        System.out.print("Ingrese patente del vehiculo a buscar: ");
                        taller.buscarVehiculo(scanner.next());
                        continue;
                    case 4:
                        taller.cantidadEnEspera();
                        continue;
                    case 5:
                        System.out.print("Ingrese patente del vehiculo a eliminar: ");
                        taller.eliminarVehiculo(scanner.next());
                        continue;
                    case 6:
                        taller.verProximoTurno();
                        continue;
                    case 7:
                        taller.atenderSiguienteVehiculo(scanner, vehiculo);
                        continue;
                    case 8:
                        System.out.println("Volviendo al menú principal");
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, seleccione una opción del menú.");
                        break;
                }
                break;
            } catch (Exception e){
                System.out.println("Ingrese un número válido del 1 al 7: "+ e.getMessage());
            }
        }
    }

}