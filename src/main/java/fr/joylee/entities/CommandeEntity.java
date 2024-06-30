package fr.joylee.entities;

import fr.joylee.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "commande", schema = "eboutique")
@Getter
@Setter
@NoArgsConstructor
public class CommandeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "prixHT", nullable = false, precision = 0)
    private Double prixHt;

    @Column(name = "prixTTC", nullable = false, precision = 0)
    private Double prixTtc;

    @Column(name = "adresse_livraison", nullable = false)
    private String adresse;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "utilisateur_id")
    private UtilisateurEntity utilisateur;
}
