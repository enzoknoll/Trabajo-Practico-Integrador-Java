package concesionario;

//import concesionario.exceptions.*;
//import java.util.ArrayList;

public class Camioneta extends Vehiculo {
    private String tipoCarroceria;
//    private ArrayList<Camioneta> listaCamionetas = new ArrayList<>();

    public Camioneta(String marca, String modelo, int anio, boolean condicionNuevo, String color, String patente, String tipoCarroceria) {
        super(marca, modelo, anio, condicionNuevo, color, patente);
        this.tipoCarroceria = tipoCarroceria;
    }
    public String getTipoCarroceria() {
        return tipoCarroceria;
    }

//    public void agregarCamioneta(Camioneta nuevaCamioneta) {
//        try {
//            if (listaCamionetas == null) {
//                throw new NullPointerException("La lista no está inicializada.");
//            }
//            if (listaCamionetas.contains(nuevaCamioneta)) {
//                throw new VehiculoDuplicadoException("La patente " + nuevaCamioneta.getPatente() + " ya existe.");
//            }
//            listaCamionetas.add(nuevaCamioneta);
//            System.out.println("Camioneta agregada.");
//        } catch (VehiculoDuplicadoException e) {
//            System.out.println("Aviso: " + e.getMessage());
//        } catch (Exception e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }
//
//    public void actualizarCamioneta(String patenteBuscada, Camioneta nuevosDatos) {
//        try {
//            if (listaCamionetas == null) {
//                throw new NullPointerException("La lista no está inicializada.");
//            }
//            boolean actualizado = false;
//            for (int i = 0; i < listaCamionetas.size(); i++) {
//                Camioneta actual = listaCamionetas.get(i);
//                if (actual.getPatente().equalsIgnoreCase(patenteBuscada)) {
//                    if (!nuevosDatos.getPatente().equalsIgnoreCase(actual.getPatente()) 
//                        && listaCamionetas.contains(nuevosDatos)) {
//                        throw new IllegalArgumentException("No se puede actualizar: La nueva patente ya existe en otro vehículo.");
//                    }
//                    listaCamionetas.set(i, nuevosDatos);
//                    System.out.println("Éxito: Los datos de la camioneta " + patenteBuscada + " han sido actualizados.");
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
//    public void buscarCamioneta(String patenteBuscada) {
//        try {
//            if (listaCamionetas == null) {
//                throw new NullPointerException("La lista no está inicializada.");
//            }
//            boolean encontrada = false;
//            for (Camioneta c : listaCamionetas) {
//                if (c.getPatente().equalsIgnoreCase(patenteBuscada)) {
//                    System.out.println("---------------------------");
//                    System.out.println("¡VEHÍCULO ENCONTRADO!");
//                    System.out.println(c.toString());
//                    System.out.println("---------------------------");
//                    encontrada = true;
//                    break;
//                }
//            }
//            if (!encontrada) {
//                System.out.println("No se encontró ninguna camioneta con la patente: " + patenteBuscada);
//            }
//        } catch (NullPointerException e) {
//            System.out.println("Error grave: " + e.getMessage());
//        } catch (Exception e) {
//            System.out.println("Ocurrió un error inesperado al buscar: " + e.getMessage());
//        }
//    }
//
//    public void eliminarCamioneta(String patente) {
//        try {
//            if (listaCamionetas == null) {
//                throw new NullPointerException("La lista no está inicializada.");
//            }
//            boolean fueEliminado = listaCamionetas.removeIf(v -> v.getPatente().equalsIgnoreCase(patente));
//            if (!fueEliminado) {
//                throw new IllegalArgumentException("No se encontró ningún vehículo con la patente: " + patente);
//            }
//            System.out.println("Éxito: Camioneta con patente " + patente + " eliminada.");
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
    //    return "Camioneta{" +
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
