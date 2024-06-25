package fr.joylee.repositories;

import fr.joylee.entities.UtilisateurEntity;
import jdk.jshell.execution.Util;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UtilisateurEntity, Integer> {
    Optional<UtilisateurEntity> findByPrenomAndNom(String prenom, String nom);

    Optional<UtilisateurEntity> findByEmail(String email);

    void updateByUtilisateur_id(int id);
}
