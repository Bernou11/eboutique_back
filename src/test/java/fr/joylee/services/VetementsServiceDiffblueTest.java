package fr.joylee.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import fr.joylee.dto.VetementDto;
import fr.joylee.entities.VetementEntity;
import fr.joylee.enums.AgeEnum;
import fr.joylee.enums.PlacementEnum;
import fr.joylee.enums.SexeEnum;
import fr.joylee.enums.TypeEnum;
import fr.joylee.repositories.VetementRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {VetementsService.class})
@ExtendWith(SpringExtension.class)
class VetementsServiceDiffblueTest {

    @MockBean
    private VetementRepository vetementRepository;

    @Autowired
    private VetementsService vetementsService;

    /**
     * Method under test: {@link VetementsService#getVetements()}
     */

    /**
     * Method under test: {@link VetementsService#saveVetement(VetementDto)}
     */

    /**
     * Method under test: {@link VetementsService#deleteVetementById(int)}
     */

    /**
     * Method under test:
     * {@link VetementsService#updateVetementById(int, VetementEntity)}
     */

    /**
     * Method under test: {@link VetementsService#getVetementById(int)}
     */

}
