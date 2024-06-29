package fr.joylee.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

import fr.joylee.dto.ArticleDto;
import fr.joylee.entities.ArticleEntity;
import fr.joylee.enums.AgeEnum;
import fr.joylee.enums.CollectionsEnum;
import fr.joylee.enums.PlacementEnum;
import fr.joylee.enums.SexeEnum;
import fr.joylee.enums.TypeEnum;
import fr.joylee.mappers.ArticleMapper;
import fr.joylee.repositories.ArticleRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.text.html.Option;

@ContextConfiguration(classes = {ArticleService.class})
@ExtendWith(SpringExtension.class)
class ArticleServiceDiffblueTest {
    @MockBean
    private ArticleMapper articleMapper;

    @MockBean
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleService articleService;

    @Test
    void testFindAllArticles() {
        // Given
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setAge(AgeEnum.adulte);
        articleEntity.setCollection(CollectionsEnum.ete);
        articleEntity.setCreateur("Createur");
        articleEntity.setDescription("Description");
        articleEntity.setGenre(SexeEnum.F);
        articleEntity.setId(1);
        articleEntity.setImage("Image");
        articleEntity.setPlacement(PlacementEnum.haut);
        articleEntity.setPrixHT(10.0d);
        articleEntity.setPrixTTC(10.0d);
        articleEntity.setSous_type("Sous type");
        articleEntity.setType(TypeEnum.vetement);
        Optional<ArticleEntity> ofResult1 = Optional.of(articleEntity);

        ArticleEntity articleEntity2 = new ArticleEntity();
        articleEntity2.setAge(AgeEnum.adulte);
        articleEntity2.setCollection(CollectionsEnum.ete);
        articleEntity2.setCreateur("Createur");
        articleEntity2.setDescription("Description");
        articleEntity2.setGenre(SexeEnum.F);
        articleEntity2.setId(2);
        articleEntity2.setImage("Image");
        articleEntity2.setPlacement(PlacementEnum.haut);
        articleEntity2.setPrixHT(10.0d);
        articleEntity2.setPrixTTC(10.0d);
        articleEntity2.setSous_type("Sous type");
        articleEntity2.setType(TypeEnum.vetement);
        Optional<ArticleEntity> ofResult2 = Optional.of(articleEntity2);

        List<ArticleEntity> mockEntities = Arrays.asList(ofResult1.get(), ofResult2.get());

        // When
        when(articleService.findAllArticles()).thenReturn(mockEntities);

        List<ArticleEntity> entities = articleService.findAllArticles();

        // Then

        assertTrue(entities != null);
        assertTrue(entities.size() == 2);
        assertTrue(entities.get(0).getId() == 1);
        assertTrue(entities.get(1).getId() == 2);

        verify(articleRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setAge(AgeEnum.adulte);
        articleEntity.setCollection(CollectionsEnum.ete);
        articleEntity.setCreateur("Createur");
        articleEntity.setDescription("Description");
        articleEntity.setGenre(SexeEnum.F);
        articleEntity.setId(1);
        articleEntity.setImage("Image");
        articleEntity.setPlacement(PlacementEnum.haut);
        articleEntity.setPrixHT(10.0d);
        articleEntity.setPrixTTC(10.0d);
        articleEntity.setSous_type("Sous type");
        articleEntity.setType(TypeEnum.vetement);
        Optional<ArticleEntity> ofResult = Optional.of(articleEntity);
        when(articleRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Optional<ArticleEntity> actualFindByIdResult = articleService.findById(1);

        verify(articleRepository).findById(eq(1));
        assertSame(ofResult, actualFindByIdResult);
    }

    @Test
    void testFindByCreateur() {
        // Given

        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setAge(AgeEnum.adulte);
        articleEntity.setCollection(CollectionsEnum.ete);
        articleEntity.setCreateur("Createur");
        articleEntity.setDescription("Description");
        articleEntity.setGenre(SexeEnum.F);
        articleEntity.setId(1);
        articleEntity.setImage("Image");
        articleEntity.setPlacement(PlacementEnum.haut);
        articleEntity.setPrixHT(10.0d);
        articleEntity.setPrixTTC(10.0d);
        articleEntity.setSous_type("Sous type");
        articleEntity.setType(TypeEnum.vetement);

        Optional<ArticleEntity> ofResult = Optional.of(articleEntity);

        ArticleEntity articleEntity2 = new ArticleEntity();
        articleEntity2.setAge(AgeEnum.adulte);
        articleEntity2.setCollection(CollectionsEnum.ete);
        articleEntity2.setCreateur("False");
        articleEntity2.setDescription("Description");
        articleEntity2.setGenre(SexeEnum.F);
        articleEntity2.setId(2);
        articleEntity2.setImage("Image");
        articleEntity2.setPlacement(PlacementEnum.haut);
        articleEntity2.setPrixHT(10.0d);
        articleEntity2.setPrixTTC(10.0d);
        articleEntity2.setSous_type("Sous type");
        articleEntity2.setType(TypeEnum.vetement);

        Optional<ArticleEntity> ofResult2 = Optional.of(articleEntity2);

        List<ArticleEntity> mockEntities = Arrays.asList(ofResult.get(), ofResult2.get());

        // When

        when(articleService.findByCreateur(anyString())).thenReturn(mockEntities);

        // Then

        List<ArticleEntity> foundArticles = articleService.findByCreateur("Createur");

        assertTrue(foundArticles.size() == 1);
        assertTrue(foundArticles.stream().allMatch(article -> "Createur".equals(article.getCreateur())));
    }

    @Test
    void testSaveNewArticle() {
        ArticleDto article = new ArticleDto();
        article.setAge("adulte");
        article.setCollection("ete");
        article.setCreateur("Createur");
        article.setDescription("Description");
        article.setGenre("H");
        article.setImage("Image");
        article.setPlacement("haut");
        article.setPrixHT(10.0d);
        article.setPrixTTC(10.0d);
        article.setSousType("Sous-Type");
        article.setType("vetement");

        articleService.saveNewArticle(article);

        verify(articleRepository).save(isA(ArticleEntity.class));
    }

//    @Test
//    void testUpdateArticle() {
//        ArticleEntity dto1 = new ArticleEntity();
//        dto1.setAge(AgeEnum.enfant);
//        dto1.setCollection(CollectionsEnum.ete);
//        dto1.setCreateur("Createur");
//        dto1.setDescription("Description");
//        dto1.setGenre(SexeEnum.F);
//        dto1.setId(1);
//        dto1.setImage("Image");
//        dto1.setPlacement(PlacementEnum.haut);
//        dto1.setPrixHT(10.0d);
//        dto1.setPrixTTC(10.0d);
//        dto1.setSous_type("Sous-type");
//        dto1.setType(TypeEnum.vetement);
//
//        articleService.saveNewArticle(articleEntity);
//
//        ArticleDto dto = new ArticleDto();
//        dto.setId(1);
//        dto.setAge("adulte");
//
//        articleService.updateArticle(dto);
//
//        verify(articleMapper).updateArticleFromDto(isA(ArticleDto.class), isA(ArticleEntity.class));
//        verify(articleRepository).findById(eq(1));
//        verify(articleRepository).save(isA(ArticleEntity.class));
//        assertEquals(AgeEnum.valueOf("adulte"), articleEntity.getAge());
//        assertEquals(CollectionsEnum.valueOf("ete"), articleEntity.getCollection());
//        assertEquals("Createur", articleEntity.getCreateur());
//        assertEquals(SexeEnum.valueOf("F"), articleEntity.getGenre());
//        assertEquals("Image", articleEntity.getImage());
//        assertEquals(PlacementEnum.valueOf("haut"), articleEntity.getPlacement());
//        assertEquals("Sous Type", articleEntity.getSous_type());
//        assertEquals("Description", articleEntity.getDescription());
//        assertEquals(TypeEnum.valueOf("vetement"), articleEntity.getType());
//        assertEquals(1, articleEntity.getId());
//        assertEquals(10.0d, dto.getPrixHT().doubleValue());
//        assertEquals(10.0d, dto.getPrixTTC().doubleValue());
//    }

    /**
     * Method under test: {@link ArticleService#deleteById(int)}
     */
    @Test
    void testDeleteById() {
        // Given

        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setAge(AgeEnum.adulte);
        articleEntity.setCollection(CollectionsEnum.ete);
        articleEntity.setCreateur("Createur");
        articleEntity.setDescription("Description");
        articleEntity.setGenre(SexeEnum.F);
        articleEntity.setId(1);
        articleEntity.setImage("Image");
        articleEntity.setPlacement(PlacementEnum.haut);
        articleEntity.setPrixHT(10.0d);
        articleEntity.setPrixTTC(10.0d);
        articleEntity.setSous_type("Sous type");
        articleEntity.setType(TypeEnum.vetement);
        Optional<ArticleEntity> ofResult = Optional.of(articleEntity);
        when(articleRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        articleService.deleteById(1);

        ArticleEntity deletedArticle = articleService.findById(1).get();

        System.out.println(deletedArticle);

        assertTrue(deletedArticle == null);
    }
}
