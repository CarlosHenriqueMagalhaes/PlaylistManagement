package br.inatel.project.playlist.management.exception;

import java.io.Serializable;
//Essa classe manipula a saida das anotações da classe playlistdto ao dar erro no POST
//This class helps to handle the exception regarding
//@ExceptionHandler(MethodArgumentNotValidException) validation
//referring to the annotations(@) of the playListDTO class
public class FieldMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//these are the fields I want to display when giving the error...

	private String fieldName;
	private String message;
	
	//Constructors
	
	public FieldMessage() {
		super();
	}
	public FieldMessage(String fieldName, String message) {
		super();
		this.fieldName = fieldName;
		this.message = message;
	}
	
	//Getters and setters
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}