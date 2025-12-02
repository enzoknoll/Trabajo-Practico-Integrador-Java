package concesionario.Vista;

public class Menu {
    public static void mostrarMenu() {
    System.out.println("╔══════════════════════════════════╗");
    System.out.println("║           MENÚ PRINCIPAL         ║");
    System.out.println("╠══════════════════════════════════╣");
    System.out.println("║ 1. Agregar vehículo              ║");
    System.out.println("║ 2. Mostrar vehículos             ║");
    System.out.println("║ 3. Buscar vehículo por patente   ║");
    System.out.println("║ 4. Actualizar vehículo           ║");
    System.out.println("║ 5. Eliminar vehículo             ║");
    System.out.println("║ 6. Taller                        ║");
    System.out.println("║ 7. Salir                         ║");
    System.out.println("╚══════════════════════════════════╝");
    System.out.print("Seleccione una opción: ");
    }

    public static void mostrarMenuVehiculo() {
    System.out.println("╔══════════════════════════════════╗");
    System.out.println("║      MENÚ AGREGAR VEHICULO       ║");
    System.out.println("╠══════════════════════════════════╣");
    System.out.println("║ 1. Auto                          ║");
    System.out.println("║ 2. Moto                          ║");
    System.out.println("║ 3. Camioneta                     ║");
    System.out.println("║ 4.<<═╗ Volver al menú principal  ║");
    System.out.println("╚══════════════════════════════════╝");
    System.out.print("Seleccione una opción: ");
    }

    public static void mostrarMenuTaller() {
    System.out.println("╔═════════════════════════════════════╗");
    System.out.println("║           MENÚ TALLER               ║");
    System.out.println("╠═════════════════════════════════════╣");
    System.out.println("║ 1. Agregar vehículo al final        ║");
    System.out.println("║ 2. Mostrar vehículos en el taller   ║");
    System.out.println("║ 3. Buscar vehículo por patente      ║");
    System.out.println("║ 4. Ver cantidad en la cola          ║");
    System.out.println("║ 5. Eliminar vehículo del taller     ║");
    System.out.println("║ 6. Ver siguiente en la cola         ║");
    System.out.println("║ 7. Atender proximo vehículo         ║");
    System.out.println("║ 8.<<═╗ Volver al menú principal     ║");
    System.out.println("╚═════════════════════════════════════╝");
    System.out.print("Seleccione una opción: ");
    }
}