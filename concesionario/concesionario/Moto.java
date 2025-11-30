package concesionario;

//import concesionario.exceptions.*;
import java.util.ArrayList;

public class Moto extends Vehiculo {
    private String tipoMoto;
//    private ArrayList<Moto> listaMotos = new ArrayList<>();

    public Moto(String marca, String modelo, int anio, boolean condicionNuevo, String color, String patente, String tipoMoto) {
        super(marca, modelo, anio, condicionNuevo, color, patente);
        this.tipoMoto = tipoMoto;
    }
    public String getTipoMoto() {
        return tipoMoto;
    }

//    public void agregarMoto(Moto nuevaMoto) {
//        try {
//            if (listaMotos == null) {
//                throw new NullPointerException("La lista no está inicializada.");
//            }
//            if (listaMotos.contains(nuevaMoto)) {
//                throw new VehiculoDuplicadoException("La patente " + nuevaMoto.getPatente() + " ya existe.");
//            }
//            listaMotos.add(nuevaMoto);
//            System.out.println("Moto agregada.");
//        } catch (VehiculoDuplicadoException e) {
//            System.out.println("Aviso: " + e.getMessage());
//        } catch (Exception e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }
//
//    public void actualizarMoto(String patenteBuscada, Moto nuevosDatos) {
//        try {
//            if (listaMotos == null) {
//                throw new NullPointerException("La lista no está inicializada.");
//            }
//            boolean actualizado = false;
//            for (int i = 0; i < listaMotos.size(); i++) {
//                Moto actual = listaMotos.get(i);
//                if (actual.getPatente().equalsIgnoreCase(patenteBuscada)) {
//                    if (!nuevosDatos.getPatente().equalsIgnoreCase(actual.getPatente()) 
//                        && listaMotos.contains(nuevosDatos)) {
//                        throw new IllegalArgumentException("No se puede actualizar: La nueva patente ya existe en otro vehículo.");
//                    }
//                    listaMotos.set(i, nuevosDatos);
//                    System.out.println("Éxito: Los datos de la moto " + patenteBuscada + " han sido actualizados.");
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
//    public void buscarMoto(String patenteBuscada) {
//        try {
//            if (listaMotos == null) {
//                throw new NullPointerException("La lista no está inicializada.");
//            }
//            boolean encontrada = false;
//            for (Moto m : listaMotos) {
//                if (m.getPatente().equalsIgnoreCase(patenteBuscada)) {
//                    System.out.println("---------------------------");
//                    System.out.println("¡VEHÍCULO ENCONTRADO!");
//                    System.out.println(m.toString());
//                    System.out.println("---------------------------");
//                    encontrada = true;
//                    break;
//                }
//            }
//            if (!encontrada) {
//                System.out.println("No se encontró ninguna moto con la patente: " + patenteBuscada);
//            }
//        } catch (NullPointerException e) {
//            System.out.println("Error grave: " + e.getMessage());
//        } catch (Exception e) {
//            System.out.println("Ocurrió un error inesperado al buscar: " + e.getMessage());
//        }
//    }
//
//    public void eliminarMoto(String patente) {
//        try {
//            if (listaMotos == null) {
//                throw new NullPointerException("La lista no está inicializada.");
//            }
//            boolean fueEliminado = listaMotos.removeIf(v -> v.getPatente().equalsIgnoreCase(patente));
//            if (!fueEliminado) {
//                throw new IllegalArgumentException("No se encontró ningún vehículo con la patente: " + patente);
//            }
//            System.out.println("Éxito: Moto con patente " + patente + " eliminada.");
//        } catch (IllegalArgumentException e) {
//            System.out.println("Aviso: " + e.getMessage());
//        } catch (NullPointerException e) {
//            System.out.println("Error grave: " + e.getMessage());
//        } catch (Exception e) {
//            System.out.println("Error inesperado: " + e.getMessage());
//        }
//    }

    @Override
    public String toString() {
        return "Moto{" +
                "marca='" + getMarca() + '\'' +
                ", modelo='" + getModelo() + '\'' +
                ", anio=" + getAnio() +
                ", 0 KM=" + esNuevo() +
                ", color='" + getColor() + '\'' +
                ", tipoMoto='" + tipoMoto + '\'' +
                '}';
    }

}
