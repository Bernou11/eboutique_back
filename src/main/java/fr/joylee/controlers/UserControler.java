package fr.joylee.controlers;

import fr.joylee.dto.UtilisateurDto;
import fr.joylee.entities.UtilisateurEntity;
import fr.joylee.services.UserService;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserControler {
    private final UserService userService;

    public UserControler(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/utilisateurs")
    public List<UtilisateurEntity> getUsersList() {
        return userService.getUsers();
    }

    @GetMapping("/utilisateurs/{id}")
    public UtilisateurEntity getUserById(@PathVariable("id") int id) {
        return userService.findById(id).orElseThrow(() ->
                new RuntimeException("Utilisateur introuvable avec cet id : " + id));
    }

    @GetMapping("/utilisateur/{prenom}&{nom}")
    public UtilisateurEntity getUserByPrenomAndNom(@PathVariable("prenom") String prenom, @PathVariable("nom") String nom) {
        return userService.findByPrenomAndNom(prenom, nom).orElseThrow(() ->
                new RuntimeException("Utilisateur introuvable avec ces informations : " + prenom + " " + nom));
    }

    @PostMapping(path = "/utilisateurs", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createUser(@RequestBody UtilisateurDto newUser) throws MessagingException, UnsupportedEncodingException {
        userService.saveUser(newUser);

        return new ResponseEntity<>("Utilisateur créé avec succès!", HttpStatus.OK);
    }

    @DeleteMapping("/utilisateurs/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int userId){
        userService.deleteUserById(userId);

        return new ResponseEntity<>("Utilisateur supprimé avec succès!.", HttpStatus.OK);
    }

    @PutMapping("/utilisateurs/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") int userId){
        userService.updateUser(userId);

        return new ResponseEntity<>("Utilisateur modifié avec succès!", HttpStatus.OK);
    }

    @GetMapping("/utilisateurs/{email}")
    public Optional<UtilisateurEntity> getByEmail(@PathVariable("email") String email) {
        return userService.getByEmail(email);
    }
}
