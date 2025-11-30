package concesionario;

//import concesionario.exceptions.*;
//import java.util.ArrayList;

public class Auto extends Vehiculo {
    private String tipoCarroceria;
//    private ArrayList<Auto> listaAutos = new ArrayList<>();

    public Auto(String marca, String modelo, int anio, boolean condicionNuevo, String color, String patente, String tipoCarroceria) {
        super(marca, modelo, anio, condicionNuevo, color, patente);
        this.tipoCarroceria = tipoCarroceria;
    }
    public String getTipoCarroceria() {
        return tipoCarroceria;
    }

//    public void agregarAuto(Auto nuevoAuto) {
//        try {
//            if (listaAutos == null) {
//                throw new NullPointerException("La lista no está inicializada.");
//            }
//            if (listaAutos.contains(nuevoAuto)) {
//                throw new VehiculoDuplicadoException("La patente " + nuevoAuto.getPatente() + " ya existe.");
//            }
//            listaAutos.add(nuevoAuto);
//            System.out.println("Auto agregado.");
//        } catch (VehiculoDuplicadoException e) {
//            System.out.println("Aviso: " + e.getMessage());
//        } catch (Exception e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }
//
//    public void actualizarAuto(String patenteBuscada, Auto nuevosDatos) {
//        try {
//            if (listaAutos == null) {
//                throw new NullPointerException("La lista no está inicializada.");
//            }
//            boolean actualizado = false;
//            for (int i = 0; i < listaAutos.size(); i++) {
//                Auto actual = listaAutos.get(i);
//                if (actual.getPatente().equalsIgnoreCase(patenteBuscada)) {
//                    if (!nuevosDatos.getPatente().equalsIgnoreCase(actual.getPatente()) 
//                        && listaAutos.contains(nuevosDatos)) {
//                        throw new IllegalArgumentException("No se puede actualizar: La nueva patente ya existe en otro vehículo.");
//                    }
//                    listaAutos.set(i, nuevosDatos);
//                    System.out.println("Éxito: Los datos del auto " + patenteBuscada + " han sido actualizados.");
//                    actualizado = true;
//                    break; 
//                }
//            }
//            if (!actualizado) {
//                throw new IllegalArgumentException("No se pudo actualizar: Patente " + patenteBuscada + " no encontrada.");
//            }
//        } catch (IllegalArgumentException e) {
//            System.out.println("Error de actualización: " + e.getMessage());
//        } catch (NullPointerException e) {
//            System.out.println("Error grave: " + e.getMessage());
//        } catch (Exception e) {
//            System.out.println("Error inesperado: " + e.getMessage());
//        }
//    }
//
//    public void buscarAuto(String patenteBuscada) {
//        try {
//            if (listaAutos == null) {
//                throw new NullPointerException("La lista no está inicializada.");
//            }
//            boolean encontrada = false;
//            for (Auto a : listaAutos) {
//                if (a.getPatente().equalsIgnoreCase(patenteBuscada)) {
//                    System.out.println("---------------------------");
//                    System.out.println("¡VEHÍCULO ENCONTRADO!");
//                    System.out.println(a.toString());
//                    System.out.println("---------------------------");
//                    encontrada = true;
//                    break;
//                }
//            }
//            if (!encontrada) {
//                System.out.println("No se encontró ningún auto con la patente: " + patenteBuscada);
//            }
//        } catch (NullPointerException e) {
//            System.out.println("Error grave: " + e.getMessage());
//        } catch (Exception e) {
//            System.out.println("Ocurrió un error inesperado al buscar: " + e.getMessage());
//        }
//    }
//
//    public void eliminarAuto(String patente) {
//        try {
//            if (listaAutos == null) {
//                throw new NullPointerException("La lista no está inicializada.");
//            }
//            boolean fueEliminado = listaAutos.removeIf(v -> v.getPatente().equalsIgnoreCase(patente));
//            if (!fueEliminado) {
//                throw new IllegalArgumentException("No se encontró ningún vehículo con la patente: " + patente);
//            }
//            System.out.println("Éxito: Auto con patente " + patente + " eliminado.");
//        } catch (IllegalArgumentException e) {
//            System.out.println("Aviso: " + e.getMessage());
//        } catch (NullPointerException e) {
//            System.out.println("Error grave: " + e.getMessage());
//        } catch (Exception e) {
//            System.out.println("Error inesperado: " + e.getMessage());
//        }
//    }

    //@Override
    //public String toString() {
    //    return "Auto{" +
    //            "marca='" + getMarca() + '\'' +
    //            ", modelo='" + getModelo() + '\'' +
    //            ", anio=" + getAnio() +
    //            ", condicionNuevo=" + esNuevo() +
    //            ", color='" + getColor() + '\'' +
    //            ", tipoCarroceria='" + tipoCarroceria + '\'' +
    //            '}';
    //}

    @Override
    public String toString() {
        String linea = " patente: " + getPatente() +
                   " | Año: " + getAnio() +
                   " | Marca: " + getMarca() +
                   " | Modelo: " + getModelo() +
                   " | Color: " + getColor() +
                   " | Condición: " + (esNuevo() ? "0 KM" : "Usado") +
                   " | Tipo de carrocería: " + tipoCarroceria + " ";

        String borde = "═".repeat(linea.length());

        return "╔" + borde + "╗\n" +
               "║" + linea + "║\n" +
               "╚" + borde + "╝";
    }
}
