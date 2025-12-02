package concesionario.utilidades;

import concesionario.modelo.*;
import concesionario.negocio.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CargaDatos {

    // Cargar archivo en el ArrayList de la Concesionaria
    public static void cargarConcesionaria(String ruta, Concesionaria concesionaria) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            int contador = 0;
            while ((linea = br.readLine()) != null) {
                Vehiculo v = parsearVehiculo(linea);
                if (v != null) {
                    concesionaria.agregarVehiculo(v);
                    contador++;
                }
            }
            System.out.println("-> Se cargaron " + contador + " vehículos en la Concesionaria.");
        } catch (IOException e) {
            System.out.println("Error leyendo archivo Concesionaria: " + e.getMessage());
        }
    }

    // Cargar archivo en la LinkedList (Queue) del Taller
    public static void cargarTaller(String ruta, Taller taller) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            int contador = 0;
            while ((linea = br.readLine()) != null) {
                Vehiculo v = parsearVehiculo(linea);
                if (v != null) {
                    taller.agregarVehiculo(v);
                    contador++;
                }
            }
            System.out.println("-> Se cargaron " + contador + " vehículos en la cola del Taller.");
        } catch (IOException e) {
            System.out.println("Error leyendo archivo Taller: " + e.getMessage());
        }
    }

    // Método privado auxiliar para convertir texto a objeto Vehiculo
    private static Vehiculo parsearVehiculo(String linea) {
        try {
            String[] d = linea.split(",");            
            String tipo = d[0].trim().toUpperCase();
            String marca = d[1].trim();
            String modelo = d[2].trim();
            int anio = Integer.parseInt(d[3].trim()); // Usamos el método parseInt de la clase Wrapper Integer
            boolean nuevo = Boolean.parseBoolean(d[4].trim()); // Usamos el método parseBoolean de la clase Wrapper Boolean
            String color = d[5].trim();
            String patente = d[6].trim();
            String tipoEspecifico = d[7].trim().toUpperCase(); // Para el Enum

            switch (tipo) {
                case "AUTO":
                    // Convertimos String a Enum de Auto
                    Auto.TipoCarroceria tipoAuto = Auto.TipoCarroceria.valueOf(tipoEspecifico);
                    return new Auto(marca, modelo, anio, nuevo, color, patente, tipoAuto);

                case "MOTO":
                    // Convertimos String a Enum de Moto
                    Moto.TipoMoto tipoMoto = Moto.TipoMoto.valueOf(tipoEspecifico);
                    return new Moto(marca, modelo, anio, nuevo, color, patente, tipoMoto);

                case "CAMIONETA":
                    // Convertimos String a Enum de Camioneta
                    Camioneta.TipoCarroceria tipoCamioneta = Camioneta.TipoCarroceria.valueOf(tipoEspecifico);
                    return new Camioneta(marca, modelo, anio, nuevo, color, patente, tipoCamioneta);

                default:
                    return null;
            }
        } catch (Exception e) {
            System.out.println("Error en línea: " + linea + " (" + e.getMessage() + ")");
            return null;
        }
    }
}