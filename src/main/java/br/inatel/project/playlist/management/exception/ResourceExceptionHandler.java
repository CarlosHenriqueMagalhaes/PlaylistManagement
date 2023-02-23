package br.inatel.project.playlist.management.exception;

import javax.servlet.http.HttpServletRequest;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
/**
 * Principal Exception handling class
 * @author Carlos Magalhães
 * @since 1.0
 */
@ControllerAdvice
public class ResourceExceptionHandler {

	/**
	 * For object not found:
	 * @param e
	 * @param request
	 * @return message declared in the method
	 */
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	/**
	 * For exception when giving validation error
	 * Used when giving error in the post
	 * I made the auxiliary classes FieldMessage and ValidationError
	 * @param e
	 * @param request
	 * @return exception handling msg
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST.value(), "validation error");
		for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			error.addError(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	/**
	 * For object not found:
	 * @param e
	 * @param request
	 * @return message declared in the method
	 */
	@ExceptionHandler(NullObjectNotFoundException.class)
	public ResponseEntity<StandardError> nullObjectNotFound(NullObjectNotFoundException e, HttpServletRequest request) {
		StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	/**
	 * Communications link failure, Unable to acquire JDBC Connection
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(CommunicationsException.class)
	public ResponseEntity<StandardError> communicationException(CommunicationsException e, HttpServletRequest request){
		StandardError error = new StandardError(HttpStatus.SERVICE_UNAVAILABLE.value(), "Communications link failure, Unable to acquire JDBC Connection");
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
	}
}