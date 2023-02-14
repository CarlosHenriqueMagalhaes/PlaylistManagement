package br.inatel.project.playlist.management.exception;
/**
 * Exception handling class
 * @author Carlos Magalhães
 * @since 1.0
 */
public class NullObjectNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public NullObjectNotFoundException(String msg) {
		super(msg);
	}
	public NullObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}