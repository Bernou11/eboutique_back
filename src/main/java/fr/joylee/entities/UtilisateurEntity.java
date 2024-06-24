package fr.joylee.entities;

import fr.joylee.enums.RoleEnum;
import fr.joylee.enums.SexeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "utilisateur", schema = "eboutique")
@Getter
@Setter
@NoArgsConstructor
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

    @Enumerated(EnumType.STRING)
    @Column(name = "sexe", nullable = false)
    private SexeEnum sexe;

    @Column(name = "verification_code", nullable = false)
    private String verificationCode;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        UtilisateurEntity that = (UtilisateurEntity) o;
        return Objects.equals(getUtilisateur_id(), that.getUtilisateur_id());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
