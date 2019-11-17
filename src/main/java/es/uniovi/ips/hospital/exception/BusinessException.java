package es.uniovi.ips.hospital.exception;

public class BusinessException extends Exception {

	private static final long serialVersionUID = -4464032654734596031L;

	public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
