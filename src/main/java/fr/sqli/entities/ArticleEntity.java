package fr.sqli.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.Type;

import java.util.List;

@Entity
@Table(name = "article", schema = "eboutique")
@Getter
@Setter
public class ArticleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "article_id", nullable = false)
    private int id;
    @Type(type = "json")
    @Column(name = "type_vetement", nullable = true)
    private String typeVetement;
    @Basic
    @Column(name = "description", nullable = false, length = -1)
    private String description;
    @Basic
    @Column(name = "image", nullable = false, length = -1)
    private String image;
    @Basic
    @Column(name = "genre", nullable = true)
    private Byte genre;
    @Basic
    @Column(name = "createur", nullable = true, length = -1)
    private String createur;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tag_article_id")
    private TagArticleEntity tagArticleId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "commande_id")
    private CommandeEntity commandeId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ArticleEntity that = (ArticleEntity) o;
        return false;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
