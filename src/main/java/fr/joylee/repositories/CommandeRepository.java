package fr.joylee.repositories;

import fr.joylee.entities.CommandeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeRepository extends JpaRepository<CommandeEntity, Integer> {
}
