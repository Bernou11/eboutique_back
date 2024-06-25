package fr.joylee.dto;

import fr.joylee.enums.AgeEnum;
import fr.joylee.enums.PlacementEnum;
import fr.joylee.enums.SexeEnum;
import fr.joylee.enums.TypeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VetementDto {
    private PlacementEnum placement;

    private TypeEnum type;

    private String sousType;

    private SexeEnum genre;

    private AgeEnum age;
}
