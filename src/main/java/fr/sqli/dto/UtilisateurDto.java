package fr.sqli.dto;

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
}
