package es.uniovi.ips.hospital.exception;

public class InputException extends Exception {

	private static final long serialVersionUID = -4464032654734596031L;

	public InputException() {
    }

    public InputException(String message) {
        super(message);
    }

    public InputException(Throwable cause) {
        super(cause);
    }

    public InputException(String message, Throwable cause) {
        super(message, cause);
    }
}
