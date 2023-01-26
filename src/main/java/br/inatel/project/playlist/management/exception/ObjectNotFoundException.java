package br.inatel.project.playlist.management.exception;
/**
 * Exception handling class
 * @author Carlos Magalhães
 * @since 1.0
 */
public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String msg) {
		super(msg);
	}

	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}