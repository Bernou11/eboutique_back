package fr.joylee.services;

import fr.joylee.dto.VetementDto;
import fr.joylee.entities.VetementEntity;
import fr.joylee.enums.AgeEnum;
import fr.joylee.enums.PlacementEnum;
import fr.joylee.enums.SexeEnum;
import fr.joylee.enums.TypeEnum;
import fr.joylee.repositories.VetementRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {VetementsService.class})
@ExtendWith(SpringExtension.class)
public class VetementsServiceTest {
    @MockBean
    private VetementRepository vetementRepository;

    @Autowired
    private VetementsService vetementsService;

    @Test
    void testGetVetements() {
        ArrayList<VetementEntity> vetementEntityList = new ArrayList<>();
        when(vetementRepository.findAll()).thenReturn(vetementEntityList);

        // Act
        List<VetementEntity> actualVetements = vetementsService.getVetements();

        // Assert
        verify(vetementRepository).findAll();
        assertTrue(actualVetements.isEmpty());
        assertSame(vetementEntityList, actualVetements);
    }

    @Test
    void testGetVetementById() {
        VetementEntity vetementEntity = new VetementEntity();
        vetementEntity.setAge(AgeEnum.adulte);
        vetementEntity.setGenre(SexeEnum.F);
        vetementEntity.setPlacement(PlacementEnum.haut);
        vetementEntity.setSous_type("Sous type");
        vetementEntity.setType(TypeEnum.vêtement);
        vetementEntity.setVetement_id(1);
        Optional<VetementEntity> ofResult = Optional.of(vetementEntity);
        when(vetementRepository.findByVetement_id(anyInt())).thenReturn(ofResult);

        // Act
        Optional<VetementEntity> actualVetementById = vetementsService.getVetementById(1);

        // Assert
        verify(vetementRepository).findByVetement_id(eq(1));
        assertSame(ofResult, actualVetementById);
    }

    @Test
    void testSaveVetement() {
        VetementEntity vetementEntity = new VetementEntity();
        vetementEntity.setAge(AgeEnum.adulte);
        vetementEntity.setGenre(SexeEnum.F);
        vetementEntity.setPlacement(PlacementEnum.haut);
        vetementEntity.setSous_type("Sous type");
        vetementEntity.setType(TypeEnum.vêtement);
        vetementEntity.setVetement_id(1);
        when(vetementRepository.save(Mockito.any())).thenReturn(vetementEntity);

        VetementDto vetement = new VetementDto();
        vetement.setAge(AgeEnum.adulte);
        vetement.setGenre(SexeEnum.F);
        vetement.setPlacement(PlacementEnum.haut);
        vetement.setSousType("Sous Type");
        vetement.setType(TypeEnum.vêtement);

        vetementsService.saveVetement(vetement);

        verify(vetementRepository).save(isA(VetementEntity.class));
    }

    @Test
    void testUpdateVetementById() {
        VetementEntity vetementEntity = new VetementEntity();
        vetementEntity.setAge(AgeEnum.adulte);
        vetementEntity.setGenre(SexeEnum.F);
        vetementEntity.setPlacement(PlacementEnum.haut);
        vetementEntity.setSous_type("Sous type");
        vetementEntity.setType(TypeEnum.vêtement);
        vetementEntity.setVetement_id(1);
        Optional<VetementEntity> ofResult = Optional.of(vetementEntity);

        VetementEntity vetementEntity2 = new VetementEntity();
        vetementEntity2.setAge(AgeEnum.adulte);
        vetementEntity2.setGenre(SexeEnum.F);
        vetementEntity2.setPlacement(PlacementEnum.haut);
        vetementEntity2.setSous_type("Sous type");
        vetementEntity2.setType(TypeEnum.vêtement);
        vetementEntity2.setVetement_id(1);
        when(vetementRepository.save(Mockito.any())).thenReturn(vetementEntity2);
        when(vetementRepository.findByVetement_id(anyInt())).thenReturn(ofResult);

        VetementEntity modifications = new VetementEntity();
        modifications.setAge(AgeEnum.adulte);
        modifications.setGenre(SexeEnum.F);
        modifications.setPlacement(PlacementEnum.haut);
        modifications.setSous_type("Sous type");
        modifications.setType(TypeEnum.vêtement);
        modifications.setVetement_id(1);

        vetementsService.updateVetementById(1, modifications);

        verify(vetementRepository).findByVetement_id(eq(1));
        verify(vetementRepository).save(isA(VetementEntity.class));
    }

    @Test
    void testDeleteVetementById() {
        // Arrange
        doNothing().when(vetementRepository).deleteById(Mockito.<Integer>any());

        VetementEntity vetementEntity = new VetementEntity();
        vetementEntity.setAge(AgeEnum.adulte);
        vetementEntity.setGenre(SexeEnum.F);
        vetementEntity.setPlacement(PlacementEnum.haut);
        vetementEntity.setSous_type("Sous type");
        vetementEntity.setType(TypeEnum.vêtement);
        vetementEntity.setVetement_id(1);

        vetementRepository.save(vetementEntity);

        vetementsService.deleteVetementById(vetementEntity.getVetement_id());

        verify(vetementRepository).deleteById(eq(vetementEntity.getVetement_id()));

        Optional<VetementEntity> deletedVetement = vetementsService.getVetementById(vetementEntity.getVetement_id());

        assertTrue(deletedVetement.isEmpty());
    }
}
