package fr.joylee.entities;

import fr.joylee.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "utilisateur", schema = "eboutique")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
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
}
