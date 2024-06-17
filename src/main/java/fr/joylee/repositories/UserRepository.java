package fr.joylee.repositories;

import fr.joylee.entities.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UtilisateurEntity, Integer> {
    UtilisateurEntity findByEmail(String email);

    UtilisateurEntity findByUtilisateur_id(int id);

    void deleteByUtilisateur_id(int id);
}
