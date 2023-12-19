package fr.sqli.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "utilisateur", schema = "eboutique")
@Getter
@Setter
public class UtilisateurEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "utilisateur_id", nullable = false)
    private int id;
    @Basic
    @Column(name = "email", nullable = false, length = 30)
    private String email;
    @Basic
    @Column(name = "password", nullable = false, length = 18)
    private String password;
    @Basic
    @Column(name = "prenom", nullable = false, length = 45)
    private String prenom;
    @Basic
    @Column(name = "nom", nullable = false, length = 45)
    private String nom;
    @Basic
    @Column(name = "pseudo", nullable = false, length = -1)
    private String pseudo;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;
    @Basic
    @Column(name = "status", nullable = false)
    private Byte status;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UtilisateurEntity that)) return false;
        return getId() == that.getId() && getRoleId() == that.getRoleId() && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getPassword(), that.getPassword()) && Objects.equals(getPrenom(), that.getPrenom()) && Objects.equals(getNom(), that.getNom()) && Objects.equals(getPseudo(), that.getPseudo()) && Objects.equals(getCreationDate(), that.getCreationDate()) && Objects.equals(getStatus(), that.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getPassword(), getPrenom(), getNom(), getPseudo(), getCreationDate(), getStatus(), getRoleId());
    }
}
