package fr.joylee.entities;

import fr.joylee.enums.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "article", schema = "eboutique")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ArticleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "description", nullable = false, length = -1)
    private String description;

    @Lob
    @Column(name = "image", nullable = false, length = -1)
    private String image;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre", nullable = false)
    private SexeEnum genre;

    @Column(name = "createur", nullable = false, length = -1)
    private String createur;

    @Column(name = "prixHT", nullable = false)
    private Double prixHT;

    @Column(name = "prixTTC", nullable = false)
    private Double prixTTC;

    @Enumerated(EnumType.STRING)
    @Column(name = "collection", nullable = false)
    private CollectionsEnum collection;

    @Enumerated(EnumType.STRING)
    @Column(name = "placement", nullable = false)
    private PlacementEnum placement;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = -1)
    private TypeEnum type;

    @Column(name = "sous-type", nullable = false, length = -1)
    private String sous_type;

    @Enumerated(EnumType.STRING)
    @Column(name = "age", nullable = false)
    private AgeEnum age;
}
