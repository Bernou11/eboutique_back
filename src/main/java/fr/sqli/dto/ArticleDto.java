package fr.sqli.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ArticleDto {
    private int article_id;

    private String description;

    private String image;

    private Byte genre;

    private Double prixHT;

    private Double prixTTC;

    private String collection;
}
