//package fr.joylee.entities;
//
//import fr.joylee.enums.AgeEnum;
//import fr.joylee.enums.PlacementEnum;
//import fr.joylee.enums.SexeEnum;
//import fr.joylee.enums.TypeEnum;
//import jakarta.persistence.*;
//import lombok.*;
//import org.hibernate.proxy.HibernateProxy;
//
//import java.util.Objects;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "vetement", schema = "eboutique")
//@NoArgsConstructor
//@EqualsAndHashCode
//public class VetementEntity {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Id
//    @Column(name = "vetement_id", nullable = false)
//    private int vetement_id;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "placement", nullable = false)
//    private PlacementEnum placement;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "type", nullable = false, length = -1)
//    private TypeEnum type;
//
//    @Column(name = "sous-type", nullable = false, length = -1)
//    private String sous_type;
//
//    @Column(name = "genre", nullable = true)
//    private SexeEnum genre;
//
//    @Column(name = "age", nullable = false)
//    private AgeEnum age;
//}
