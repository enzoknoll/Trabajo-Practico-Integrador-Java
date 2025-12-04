package concesionario.vista;

import java.util.Scanner;

import concesionario.modelo.*;
import concesionario.negocio.*;
import concesionario.utilidades.*;

public class Main {
    public static void main(String[] args) {
        // 1. INICIALIZACIÓN DE OBJETOS PRINCIPALES para las listas
        Concesionaria vehiculo = new Concesionaria();
        Taller taller = new Taller();
        // Scanner para la entrada de datos
        Scanner scanner = new Scanner(System.in);

        // 2. CARGA DE DATOS DESDE TXT
        System.out.println("Iniciando carga de datos...");
        
        // Asegúrate de que los archivos .txt estén en la carpeta raíz del proyecto
        CargaDatos.cargarConcesionaria("Trabajo-Practico-Integrador-Java/vehiculos_concesionaria.txt", vehiculo);
        CargaDatos.cargarTaller("Trabajo-Practico-Integrador-Java/vehiculos_taller.txt", taller);
        
        System.out.println("\nSistema inicializado correctamente.\n");

        // 3. MENÚ PRINCIPAL - BUCLE INFINITO HASTA QUE EL USUARIO DECIDA SALIR
        while (true){
            Menu.mostrarMenu();
            try {
                switch (scanner.nextInt()) {
                case 1:
                    Vehiculo nuevoVehiculo = pedirDatosVehiculo(scanner, false, false);
                    if (nuevoVehiculo != null) {
                        vehiculo.agregarVehiculo(nuevoVehiculo);
                        System.out.println("Vehículo agregado exitosamente.");
                    }
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

                    Vehiculo VehiculoModificado = pedirDatosVehiculo(scanner, false, true);

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
                break;
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, seleccione una opción del menú: ");
                scanner.next(); // Limpiar el buffer de entrada
            }
        }
            scanner.close();
    }

    private static Vehiculo pedirDatosVehiculo(Scanner scanner, boolean paraTaller, boolean esActualizacion) {
        int anio;
        if(!esActualizacion){
            Menu.mostrarMenuVehiculo();
            System.out.print("Ingrese un vehiculo nuevo: ");
        } else {
            Menu.mostrarMenuActualizarVehiculo();
            System.out.print("Ingrese el tipo de vehiculo a actualizar: ");
        }
        int tipoVehiculo; // 1=Auto, 2=Moto, 3=Camioneta, 4=Volver
        while(true){
            tipoVehiculo = scanner.nextInt();
            scanner.nextLine(); // Se limpia el buffer del scanner
            if (tipoVehiculo < 1 || tipoVehiculo > 4){
                System.out.print("Opción inválida. Ingrese un número del 1 al 4: ");
                continue;
            } else if (tipoVehiculo == 4){
            System.out.println("Volviendo al menú principal");
            return null;
            } else {
                break;
            }
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
        boolean esNuevo = false; // inicialización por defecto
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
        
        while (true){ // Bucle para manejar excepciones de tipos inválidos
            try{
                switch (tipoVehiculo){
                    case 1:
                        System.out.print("Ingrese carrocería del auto (SEDAN | HATCHBACK | COUPE | CONVERTIBLE): ");
                        String carroceriaAutoInput = scanner.nextLine().toUpperCase();
                        Auto.TipoCarroceria carroceria = Auto.TipoCarroceria.valueOf(carroceriaAutoInput); // Convertimos String a Enum de Auto
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
// Menú específico para el Taller
    public static void menuTaller(Scanner scanner, Concesionaria vehiculo, Taller taller) {
        while (true){
            Menu.mostrarMenuTaller();
            try{
                switch(scanner.nextInt()){
                    case 1:
                        Vehiculo nuevoVehiculo = pedirDatosVehiculo(scanner, true, false);
                        if (nuevoVehiculo != null) {
                            taller.agregarVehiculo(nuevoVehiculo);
                            System.out.println("Vehículo agregado al taller exitosamente.");
                        }
                        continue;
                    case 2:
                        taller.mostrarVehiculos();
                        continue;
                    case 3:
                        System.out.print("Ingrese patente del vehiculo a buscar: ");
                        taller.buscarVehiculo(scanner.next()); // Se usa next() porque la patente no tiene espacios
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
                System.out.println("Entrada inválida. Por favor, seleccione una opción del menú: ");
                scanner.next(); // Limpiar el buffer de entrada
            }
        }
    }

}