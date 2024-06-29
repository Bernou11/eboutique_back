package fr.joylee.services;

import fr.joylee.dto.ArticleDto;
import fr.joylee.entities.ArticleEntity;
import fr.joylee.enums.*;
import fr.joylee.mappers.ArticleMapper;
import fr.joylee.repositories.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    private static final Logger log = LoggerFactory.getLogger(ArticleService.class);
    private final ArticleRepository repo;
    private final ArticleMapper articleMapper;

    @Autowired
    public ArticleService(ArticleRepository repo, ArticleMapper articleMapper) {
        this.repo = repo;
        this.articleMapper = articleMapper;
    }

    public List<ArticleEntity> findAllArticles() {
        return repo.findAll();
    }

    public Optional<ArticleEntity> findById(int id) {
        return repo.findById(id);
    }

    public List<ArticleEntity> findByCreateur(String createur) {
        return repo.findByCreateur(createur);
    }

    public void saveNewArticle(ArticleDto article) {
        ArticleEntity articleEntity = new ArticleEntity();

        articleEntity.setSous_type(article.getSousType());
        articleEntity.setCreateur(article.getCreateur());
        switch(article.getCollection().toLowerCase()) {
            case "ete" -> articleEntity.setCollection(CollectionsEnum.ete);
            case "hiver" -> articleEntity.setCollection(CollectionsEnum.hiver);
            case "automne" -> articleEntity.setCollection(CollectionsEnum.automne);
            case "printemps" -> articleEntity.setCollection(CollectionsEnum.printemps);
        }
        switch(article.getAge().toLowerCase()) {
            case "adulte" -> articleEntity.setAge(AgeEnum.adulte);
            case "enfant" -> articleEntity.setAge(AgeEnum.enfant);
            case "ado" -> articleEntity.setAge(AgeEnum.ado);
        }
        switch(article.getType().toLowerCase()) {
            case "vetement" -> articleEntity.setType(TypeEnum.vetement);
            case "sous_vetement" -> articleEntity.setType(TypeEnum.sous_vetement);
            case "chassure" -> articleEntity.setType(TypeEnum.chaussures);
        }
        switch(article.getPlacement().toLowerCase()) {
            case "haut" -> articleEntity.setPlacement(PlacementEnum.haut);
            case "bas" -> articleEntity.setPlacement(PlacementEnum.bas);
            case "ensemble" -> articleEntity.setPlacement(PlacementEnum.ensemble);
        }
        switch(article.getGenre().toLowerCase()) {
            case "m" -> articleEntity.setGenre(SexeEnum.M);
            case "f" -> articleEntity.setGenre(SexeEnum.F);
            case "autre" -> articleEntity.setGenre(SexeEnum.autre);
        }
        articleEntity.setImage(article.getImage());
        articleEntity.setDescription(article.getDescription());
        articleEntity.setPrixTTC(article.getPrixTTC());
        articleEntity.setPrixHT(article.getPrixHT());

        repo.save(articleEntity);

        log.info("L'article a été enregistré avec succès. ID : {}", articleEntity.getId());
    }

    public void updateArticle(ArticleDto dto) {
        ArticleEntity entity = repo.findById(dto.getId()).get();

        articleMapper.updateArticleFromDto(dto, entity);

        if(dto.getGenre() != null) {
            switch(dto.getGenre().toLowerCase()) {
                case "m" -> entity.setGenre(SexeEnum.M);
                case "f" -> entity.setGenre(SexeEnum.F);
                case "autre" -> entity.setGenre(SexeEnum.autre);
            }
        }
        if (dto.getType() != null) {
            switch(dto.getType().toLowerCase()) {
                case "vetement" -> entity.setType(TypeEnum.vetement);
                case "sous_vetement" -> entity.setType(TypeEnum.sous_vetement);
                case "chaussures" -> entity.setType(TypeEnum.chaussures);
            }
        }
        if(dto.getAge() != null) {
            switch(dto.getAge().toLowerCase()) {
                case "adulte" -> entity.setAge(AgeEnum.adulte);
                case "enfant" -> entity.setAge(AgeEnum.enfant);
                case "ado"-> entity.setAge(AgeEnum.ado);
            }
        }
        if(dto.getGenre() != null) {
            switch(dto.getGenre().toLowerCase()) {
                case "m" -> entity.setGenre(SexeEnum.M);
                case "f" -> entity.setGenre(SexeEnum.F);
                case "autre" -> entity.setGenre(SexeEnum.autre);
            }
        }
        if(dto.getCollection() != null) {
            switch(dto.getCollection().toLowerCase()) {
                case "ete", "été" -> entity.setCollection(CollectionsEnum.ete);
                case "hiver" -> entity.setCollection(CollectionsEnum.hiver);
                case "automne" -> entity.setCollection(CollectionsEnum.automne);
                case "printemps" -> entity.setCollection(CollectionsEnum.printemps);
            }
        }

        repo.save(entity);

        log.info("L'article {} a été modifié avec succès", entity.getId());
    }

    public void deleteById(int id) {
        repo.deleteById(id);

        log.info("L'article {} a été supprimé avec succès", id);
    }
}
