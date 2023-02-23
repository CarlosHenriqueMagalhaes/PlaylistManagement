package br.inatel.project.playlist.management.exception;

import java.io.Serializable;

/**
 * Exception class
 * This class handles the output of playlistDto class annotations when giving POST error
 * This class helps to handle the exception regarding
 * (@ExceptionHandler(MethodArgumentNotValidException)) validation
 * referring to the annotations(@) of the playListDTO class
 *
 * @author Carlos Magalhães
 * @since 1.0
 */
public class FieldMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    private String fieldName;
    private String message;

    /**
     * Constructors
     */
    public FieldMessage() {
        super();
    }

    public FieldMessage(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    // Getters and setters
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