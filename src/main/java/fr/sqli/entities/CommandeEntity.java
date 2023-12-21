package fr.sqli.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.List;

@Entity
@Table(name = "commande", schema = "eboutique")
@Getter
@Setter
@AllArgsConstructor
public class CommandeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "commande_id", nullable = false)
    private int id;

    @Column(name = "prixHT", nullable = false, precision = 0)
    private Double prixHt;

    @Column(name = "prixTTC", nullable = false, precision = 0)
    private Double prixTtc;

    @Column(name = "status", nullable = false)
    private Byte status;

    @OneToMany(fetch = FetchType.EAGER)
    private List<ArticleEntity> articles;

    public CommandeEntity() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CommandeEntity that = (CommandeEntity) o;
        return false;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
