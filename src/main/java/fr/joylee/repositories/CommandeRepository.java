package fr.joylee.repositories;

import fr.joylee.entities.CommandeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<CommandeEntity, Integer> {
    @Query("from CommandeEntity c where c.utilisateur.id = :utilisateur_id")
    List<CommandeEntity> getCommandesByUtilisateurId(@Param("utilisateur_id") int id);

    CommandeEntity findById(int id);
}
