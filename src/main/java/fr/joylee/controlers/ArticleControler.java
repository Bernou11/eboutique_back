package fr.joylee.controlers;

import fr.joylee.dto.ArticleDto;
import fr.joylee.entities.ArticleEntity;
import fr.joylee.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/eboutique/api/articles")
public class ArticleControler {
    @Autowired
    ArticleService service;

    @GetMapping()
    public ResponseEntity<List<ArticleEntity>> findAllArticles() {
        List<ArticleEntity> articles = service.findAllArticles();

        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleEntity> findById(@PathVariable("id") int id) {
        ArticleEntity article = service.findById(id).get();

        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @GetMapping("/createurs/{createur}")
    public ResponseEntity<List<ArticleEntity>> findByCreateur(@PathVariable("createur") String createur) {
        List<ArticleEntity> articles = service.findByCreateur(createur);

        return new ResponseEntity<>(articles, HttpStatus.PARTIAL_CONTENT);
    }

    @PostMapping()
    public ResponseEntity<String> saveNewArticle(@RequestBody ArticleDto dto) {
        service.saveNewArticle(dto);

        return new ResponseEntity<>("L'article " + dto.getId() + " a été enregistré avec succès", HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<String> updateArticle(@RequestBody ArticleDto dto) {
        service.updateArticle(dto);

        return new ResponseEntity<>("L'article " + dto.getId() + " a été modifié avec succès", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") int id) {
        service.deleteById(id);

        return new ResponseEntity<>("L'article " + id + " a été supprimé avec succès", HttpStatus.ACCEPTED);
    }
}
