package fr.joylee.controlers;

import fr.joylee.dto.UtilisateurDto;
import fr.joylee.dto.VetementDto;
import fr.joylee.entities.VetementEntity;
import fr.joylee.services.VetementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class VetementControler {
    @Autowired
    VetementsService service;

    @GetMapping("/vetements/{id}")
    public Optional<VetementEntity> getById(@PathVariable("id") int id) {
        return service.getVetementById(id);
    }

    @GetMapping("/vetements")
    public List<VetementEntity> getAllVetements() {
        return service.getVetements();
    }

    @PostMapping(path = "/utilisateurs", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveVetement(@RequestBody VetementDto newVetement) {
        service.saveVetement(newVetement);

        return new ResponseEntity<>("Vètement créé avec succès!", HttpStatus.OK);
    }

    @DeleteMapping("/vetements/{id}")
    public ResponseEntity<String> deleteVetement(@PathVariable("id") int id) {
        service.deleteVetementById(id);

        return new ResponseEntity<>("Vètement supprimé avec succès!", HttpStatus.OK);
    }

    @PutMapping("/vetements/{id}")
    public ResponseEntity<String> updateVetement(@RequestBody VetementEntity modif, @PathVariable("id") int id) {
        service.updateVetementById(id, modif);

        return new ResponseEntity<>("Le vêtement a été modifié avec succès", HttpStatus.OK);
    }
}
