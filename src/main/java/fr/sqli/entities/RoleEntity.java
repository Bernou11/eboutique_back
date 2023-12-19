package fr.sqli.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "role", schema = "eboutique")
@Getter
@Setter
public class RoleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "role_id", nullable = false)
    private int id;
    @Type(type = "json")
    @Column(name = "type_role", nullable = true)
    private String typeRole;

    @OneToMany(fetch = FetchType.EAGER)
    private List<UtilisateurEntity> utilisateurs;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return id == that.id && Objects.equals(typeRole, that.typeRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeRole);
    }
}
