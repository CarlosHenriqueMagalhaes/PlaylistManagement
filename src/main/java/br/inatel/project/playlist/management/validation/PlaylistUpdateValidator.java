package br.inatel.project.playlist.management.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.inatel.project.playlist.management.domain.Playlist;
import br.inatel.project.playlist.management.dto.PlaylistDTO;
import br.inatel.project.playlist.management.exception.FieldMessage;
import br.inatel.project.playlist.management.repository.PlaylistRepository;

/**
 * Annotation Validator Class:
 *
 * @author Carlos Magalhães
 * @since 1.0
 */
public class PlaylistUpdateValidator implements ConstraintValidator<PlaylistUpdate, PlaylistDTO> {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private PlaylistRepository repo;

    @Override
    public void initialize(PlaylistUpdate ann) {
    }

    /**
     * Validator
     *
     * @param objDto  object to validate
     * @param context context in which the constraint is evaluated
     * @return message with exception handling
     */
    @Override
    public boolean isValid(PlaylistDTO objDto, ConstraintValidatorContext context) {
        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) request
                .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));


        List<FieldMessage> list = new ArrayList<>();

        Optional<Playlist> playlistOptional = repo.findById(objDto.getPlaylistId());
        if (playlistOptional != null && playlistOptional.isPresent() && !playlistOptional.get().getId().equals(uriId)) {
            list.add(new FieldMessage("id", "already existing id"));
        }

        // class method
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}