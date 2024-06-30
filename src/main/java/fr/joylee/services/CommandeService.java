package fr.joylee.services;

import fr.joylee.dto.CommandeDto;
import fr.joylee.dto.CommandeUpdateDto;
import fr.joylee.entities.CommandeEntity;
import fr.joylee.enums.StatusEnum;
import fr.joylee.mappers.CommandeMapper;
import fr.joylee.repositories.CommandeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommandeService {
    private static final Logger log = LoggerFactory.getLogger(CommandeService.class);
    private final CommandeRepository repo;

    private final UserService userService;
    private final CommandeMapper commandeMapper;

    @Autowired
    public CommandeService(CommandeRepository repo, UserService userService, CommandeMapper commandeMapper) {
        this.repo = repo;
        this.userService = userService;
        this.commandeMapper = commandeMapper;
    }

    public List<CommandeEntity> listCommandForUser(int utilisateurId) {
        return repo.getCommandesByUtilisateurId(utilisateurId);
    }

    public CommandeEntity getCommandeById(int id) {
        return repo.findById(id);
    }

    @Transactional
    public void saveNewCommande(CommandeDto dto) {
        CommandeEntity commande = new CommandeEntity();

        commande.setId(dto.getIdClient());
        commande.setPrixHt(dto.getPrixHT());
        commande.setStatus(StatusEnum.traitement);
        commande.setUtilisateur(userService.findById(dto.getIdClient()).get());
        commande.setPrixTtc(dto.getPrixTTC());
        commande.setAdresse(dto.getAdresse());

        repo.save(commande);

        log.info("La commande {} a été créée avec succès", commande.getId());
    }

    @Transactional
    public void updateCommande(CommandeUpdateDto dto) {
        CommandeEntity commande = repo.findById(dto.getId());

        commandeMapper.updateCommandeFromDto(dto, commande);

        repo.save(commande);

        log.info("La commande {} a été modifiée avec succès", dto.getId());
    }

    @Transactional
    public void deleteCommandeById(int id) {
        repo.deleteById(id);

        log.info("La commande {} a été supprimée avec succès", id);
    }
}
