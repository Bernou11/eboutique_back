package fr.joylee.services;

import fr.joylee.dto.UtilisateurDto;
import fr.joylee.entities.UtilisateurEntity;
import fr.joylee.enums.RoleEnum;
import fr.joylee.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
public class UserServiceTests {

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @Test
    @DisplayName("Création d'un utilisateur")
    void saveUser() {
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

    /**
     * Method under test: {@link UserService#updateUser(int, UtilisateurDto)}
     */
    @Test
    void testUpdateUser() {
        // Arrange
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");

        UtilisateurEntity utilisateurEntity = new UtilisateurEntity();
        utilisateurEntity.setCreationDate(LocalDate.now().atStartOfDay());
        utilisateurEntity.setEmail("test@test.com");
        utilisateurEntity.setNom("test");
        utilisateurEntity.setPassword("test");
        utilisateurEntity.setPrenom("test");
        utilisateurEntity.setRole(RoleEnum.client);
        utilisateurEntity.setStatus((byte) '1');

        UtilisateurEntity utilisateurEntity2 = new UtilisateurEntity();
        utilisateurEntity2.setCreationDate(LocalDate.now().atStartOfDay());
        utilisateurEntity2.setEmail("test-update@test.com");
        utilisateurEntity2.setNom("Update");
        utilisateurEntity2.setPassword("test-update");
        utilisateurEntity2.setPrenom("Test");
        utilisateurEntity2.setPseudo("Tupdate");
        utilisateurEntity2.setRole(RoleEnum.createur);
        when(userRepository.save(Mockito.any())).thenReturn(utilisateurEntity2);
        when(userRepository.getReferenceById(Mockito.<Integer>any())).thenReturn(utilisateurEntity);

        UtilisateurDto user = new UtilisateurDto();
        user.setEmail("test-update@test.com");
        user.setId(utilisateurEntity2.getUtilisateur_id());
        user.setNom("Update");
        user.setPassword("test-update");
        user.setPrenom("Test");
        user.setPseudo("Tupdate");
        user.setRole(RoleEnum.createur);

        // Act
        UtilisateurEntity actualUpdateUserResult = userService.updateUser(1, user);

        // Assert
        verify(userRepository).getReferenceById(eq(1));
        verify(userRepository).save(isA(UtilisateurEntity.class));
        verify(passwordEncoder).encode(isA(CharSequence.class));
        assertSame(utilisateurEntity2, actualUpdateUserResult);
    }
}
