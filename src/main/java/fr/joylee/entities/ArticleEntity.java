//package fr.joylee.entities;
//
//import fr.joylee.enums.CollectionsEnum;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.EqualsAndHashCode;
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.Hibernate;
//
//@Entity
//@Table(name = "article", schema = "eboutique")
//@Getter
//@Setter
//@AllArgsConstructor
//@EqualsAndHashCode
//public class ArticleEntity {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Id
//    @Column(name = "article_id", nullable = false)
//    private int article_id;
//
//    @Column(name = "description", nullable = false, length = -1)
//    private String description;
//
//    @Lob
//    @Column(name = "image", nullable = false, length = -1)
//    private String image;
//
//    @Column(name = "genre", nullable = false)
//    private Byte genre;
//
//    @Column(name = "createur", nullable = false, length = -1)
//    private String createur;
//
//    @Column(name = "prixHT", nullable = false)
//    private Double prixHT;
//
//    @Column(name = "prixTTC", nullable = false)
//    private Double prixTTC;
//
//    @Column(name = "collection", nullable = false)
//    @Enumerated(EnumType.STRING)
//    private CollectionsEnum collection;
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "commande_id")
//    private CommandeEntity commandeId;
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "vetement_id")
//    private VetementEntity vetements;
//
//    public ArticleEntity() {
//
//    }
//}
