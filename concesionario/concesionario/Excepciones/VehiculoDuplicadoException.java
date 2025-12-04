package concesionario.excepciones;

public class VehiculoDuplicadoException extends RuntimeException {
    public VehiculoDuplicadoException(String mensaje) { // el constructor recibe un mensaje por parametro
        super(mensaje);
    }
}
