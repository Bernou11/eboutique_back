package fr.joylee.repositories;

import fr.joylee.entities.ArticleEntity;
import org.springframework.core.style.ToStringCreator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {
    List<ArticleEntity> findByCreateur(String createur);
}
