package concesionario;

public class Main {
    public static void main(String[] args) {
        
        // 1. Instanciamos la clase que tiene la lógica (la "Gestora")
        Concesionaria concesionaria = new Concesionaria();

        // 2. Creamos los objetos específicos (Hijos)
        Auto miAuto = new Auto("Toyota", "Corolla", 2022, true, "Blanco", "AA111BB", "Sedan");
        Moto miMoto = new Moto("Honda", "CBR", 2023, true, "Rojo", "MOTO999", "Deportiva");
        Camioneta miCamioneta = new Camioneta("Ford", "Raptor", 2021, false, "Negro", "CC222DD", "4x4");

        // -------------------------------------------------
        // PRUEBA DEL CRUD
        // -------------------------------------------------

        // A) AGREGAR (CREATE)
        // Fíjate que el método pide 'Vehiculo', pero le pasamos 'Auto', 'Moto', etc.
        // Esto se llama POLIMORFISMO. Funciona solo.
        System.out.println("--- AGREGANDO VEHÍCULOS ---");
        concesionaria.agregarVehiculo(miAuto);
        concesionaria.agregarVehiculo(miMoto);
        concesionaria.agregarVehiculo(miCamioneta);

        // B) BUSCAR (READ)
        System.out.println("\n--- BUSCANDO ---");
        concesionaria.buscarVehiculo("AA111BB"); // Busca el auto
        concesionaria.buscarVehiculo("MOTO999"); // Busca la moto

        // C) ACTUALIZAR (UPDATE)
        System.out.println("\n--- ACTUALIZANDO ---");
        // Para actualizar, creamos un objeto nuevo con los datos corregidos
        // OJO: Debe ser del mismo tipo (Si era Moto, pasas una Moto nueva)
        Moto motoActualizada = new Moto("Honda", "CBR Fireblade", 2024, true, "Azul", "MOTO999", "Super Sport");
        
        concesionaria.actualizarVehiculo("MOTO999", motoActualizada);
        
        // Verificamos el cambio buscando de nuevo
        concesionaria.buscarVehiculo("MOTO999");

        // D) ELIMINAR (DELETE)
        System.out.println("\n--- ELIMINANDO ---");
        concesionaria.eliminarVehiculo("CC222DD"); // Borramos la camioneta
        
        // Intentamos buscarla para confirmar que ya no está
        concesionaria.buscarVehiculo("CC222DD");
    }
}