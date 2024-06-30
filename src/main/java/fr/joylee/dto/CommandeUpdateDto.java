package fr.joylee.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class CommandeUpdateDto {
    private int id;

    private String adresse;
}
