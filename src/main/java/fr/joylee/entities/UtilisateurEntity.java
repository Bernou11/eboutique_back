package fr.joylee.entities;

import fr.joylee.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "utilisateur", schema = "eboutique")
@Getter
@Setter
@AllArgsConstructor
public class UtilisateurEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "utilisateur_id", nullable = false)
    private int utilisateur_id;

    @Column(name = "email", nullable = false, length = 30)
    private String email;

    @Column(name = "password", nullable = false, length = 18)
    private String password;

    @Column(name = "prenom", nullable = false, length = 45)
    private String prenom;

    @Column(name = "nom", nullable = false, length = 45)
    private String nom;

    @Column(name = "pseudo", nullable = false, length = -1)
    private String pseudo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "status", nullable = false)
    private Byte status;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private RoleEnum role;

    public UtilisateurEntity() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UtilisateurEntity that)) return false;
        return getUtilisateur_id() == that.getUtilisateur_id() && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getPassword(), that.getPassword()) && Objects.equals(getPrenom(), that.getPrenom()) && Objects.equals(getNom(), that.getNom()) && Objects.equals(getPseudo(), that.getPseudo()) && Objects.equals(getCreationDate(), that.getCreationDate()) && Objects.equals(getStatus(), that.getStatus()) && Objects.equals(getRole(), that.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUtilisateur_id(), getEmail(), getPassword(), getPrenom(), getNom(), getPseudo(), getCreationDate(), getStatus(), getRole());
    }
}
