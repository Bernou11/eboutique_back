package fr.joylee.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ArticleDto {
    private int id;

    private String description;

    private String image;

    private String genre;

    private String createur;

    private Double prixHT;

    private Double prixTTC;

    private String collection;

    private String placement;

    private String type;

    private String sousType;

    private String age;
}
