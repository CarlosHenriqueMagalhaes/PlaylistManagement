package br.inatel.project.playlist.management.exception;

//Classe foi criada com a necessidade de uma exception no CategoriaService no método de DELETE
public class DataIntegrityException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataIntegrityException (String msg) {
		super (msg);
	}
	
	public DataIntegrityException (String msg, Throwable cause) {
		super (msg, cause);
	}
}
