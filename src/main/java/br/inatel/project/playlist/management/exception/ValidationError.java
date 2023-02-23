package br.inatel.project.playlist.management.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * Exception class
 * This is an auxiliary class for handling exceptions regarding validation*
 * (@ExceptionHandler(MethodArgumentNotValidException))
 * This is a class that inherits data from StandardError
 * @author Carlos Magalhães
 * @since 1.0
 */
public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	private List<FieldMessage> errors = new ArrayList<>();

	/**
	 *Constructors
	 */
	public ValidationError(Integer status, String msg) {
		super(status, msg);
	}

	/**
	 *Getters and Setters
	 */
	public List<FieldMessage> getErrors() {
		return errors;
	}
	/** adulterated setter:
	 * was changed because we don't want to add an entire list at once,
	 * but one error at a time
	 */
	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}
}
