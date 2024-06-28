//package fr.joylee.services;
//
//import fr.joylee.dto.VetementDto;
//import fr.joylee.entities.UtilisateurEntity;
//import fr.joylee.entities.VetementEntity;
//import fr.joylee.enums.RoleEnum;
//import fr.joylee.repositories.VetementRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class VetementsService {
//    private static final Logger log = LoggerFactory.getLogger(VetementsService.class);
//    private final VetementRepository repo;
//
//    @Autowired
//    public VetementsService(VetementRepository repo) {
//        this.repo = repo;
//    }
//
//    public List<VetementEntity> getVetements() {
//        return repo.findAll();
//    }
//
//    public Optional<VetementEntity> getVetementById(int id) {
//        return repo.findByVetement_id(id);
//    }
//
//    /**
//     *
//     * @param vetement : Informations du vêtement à enregistrer
//     *
//     */
//    public void saveVetement(VetementDto vetement) {
//        VetementEntity newVetement = new VetementEntity();
//
//        newVetement.setAge(newVetement.getAge());
//        newVetement.setType(newVetement.getType());
//        newVetement.setGenre(vetement.getGenre());
//        newVetement.setPlacement(vetement.getPlacement());
//        newVetement.setSous_type(vetement.getSousType());
//
//        repo.save(newVetement);
//        log.info("Le nouveau vêtement a été enregistré avec succès");
//    }
//
//    /**
//     *
//     * @param id : ID du vêtement à modifier
//     * @param modifications : Nouvelles informations du vêtement à modifier
//     *
//     */
//    public void updateVetementById(int id, VetementEntity modifications) {
//        Optional<VetementEntity> oldVetement = repo.findByVetement_id(id);
//        if(modifications.getGenre() != null) {
//            oldVetement.get().setGenre(modifications.getGenre());
//        }
//        if(modifications.getAge() != null) {
//            oldVetement.get().setAge(modifications.getAge());
//        }
//        if(modifications.getType() != null) {
//            oldVetement.get().setType(modifications.getType());
//        }
//        if(modifications.getSous_type() != null) {
//            oldVetement.get().setSous_type(modifications.getSous_type());
//        }
//        if(modifications.getPlacement() != null) {
//            oldVetement.get().setPlacement(modifications.getPlacement());
//        }
//        repo.save(modifications);
//
//        log.info("Les informations du vêtement ont été modifiées avec succès");
//    }
//
//    /**
//     *
//     * @param id : ID du vêtement à supprimer
//     *
//     * @implNote : Supprime l'utilisateur avec l'id indiqué
//     *
//     */
//    public void deleteVetementById(int id) {
//        repo.deleteById(id);
//        log.info("Le vêtement avec l'id {} a été supprimé avec succès", id);
//    }
//}
