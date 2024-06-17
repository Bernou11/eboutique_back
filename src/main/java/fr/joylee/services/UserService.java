package fr.joylee.services;

import fr.joylee.dto.UtilisateurDto;
import fr.joylee.entities.UtilisateurEntity;
import fr.joylee.enums.RoleEnum;
import fr.joylee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UtilisateurEntity> getUsers() {
        return repo.findAll();
    }

    public UtilisateurEntity findById(int id) {
        return repo.getReferenceById(id);
    }

    public void deleteUserById(UtilisateurEntity user) {
        repo.deleteByUtilisateur_id(user.getUtilisateur_id());
    }

    public UtilisateurEntity updateUser(int id, UtilisateurDto user) {
        UtilisateurEntity oldUser = findById(id);

        oldUser.setNom(user.getNom());
        oldUser.setEmail(user.getEmail());
        if (user.getPseudo() != null) {
            oldUser.setPseudo(user.getPseudo());
        }
        oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
        oldUser.setPrenom(user.getPrenom());

        return repo.save(oldUser);
    }

    @Transactional
    public UtilisateurEntity saveUser(UtilisateurDto user) {
        UtilisateurEntity newUser = new UtilisateurEntity();

        newUser.setPrenom(user.getPrenom());
        newUser.setNom(user.getNom());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getPseudo() != null) {
            newUser.setPseudo(user.getPseudo());
        }
        newUser.setRole(RoleEnum.client);
        newUser.setStatus((byte) 1);
        newUser.setCreationDate(LocalDateTime.now());

        return repo.save(newUser);
    }
}
