package concesionario;

public class Main {
    public static void main(String[] args) {
        
        // 1. Instanciamos la clase que tiene la lógica (la "Gestora")
        Concesionaria concesionaria = new Concesionaria();

        // 2. Creamos los objetos específicos (Hijos)
        // --- Autos ---
        Auto miAuto = new Auto("Toyota", "Corolla", 2022, true, "Blanco", "AA111BB", "Sedan");
        Auto miAuto2 = new Auto("Chevrolet", "Cruze", 2020, true, "Azul", "AA333CC", "Sedan");
        Auto miAuto3 = new Auto("Volkswagen", "Golf", 2021, true, "Rojo", "AA555DD", "Hatchback");
        Auto miAuto4 = new Auto("Nissan", "Sentra", 2022, true, "Negro", "AA777EE", "Sedan");

        // --- Motos ---
        Moto miMoto = new Moto("Honda", "CBR", 2023, true, "Rojo", "MOTO999", "Deportiva");
        Moto miMoto2 = new Moto("Yamaha", "YZF-R3", 2022, true, "Blanco", "MOTO888", "Deportiva");
        Moto miMoto3 = new Moto("Kawasaki", "Ninja 400", 2023, true, "Verde", "MOTO777", "Deportiva");
        Moto miMoto4 = new Moto("Ducati", "Panigale V2", 2024, true, "Rojo", "MOTO666", "Deportiva");

        // --- Camionetas ---
        Camioneta miCamioneta = new Camioneta("Ford", "Raptor", 2021, false, "Negro", "CC222DD", "4x4");
        Camioneta miCamioneta2 = new Camioneta("Toyota", "Hilux", 2019, false, "Gris", "CC444EE", "4x4");
        Camioneta miCamioneta3 = new Camioneta("Chevrolet", "Silverado", 2020, false, "Blanco", "CC666FF", "Pickup");
        Camioneta miCamioneta4 = new Camioneta("Ram", "1500", 2021, false, "Azul", "CC888GG", "Pickup");


        // -------------------------------------------------
        // PRUEBA DEL CRUD
        // -------------------------------------------------

        // A) AGREGAR (CREATE)
        // Fíjate que el método pide 'Vehiculo', pero le pasamos 'Auto', 'Moto', etc.
        // Esto se llama POLIMORFISMO. Funciona solo.
        System.out.println("--- AGREGANDO VEHÍCULOS ---");
        concesionaria.agregarVehiculo(miAuto);
        concesionaria.agregarVehiculo(miAuto2);
        concesionaria.agregarVehiculo(miAuto3);
        concesionaria.agregarVehiculo(miAuto4);
        concesionaria.agregarVehiculo(miMoto);
        concesionaria.agregarVehiculo(miMoto2);
        concesionaria.agregarVehiculo(miMoto3);
        concesionaria.agregarVehiculo(miMoto4);        
        concesionaria.agregarVehiculo(miCamioneta);
        concesionaria.agregarVehiculo(miCamioneta2);
        concesionaria.agregarVehiculo(miCamioneta3);
        concesionaria.agregarVehiculo(miCamioneta4);

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