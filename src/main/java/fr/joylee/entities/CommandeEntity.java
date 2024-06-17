package fr.joylee.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "commande", schema = "eboutique")
@Getter
@Setter
@AllArgsConstructor
public class CommandeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "commande_id", nullable = false)
    private int commande_id;

    @Column(name = "prixHT", nullable = false, precision = 0)
    private Double prixHt;

    @Column(name = "prixTTC", nullable = false, precision = 0)
    private Double prixTtc;

    @Column(name = "status", nullable = false)
    private Byte status;

    public CommandeEntity() {

    }
}
