package fr.sqli.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tag_article", schema = "eboutique")
@Getter
@Setter
public class TagArticleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "tag_article_id", nullable = false)
    private int id;
    @Type(type = "json")
    @Column(name = "collection", nullable = false)
    private String collection;

    @OneToMany(fetch = FetchType.EAGER)
    private List<ArticleEntity> articles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagArticleEntity that = (TagArticleEntity) o;
        return id == that.id && Objects.equals(collection, that.collection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, collection);
    }
}
