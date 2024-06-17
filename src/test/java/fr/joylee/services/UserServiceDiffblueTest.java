package fr.joylee.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import fr.joylee.dto.UtilisateurDto;
import fr.joylee.entities.UtilisateurEntity;
import fr.joylee.enums.RoleEnum;
import fr.joylee.repositories.UserRepository;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
class UserServiceDiffblueTest {
    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * Method under test: {@link UserService#deleteUserById(UtilisateurEntity)}
     */
    @Test
    void testDeleteUserById() {
        // Arrange
        doNothing().when(userRepository).deleteByUtilisateur_id(anyInt());

        UtilisateurEntity user = new UtilisateurEntity();
        user.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmail("jane.doe@example.org");
        user.setNom("Nom");
        user.setPassword("iloveyou");
        user.setPrenom("Prenom");
        user.setPseudo("Pseudo");
        user.setRole(RoleEnum.administrateur);
        user.setStatus((byte) 'A');
        user.setUtilisateur_id(1);

        // Act
        userService.deleteUserById(user);

        // Assert that nothing has changed
        verify(userRepository).deleteByUtilisateur_id(eq(1));
        assertEquals("1970-01-01", user.getCreationDate().toLocalDate().toString());
        assertEquals("Nom", user.getNom());
        assertEquals("Prenom", user.getPrenom());
        assertEquals("Pseudo", user.getPseudo());
        assertEquals("iloveyou", user.getPassword());
        assertEquals("jane.doe@example.org", user.getEmail());
        assertEquals(1, user.getUtilisateur_id());
        assertEquals(RoleEnum.administrateur, user.getRole());
        assertEquals('A', user.getStatus().byteValue());
    }

    /**
     * Method under test: {@link UserService#updateUser(int, UtilisateurDto)}
     */
    @Test
    void testUpdateUser() {
        // Arrange
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");

        UtilisateurEntity utilisateurEntity = new UtilisateurEntity();
        utilisateurEntity.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        utilisateurEntity.setEmail("jane.doe@example.org");
        utilisateurEntity.setNom("Nom");
        utilisateurEntity.setPassword("iloveyou");
        utilisateurEntity.setPrenom("Prenom");
        utilisateurEntity.setPseudo("Pseudo");
        utilisateurEntity.setRole(RoleEnum.administrateur);
        utilisateurEntity.setStatus((byte) 'A');
        utilisateurEntity.setUtilisateur_id(1);

        UtilisateurEntity utilisateurEntity2 = new UtilisateurEntity();
        utilisateurEntity2.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        utilisateurEntity2.setEmail("jane.doe@example.org");
        utilisateurEntity2.setNom("Nom");
        utilisateurEntity2.setPassword("iloveyou");
        utilisateurEntity2.setPrenom("Prenom");
        utilisateurEntity2.setPseudo("Pseudo");
        utilisateurEntity2.setRole(RoleEnum.administrateur);
        utilisateurEntity2.setStatus((byte) 'A');
        utilisateurEntity2.setUtilisateur_id(1);
        when(userRepository.save(Mockito.<UtilisateurEntity>any())).thenReturn(utilisateurEntity2);
        when(userRepository.getReferenceById(Mockito.<Integer>any())).thenReturn(utilisateurEntity);

        UtilisateurDto user = new UtilisateurDto();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setNom("Nom");
        user.setPassword("iloveyou");
        user.setPrenom("Prenom");
        user.setPseudo("Pseudo");
        user.setRole(RoleEnum.administrateur);

        // Act
        UtilisateurEntity actualUpdateUserResult = userService.updateUser(1, user);

        // Assert
        verify(userRepository).getReferenceById(eq(1));
        verify(userRepository).save(isA(UtilisateurEntity.class));
        verify(passwordEncoder).encode(isA(CharSequence.class));
        assertSame(utilisateurEntity2, actualUpdateUserResult);
    }

    /**
     * Method under test: {@link UserService#saveUser(UtilisateurDto)}
     */
    @Test
    void testSaveUser() {
        when(passwordEncoder.encode(Mockito.any())).thenReturn("secret");

        UtilisateurEntity utilisateurEntity = new UtilisateurEntity();
        utilisateurEntity.setCreationDate(LocalDate.now().atStartOfDay());
        utilisateurEntity.setEmail("test@test.com");
        utilisateurEntity.setNom("test");
        utilisateurEntity.setPassword("test");
        utilisateurEntity.setPrenom("test");
        utilisateurEntity.setRole(RoleEnum.client);
        utilisateurEntity.setStatus((byte) '1');
        when(userRepository.save(Mockito.any())).thenReturn(utilisateurEntity);

        UtilisateurDto user = new UtilisateurDto();
        user.setEmail("test@test.com");
        user.setNom("test");
        user.setPassword("test");
        user.setPrenom("test");
        user.setRole(RoleEnum.client);

        // Act
        userService.saveUser(user);

        // Assert
        verify(userRepository).save(isA(UtilisateurEntity.class));
        verify(passwordEncoder).encode(isA(CharSequence.class));
    }
}
