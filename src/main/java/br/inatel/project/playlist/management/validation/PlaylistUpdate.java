package br.inatel.project.playlist.management.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Annotation Class for exception handling
 *
 * @author Carlos Magalhães
 * @since 1.0
 */
@Constraint(validatedBy = PlaylistUpdateValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PlaylistUpdate {
    String message() default "validation error";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}