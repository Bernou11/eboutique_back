package fr.joylee.repositories;

import fr.joylee.entities.VetementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VetementRepository extends JpaRepository<VetementEntity, Integer> {
}
