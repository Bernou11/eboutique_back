package fr.sqli.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

@Entity
@Table(name = "article", schema = "eboutique")
@Getter
@Setter
@AllArgsConstructor
public class ArticleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "article_id", nullable = false)
    private int id;

    @Column(name = "description", nullable = false, length = -1)
    private String description;

    @Lob
    @Column(name = "image", nullable = false, length = -1)
    private String image;

    @Column(name = "genre", nullable = false)
    private Byte genre;

    @Column(name = "createur", nullable = false, length = -1)
    private String createur;

    @Column(name = "prixHT", nullable = false)
    private Double prixHT;

    @Column(name = "prixTTC", nullable = false)
    private Double prixTTC;

    @Column(name = "collection", nullable = false)
    @Enumerated(EnumType.STRING)
    private String collection;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "commande_id")
    private CommandeEntity commandeId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vetement_id")
    private VetementEntity vetements;

    public ArticleEntity() {

    }

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
