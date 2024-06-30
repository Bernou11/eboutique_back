package fr.joylee.controlers;

import fr.joylee.dto.CommandeDto;
import fr.joylee.dto.CommandeUpdateDto;
import fr.joylee.entities.CommandeEntity;
import fr.joylee.services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eboutique/api/commandes")
public class CommandeControler {
    @Autowired
    CommandeService service;

    @GetMapping("/userId/{utilisateurId}")
    public ResponseEntity<List<CommandeEntity>> getByUtilisateurId(@PathVariable("utilisateurId") int utilisateurId) {
        List<CommandeEntity> commandes = service.listCommandForUser(utilisateurId);

        return new ResponseEntity<>(commandes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommandeEntity> getById(@PathVariable("id") int id) {
        CommandeEntity commande = service.getCommandeById(id);

        return new ResponseEntity<>(commande, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> saveNewCommande(@RequestBody CommandeDto dto) {
        service.saveNewCommande(dto);

        return new ResponseEntity<>("La commande a été créée avec succès", HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<String> upodateCommande(@RequestBody CommandeUpdateDto dto) {
        service.updateCommande(dto);

        return new ResponseEntity<>("La commande a été modifié avec succès", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCommande(@PathVariable("id") int id) {
        service.deleteCommandeById(id);

        return new ResponseEntity<>("La comande a été supprimée avec succès", HttpStatus.ACCEPTED);
    }
}
