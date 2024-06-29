package fr.joylee.services;

import fr.joylee.dto.UtilisateurDto;
import fr.joylee.entities.UtilisateurEntity;
import fr.joylee.enums.RoleEnum;
import fr.joylee.enums.SexeEnum;
import fr.joylee.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        UtilisateurDto user = new UtilisateurDto();
        user.setEmail("test@test.com");
        user.setNom("test");
        user.setPassword("test");
        user.setPrenom("test");
        user.setPseudo("test");
        user.setSexe("M");
        user.setRole(RoleEnum.client);

        userService.saveUser(user);

        verify(userRepository).save(isA(UtilisateurEntity.class));
        verify(passwordEncoder).encode(isA(CharSequence.class));
    }

    @Test
    void saveUserWithPseudo() {
        when(passwordEncoder.encode(Mockito.any())).thenReturn("secret");

        UtilisateurEntity utilisateurEntity = new UtilisateurEntity();
        utilisateurEntity.setCreationDate(LocalDate.now().atStartOfDay());
        utilisateurEntity.setEmail("test@test.com");
        utilisateurEntity.setNom("test");
        utilisateurEntity.setPassword("test");
        utilisateurEntity.setPrenom("test");
        utilisateurEntity.setRole(RoleEnum.client);
        utilisateurEntity.setStatus((byte) '0');
        utilisateurEntity.setSexe(SexeEnum.M);
        utilisateurEntity.setPseudo("test");
        utilisateurEntity.setId(1);
        when(userRepository.save(Mockito.any())).thenReturn(utilisateurEntity);

        UtilisateurDto user = new UtilisateurDto();
        user.setEmail("test@test.com");
        user.setNom("test");
        user.setPassword("test");
        user.setPrenom("test");
        user.setPseudo("testUpdate");
        user.setId(1);
        user.setRole(RoleEnum.createur);

        userService.saveUser(user);

        verify(userRepository).save(isA(UtilisateurEntity.class));
        verify(passwordEncoder).encode(isA(CharSequence.class));
    }

    @Test
    void testUpdateUser() {
        when(passwordEncoder.encode(Mockito.any())).thenReturn("secret");

        UtilisateurEntity utilisateurEntity = new UtilisateurEntity();
        utilisateurEntity.setCreationDate(LocalDate.now().atStartOfDay());
        utilisateurEntity.setEmail("test@test.com");
        utilisateurEntity.setNom("Test");
        utilisateurEntity.setPassword(passwordEncoder.encode("test"));
        utilisateurEntity.setPrenom("Test");
        utilisateurEntity.setRole(RoleEnum.client);
        utilisateurEntity.setStatus((byte) '0');
        utilisateurEntity.setId(1);

        UtilisateurEntity utilisateurEntity2 = new UtilisateurEntity();
        utilisateurEntity2.setCreationDate(LocalDate.now().atStartOfDay());
        utilisateurEntity2.setEmail("test-update@test.com");
        utilisateurEntity2.setNom("Update");
        utilisateurEntity2.setPassword("test-update");
        utilisateurEntity2.setPrenom("Test");
        utilisateurEntity2.setRole(RoleEnum.createur);
        Optional<UtilisateurEntity> ofResult = Optional.of(utilisateurEntity2);
        when(userRepository.save(Mockito.any())).thenReturn(utilisateurEntity);
        when(userRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        UtilisateurDto user = new UtilisateurDto();
        user.setId(utilisateurEntity.getId());
        user.setEmail(utilisateurEntity2.getEmail());
        user.setId(utilisateurEntity2.getId());
        user.setNom(utilisateurEntity2.getNom());
        user.setPassword(utilisateurEntity2.getPassword());
        user.setPrenom(utilisateurEntity2.getPrenom());
        user.setPseudo(utilisateurEntity2.getPseudo());
        user.setRole(utilisateurEntity2.getRole());

        userService.updateUser(user);
        verify(passwordEncoder).encode(isA(CharSequence.class));
    }

    @Test
    void testGetUsers() {
        // Arrange
        ArrayList<UtilisateurEntity> utilisateurEntityList = new ArrayList<>();
        UtilisateurEntity utilisateurEntity = new UtilisateurEntity();
        utilisateurEntity.setCreationDate(LocalDate.now().atStartOfDay());
        utilisateurEntity.setEmail("test@test.com");
        utilisateurEntity.setNom("Test");
        utilisateurEntity.setPassword("test");
        utilisateurEntity.setPrenom("Test");
        utilisateurEntity.setRole(RoleEnum.client);
        utilisateurEntity.setStatus((byte) '0');

        utilisateurEntityList.add(utilisateurEntity);

        when(userRepository.findAll()).thenReturn(utilisateurEntityList);

        List<UtilisateurEntity> actualUsers = userService.getUsers();

        verify(userRepository).findAll();
        assertSame(utilisateurEntityList, actualUsers);
    }

    @Test
    void testFindById() {
        // Arrange
        UtilisateurEntity utilisateurEntity = new UtilisateurEntity();
        utilisateurEntity.setCreationDate(LocalDate.now().atStartOfDay());
        utilisateurEntity.setEmail("test@test.fr");
        utilisateurEntity.setNom("Test");
        utilisateurEntity.setPassword("test");
        utilisateurEntity.setPrenom("Test");
        utilisateurEntity.setPseudo("Test");
        utilisateurEntity.setRole(RoleEnum.client);
        utilisateurEntity.setSexe(SexeEnum.F);
        utilisateurEntity.setStatus((byte) '0');
        utilisateurEntity.setId(1);
        Optional<UtilisateurEntity> ofResult = Optional.of(utilisateurEntity);
        when(userRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Optional<UtilisateurEntity> actualFindByIdResult = userService.findById(utilisateurEntity.getId());

        // Assert
        verify(userRepository).findById(eq(utilisateurEntity.getId()));
        assertSame(ofResult, actualFindByIdResult);
    }

    @Test
    void testDeleteUserById() {
        doNothing().when(userRepository).deleteById(Mockito.<Integer>any());

        UtilisateurEntity utilisateurEntity = new UtilisateurEntity();
        utilisateurEntity.setCreationDate(LocalDate.now().atStartOfDay());
        utilisateurEntity.setId(1);
        utilisateurEntity.setEmail("test@test.com");
        utilisateurEntity.setNom("Test");
        utilisateurEntity.setPassword("test");
        utilisateurEntity.setPrenom("Test");
        utilisateurEntity.setRole(RoleEnum.client);
        utilisateurEntity.setStatus((byte) '0');

        userService.deleteUserById(utilisateurEntity.getId());

        verify(userRepository).deleteById(eq(1));
    }

    @Test
    void testGetByEmail() {
        // Arrange
        UtilisateurEntity utilisateurEntity = new UtilisateurEntity();
        utilisateurEntity.setCreationDate(LocalDate.now().atStartOfDay());
        utilisateurEntity.setEmail("test.test@test.com");
        utilisateurEntity.setNom("Test");
        utilisateurEntity.setPassword("Test");
        utilisateurEntity.setPrenom("Test");
        utilisateurEntity.setPseudo("Test");
        utilisateurEntity.setRole(RoleEnum.client);
        utilisateurEntity.setSexe(SexeEnum.M);
        utilisateurEntity.setStatus((byte) '0');
        utilisateurEntity.setId(1);
        utilisateurEntity.setVerificationCode("Verification Code");
        Optional<UtilisateurEntity> ofResult = Optional.of(utilisateurEntity);
        when(userRepository.findByEmail(Mockito.any())).thenReturn(ofResult);

        Optional<UtilisateurEntity> actualByEmail = userService.getByEmail("test.test@test.com");

        verify(userRepository).findByEmail(eq("test.test@test.com"));
        assertSame(ofResult, actualByEmail);
    }

    @Test
    void testGetByEmail2() {
        // Arrange
        when(userRepository.findByEmail(Mockito.any())).thenThrow(
                new RuntimeException("Email non existant")
        );

        // Act and Assert
        assertThrows(
                RuntimeException.class, () -> userService.getByEmail("error.error@error.com")
        );
        verify(userRepository).findByEmail(eq("error.error@error.com"));
    }

    @Test
    void testFindByPrenomAndNom() {
        // Arrange
        UtilisateurEntity utilisateurEntity = new UtilisateurEntity();
        utilisateurEntity.setCreationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        utilisateurEntity.setEmail("test.test@test.com");
        utilisateurEntity.setNom("Test");
        utilisateurEntity.setPassword("Test");
        utilisateurEntity.setPrenom("Test");
        utilisateurEntity.setPseudo("Test");
        utilisateurEntity.setRole(RoleEnum.client);
        utilisateurEntity.setSexe(SexeEnum.F);
        utilisateurEntity.setStatus((byte) '0');
        utilisateurEntity.setId(1);
        utilisateurEntity.setVerificationCode("Verification Code");
        Optional<UtilisateurEntity> ofResult = Optional.of(utilisateurEntity);
        when(userRepository.findByPrenomAndNom(Mockito.<String>any(), Mockito.<String>any())).thenReturn(ofResult);

        // Act
        Optional<UtilisateurEntity> actualFindByPrenomAndNomResult = userService.findByPrenomAndNom("Prenom", "Nom");

        // Assert
        verify(userRepository).findByPrenomAndNom(eq("Prenom"), eq("Nom"));
        assertSame(ofResult, actualFindByPrenomAndNomResult);
    }
}
