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
	private String msg;

	/**
	 *Constructors
	 */
	public StandardError() {
	}
	public StandardError(Integer status, String msg) {
		this.status = status;
		this.msg = msg;
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
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
