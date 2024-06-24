package fr.joylee.services;

import fr.joylee.dto.UtilisateurDto;
import fr.joylee.entities.UtilisateurEntity;
import fr.joylee.enums.RoleEnum;
import fr.joylee.mappers.UserMapper;
import fr.joylee.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertSame;
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
    private UserMapper userMapper;

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

        userService.saveUser(user);

        verify(userRepository).save(isA(UtilisateurEntity.class));
        verify(passwordEncoder).encode(isA(CharSequence.class));
    }

    /**
     * Method under test: {@link UserService#updateUser(int, UtilisateurDto)}
     */
    @Test
    void testUpdateUser() {
        when(passwordEncoder.encode(Mockito.any())).thenReturn("secret");

        UtilisateurEntity utilisateurEntity = new UtilisateurEntity();
        utilisateurEntity.setCreationDate(LocalDate.now().atStartOfDay());
        utilisateurEntity.setEmail("test@test.com");
        utilisateurEntity.setNom("Test");
        utilisateurEntity.setPassword("test");
        utilisateurEntity.setPrenom("Test");
        utilisateurEntity.setRole(RoleEnum.client);
        utilisateurEntity.setStatus((byte) '1');

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
        user.setEmail(utilisateurEntity2.getEmail());
        user.setId(utilisateurEntity2.getUtilisateur_id());
        user.setNom(utilisateurEntity2.getNom());
        user.setPassword(utilisateurEntity2.getPassword());
        user.setPrenom(utilisateurEntity2.getPrenom());
        user.setPseudo(utilisateurEntity2.getPseudo());
        user.setRole(RoleEnum.createur);

        // Act
        userService.updateUser(1, user);

        // Assert
        verify(userMapper).toUser(isA(UtilisateurDto.class));
        verify(userRepository).findById(eq(1));
        verify(userRepository).save(isA(UtilisateurEntity.class));
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
        utilisateurEntity.setStatus((byte) '1');

        utilisateurEntityList.add(utilisateurEntity);

        when(userRepository.findAll()).thenReturn(utilisateurEntityList);

        List<UtilisateurEntity> actualUsers = userService.getUsers();

        verify(userRepository).findAll();
        assertSame(utilisateurEntityList, actualUsers);
    }
}
