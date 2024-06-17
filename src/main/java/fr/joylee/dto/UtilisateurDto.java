package fr.joylee.dto;

import fr.joylee.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode
public class UtilisateurDto {
    private int id;

    private String email;

    private String password;

    private String prenom;

    private String nom;

    private String pseudo;

    private RoleEnum role;
}
