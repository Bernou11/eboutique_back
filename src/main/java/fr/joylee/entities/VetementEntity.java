package fr.joylee.entities;

import fr.joylee.enums.PlacementEnum;
import fr.joylee.enums.TypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "vetement", schema = "eboutique")
@AllArgsConstructor
@EqualsAndHashCode
public class VetementEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "vetement_id", nullable = false)
    private int vetement_id;

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

    public VetementEntity() {

    }
}
