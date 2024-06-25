package fr.joylee.repositories;

import fr.joylee.entities.VetementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VetementRepository extends JpaRepository<VetementEntity, Integer> {
    Optional<VetementEntity> findByVetement_id(int id);
    void deleteByVetement_id(int id);
}
