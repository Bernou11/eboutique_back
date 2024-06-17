package fr.joylee.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class CommandeDto {
    private int id;

    private int idClient;

    private Double prixHt;

    private Double prixTtc;
}
