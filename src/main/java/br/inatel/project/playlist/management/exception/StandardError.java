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
	private Long timeStamp;

	// constructors

	public StandardError() {
	}

	public StandardError(Integer status, String msg, Long timeStamp) {
		this.status = status;
		this.msg = msg;
		this.timeStamp = timeStamp;
	}

	// Getters and setters

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

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

}
