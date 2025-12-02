package concesionario.Excepciones;

public class VehiculoDuplicadoException extends RuntimeException {
    public VehiculoDuplicadoException(String mensaje) {
        super(mensaje);
    }
}
