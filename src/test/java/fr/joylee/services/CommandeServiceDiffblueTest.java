package fr.joylee.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import fr.joylee.dto.CommandeDto;
import fr.joylee.dto.CommandeUpdateDto;
import fr.joylee.dto.UtilisateurDto;
import fr.joylee.entities.ArticleEntity;
import fr.joylee.entities.CommandeEntity;
import fr.joylee.entities.UtilisateurEntity;
import fr.joylee.enums.*;
import fr.joylee.enums.RoleEnum;
import fr.joylee.enums.SexeEnum;
import fr.joylee.enums.StatusEnum;
import fr.joylee.mappers.CommandeMapper;
import fr.joylee.repositories.CommandeRepository;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CommandeService.class})
@ExtendWith(SpringExtension.class)
class CommandeServiceDiffblueTest {
    @MockBean
    private CommandeMapper commandeMapper;

    @MockBean
    private CommandeRepository commandeRepository;

    @Autowired
    private CommandeService commandeService;

    @MockBean
    private UserService userService;

    @Test
    void testGetCommandeById() {
        // Arrange
        UtilisateurEntity utilisateur = new UtilisateurEntity();
        utilisateur.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setId(1);
        utilisateur.setNom("Nom");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPrenom("Prenom");
        utilisateur.setPseudo("Pseudo");
        utilisateur.setRole(RoleEnum.administrateur);
        utilisateur.setSexe(SexeEnum.F);
        utilisateur.setStatus((byte) 'A');
        utilisateur.setVerificationCode("Verification Code");

        CommandeEntity commandeEntity = new CommandeEntity();
        commandeEntity.setAdresse("Adresse");
        commandeEntity.setId(1);
        commandeEntity.setPrixHt(10.0d);
        commandeEntity.setPrixTtc(10.0d);
        commandeEntity.setStatus(StatusEnum.traitement);
        commandeEntity.setUtilisateur(utilisateur);
        when(commandeRepository.findById(anyInt())).thenReturn(commandeEntity);

        // Act
        CommandeEntity actualCommandeById = commandeService.getCommandeById(1);

        // Assert
        verify(commandeRepository).findById(eq(1));
        assertSame(commandeEntity, actualCommandeById);
    }

    @Test
    void testSaveNewCommande() {
        // Given

        UtilisateurEntity utilisateur = new UtilisateurEntity();
        utilisateur.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setId(1);
        utilisateur.setNom("Nom");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPrenom("Prenom");
        utilisateur.setPseudo("Pseudo");
        utilisateur.setRole(RoleEnum.administrateur);
        utilisateur.setSexe(SexeEnum.F);
        utilisateur.setStatus((byte) '0');
        utilisateur.setVerificationCode("Verification Code");

        CommandeEntity commandeEntity = new CommandeEntity();
        commandeEntity.setAdresse("Adresse");
        commandeEntity.setId(1);
        commandeEntity.setPrixHt(10.0d);
        commandeEntity.setPrixTtc(10.0d);
        commandeEntity.setStatus(StatusEnum.traitement);
        commandeEntity.setUtilisateur(utilisateur);
        when(commandeRepository.save(Mockito.any())).thenReturn(commandeEntity);

        UtilisateurEntity utilisateurEntity = new UtilisateurEntity();
        utilisateurEntity.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        utilisateurEntity.setEmail("jane.doe@example.org");
        utilisateurEntity.setId(1);
        utilisateurEntity.setNom("Nom");
        utilisateurEntity.setPassword("iloveyou");
        utilisateurEntity.setPrenom("Prenom");
        utilisateurEntity.setPseudo("Pseudo");
        utilisateurEntity.setRole(RoleEnum.administrateur);
        utilisateurEntity.setSexe(SexeEnum.F);
        utilisateurEntity.setStatus((byte) '0');
        utilisateurEntity.setVerificationCode("Verification Code");
        Optional<UtilisateurEntity> ofResult = Optional.of(utilisateurEntity);
        when(userService.findById(anyInt())).thenReturn(ofResult);

        CommandeDto dto = new CommandeDto();
        dto.setAdresse("Adresse");
        dto.setId(1);
        dto.setIdClient(1);
        dto.setPrixHT(10.0d);
        dto.setPrixTTC(10.0d);

        // When
        commandeService.saveNewCommande(dto);

        // Then
        verify(userService).findById(eq(1));
        verify(commandeRepository).save(isA(CommandeEntity.class));
    }

    /**
     * Method under test: {@link CommandeService#deleteCommandeById(int)}
     */
    @Test
    void testDeleteCommandeById() {
        // Arrange
        doNothing().when(commandeRepository).deleteById(Mockito.<Integer>any());

        // Act
        commandeService.deleteCommandeById(1);

        // Assert that nothing has changed
        verify(commandeRepository).deleteById(eq(1));
    }

    //    @Test
    //    void testUpdateCommande() {
    //        // Given
    //        UtilisateurEntity utilisateur = new UtilisateurEntity();
    //        utilisateur.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
    //        utilisateur.setEmail("jane.doe@example.org");
    //        utilisateur.setId(1);
    //        utilisateur.setNom("Nom");
    //        utilisateur.setPassword("iloveyou");
    //        utilisateur.setPrenom("Prenom");
    //        utilisateur.setPseudo("Pseudo");
    //        utilisateur.setRole(RoleEnum.administrateur);
    //        utilisateur.setSexe(SexeEnum.F);
    //        utilisateur.setStatus((byte) '0');
    //        utilisateur.setVerificationCode("Verification Code");
    //
    //
    //        CommandeEntity commandeEntity = new CommandeEntity();
    //        commandeEntity.setAdresse("Adresse");
    //        commandeEntity.setId(1);
    //        commandeEntity.setPrixHt(10.0d);
    //        commandeEntity.setPrixTtc(10.0d);
    //        commandeEntity.setStatus(StatusEnum.traitement);
    //        commandeEntity.setUtilisateur(utilisateur);
    //
    //        CommandeUpdateDto dto = new CommandeUpdateDto();
    //        dto.setAdresse("Adresse1");
    //        dto.setId(1);
    //
    //        // When
    //        commandeService.updateCommande(dto);
    //
    //        when(commandeService.updateCommande(dto)).thenReturn(commandeEntity);
    //
    //        CommandeEntity modifiedCommand = commandeService.getCommandeById(1);
    //
    //        // Then
    //
    //        assertTrue(modifiedCommand.getAdresse().equals("Adresse1"));
    //    }

    //    @Test
    //    void testListCommandForUser() {
    //        // Given
    //        ArrayList<CommandeEntity> commandeEntityList = new ArrayList<>();
    //
    //        UtilisateurDto user1 = new UtilisateurDto();
    //        user1.setEmail("test@test.com");
    //        user1.setNom("test");
    //        user1.setPassword("test");
    //        user1.setPrenom("test");
    //        user1.setPseudo("test");
    //        user1.setSexe("M");
    //        user1.setRole(RoleEnum.client);
    //
    //        UtilisateurDto user2 = new UtilisateurDto();
    //        user2.setEmail("test@test.com");
    //        user2.setNom("test");
    //        user2.setPassword("test");
    //        user2.setPrenom("test");
    //        user2.setPseudo("test");
    //        user2.setSexe("M");
    //        user2.setRole(RoleEnum.client);
    //
    //        userService.saveUser(user1);
    //        userService.saveUser(user2);
    //
    //        List<UtilisateurEntity> utilisateurs = userService.getUsers();
    //
    //        assertTrue(utilisateurs.size() == 2);
    //
    //        CommandeEntity commandeEntity = new CommandeEntity();
    //        commandeEntity.setId(1);
    //        commandeEntity.setAdresse("Adresse");
    //        commandeEntity.setPrixTtc(100d);
    //        commandeEntity.setPrixHt(70d);
    //        commandeEntity.setStatus(StatusEnum.traitement);
    //        commandeEntity.setUtilisateur(utilisateurs.get(1));
    //
    //        CommandeEntity commandeEntity2 = new CommandeEntity();
    //        commandeEntity2.setId(1);
    //        commandeEntity2.setAdresse("Adresse");
    //        commandeEntity2.setPrixTtc(100d);
    //        commandeEntity2.setPrixHt(70d);
    //        commandeEntity2.setStatus(StatusEnum.traitement);
    //        commandeEntity2.setUtilisateur(utilisateurs.get(2));
    //
    //        when(commandeRepository.getCommandesByUtilisateurId(anyInt())).thenReturn(commandeEntityList);
    //
    //        // Act
    //        List<CommandeEntity> actualListCommandForUserResult = commandeService.listCommandForUser(1);
    //
    //        // Assert
    //        assertTrue(actualListCommandForUserResult.size() == 1);
    //        assertTrue(actualListCommandForUserResult.get(0).getId() == 1);
    //    }
}
