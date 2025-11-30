package concesionario.exceptions;

public class VehiculoDuplicadoException extends RuntimeException {
    public VehiculoDuplicadoException(String mensaje) {
        super(mensaje);
    }
}
