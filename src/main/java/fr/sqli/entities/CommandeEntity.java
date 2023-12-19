package fr.sqli.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;

import java.util.List;

@Entity
@Table(name = "commande", schema = "eboutique")
@Getter
@Setter
public class CommandeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "commande_id", nullable = false)
    private int id;

    @Basic
    @Column(name = "id_client", nullable = false)
    private int idClient;

    @Basic
    @Column(name = "prixHT", nullable = true, precision = 0)
    private Double prixHt;

    @Basic
    @Column(name = "prixTTC", nullable = true, precision = 0)
    private Double prixTtc;

    @Basic
    @Column(name = "status", nullable = true)
    private Byte status;

    @Type(type = "json")
    @Column(name = "data", nullable = false)
    private String data;

    @OneToMany(fetch = FetchType.EAGER)
    private List<ArticleEntity> articles;

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
