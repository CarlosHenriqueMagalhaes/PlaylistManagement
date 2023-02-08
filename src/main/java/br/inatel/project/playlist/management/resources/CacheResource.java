package br.inatel.project.playlist.management.resources;

import br.inatel.project.playlist.management.service.Adapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
public class CacheResource {
    @Autowired
    private Adapter adapter;

    @DeleteMapping
    public ResponseEntity<?> deleteRecipeCache() {
        adapter.deleteCache();
        return ResponseEntity.noContent().build();
    }
}
