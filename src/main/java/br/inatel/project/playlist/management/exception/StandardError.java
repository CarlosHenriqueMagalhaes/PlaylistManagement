package br.inatel.project.playlist.management.exception;

import java.io.Serializable;

/**
 * Exception handling class
 * @author Carlos Magalhães
 * @since 1.0
 */
public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer status;
	private String message;

	/**
	 *Constructors
	 */
	public StandardError() {
	}
	public StandardError(Integer status, String message) {
		this.status = status;
		this.message = message;
	}

	/**
	 *Getters and setters
	 */
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
