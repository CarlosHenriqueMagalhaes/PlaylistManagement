package br.inatel.project.playlist.management.exception;

public class NullObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NullObjectNotFoundException(String msg) {
		super(msg);
	}

	public NullObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}