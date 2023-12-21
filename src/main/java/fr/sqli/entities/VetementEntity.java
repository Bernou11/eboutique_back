package fr.sqli.entities;

import fr.sqli.enums.PlacementEnum;
import fr.sqli.enums.TypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "vetement", schema = "eboutique")
@AllArgsConstructor
public class VetementEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "vetement_id", nullable = false)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "placement", nullable = false)
    private PlacementEnum placement;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = -1)
    private TypeEnum type;

    @Column(name = "sous-type", nullable = false, length = -1)
    private String sous_type;

    @Column(name = "genre", nullable = true)
    private Byte genre;

    @OneToMany(fetch = FetchType.LAZY)
    private List<ArticleEntity> articles;

    public VetementEntity() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VetementEntity that)) return false;
        return getId() == that.getId() && getPlacement() == that.getPlacement() && getType() == that.getType() && Objects.equals(getSous_type(), that.getSous_type()) && Objects.equals(getGenre(), that.getGenre()) && Objects.equals(getArticles(), that.getArticles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPlacement(), getType(), getSous_type(), getGenre(), getArticles());
    }
}
