package fr.joylee.services;

import fr.joylee.dto.UtilisateurDto;
import fr.joylee.entities.UtilisateurEntity;
import fr.joylee.enums.RoleEnum;
import fr.joylee.enums.SexeEnum;
import fr.joylee.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {

    private static final Logger log = LogManager.getLogger(UserService.class);

    private final UserRepository repo;

    private final PasswordEncoder passwordEncoder;

//    private final JavaMailSender mailSender;

    @Autowired
    public UserService(UserRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
//        this.mailSender = mailSender;
    }

    /**
     *
     * @return : Retourne la liste de tous les utilisateurs
     *
     */
    public List<UtilisateurEntity> getUsers() {
        return repo.findAll();
    }

    /**
     *
     * @param id : id de l'utilisateur recherché
     * @return : Renvoie l'utilisateur avec l'id correspondant
     *
     */
    public Optional<UtilisateurEntity> findById(int id) {
        return repo.findById(id);
    }

    /**
     *
     * @param prenom : Prénom de l'utilisateur
     * @param nom : Nom de l'utlisateur
     * @return : Renvoie l'utilisateur avec ce nom et ce prénom
     *
     */
    public Optional<UtilisateurEntity> findByPrenomAndNom(String prenom, String nom) {
        return repo.findByPrenomAndNom(prenom, nom);
    }

    /**
     *
     * @param id : id de l'utilisateur à supprimer
     *
     * @implNote : Supprimer un utilisateur
     */
    public void deleteUserById(int id) {
        repo.deleteById(id);
        log.info("L'utilisateur {} a été supprimé avec succès", id);
    }

    /**
     *
     * @implNote : Modifie les informations d'un utilisateur
     *
     */
    @Transactional
    public UtilisateurEntity updateUser(UtilisateurDto dto) {
        UtilisateurEntity entity = repo.findById(dto.getId()).get();
        if(dto.getSexe() != null) {
            switch (dto.getSexe()) {
                case "M", "m" -> entity.setSexe(SexeEnum.M);
                case "F", "f" -> entity.setSexe(SexeEnum.F);
                case "Autre", "autre" -> entity.setSexe(SexeEnum.autre);
            }
        }
        if(dto.getPseudo() != null) {
            entity.setPseudo(dto.getPseudo());
        }
        if(dto.getNom() != null) {
            entity.setNom(dto.getNom());
        }
        if(dto.getPrenom() != null) {
            entity.setPrenom(dto.getPrenom());
        }
        if(dto.getEmail() != null) {
            entity.setEmail(dto.getEmail());
        }
        if(dto.getPassword() != null) {
            entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        repo.save(entity);

        log.info("L'utilisateur {} a été modifié avec succès", dto.getId());

        return entity;
    }

    /**
     *
     * @param user : Informations de l'utilisateur
     *
     * @implNote : Crée un nouvel utilisateur
     */
    @Transactional
    public void saveUser(UtilisateurDto user) {
        UtilisateurEntity newUser = new UtilisateurEntity();

        newUser.setPrenom(user.getPrenom());
        newUser.setNom(user.getNom());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getPseudo() != null) {
            newUser.setPseudo(user.getPseudo());
        }
        newUser.setRole(RoleEnum.client);
        if(user.getSexe().equalsIgnoreCase("f")) {
            newUser.setSexe(SexeEnum.F);
        } if(user.getSexe().equalsIgnoreCase("m")) {
            newUser.setSexe(SexeEnum.M);
        } if(user.getSexe().equalsIgnoreCase("autre")) {
            newUser.setSexe(SexeEnum.autre);
        }
        newUser.setStatus((byte) 0);
        newUser.setCreationDate(LocalDateTime.now());
        newUser.setVerificationCode(generateRandomString());

        repo.save(newUser);

        // TODO : Ajouter l'url de l'application
//        sendVerificationEmail(newUser);

        log.info("L'utilisateur {} a été créé avec succès", newUser.getId());
    }

    /**
     *
     * @param email : email de l'utilisateur recherché
     * @return : Retourne l'utilisateur avec l'email correspondant
     *
     */
    public Optional<UtilisateurEntity> getByEmail(String email) {
        return repo.findByEmail(email);
    }

    /**
     *
     * @param user : Informaytions de l'utilisateur
     * @implNote : Permet d'envoyer un email pour la vérification du compte
     */
//    private void sendVerificationEmail(UtilisateurEntity user)
//            throws UnsupportedEncodingException, MessagingException {
//        String toAddress = user.getEmail();
//        String fromAddress = "joylee@gmail.com";
//        String senderName = "Joylee";
//        String subject = "Veuiller vérifier votre compte";
//        String sexe;
//        if (user.getSexe() == SexeEnum.F) {
//            sexe = "Madame";
//        } else {
//            sexe = "Monsieur";
//        }
//        String content = "Bonjour, " + sexe + " " + user.getPrenom() + " " + user.getNom() + "<br>"
//                + "Veuiller cliquer sur le lien ci-dessous pour valider et activer votre compte:<br>"
//                + "<h3><a href=\"[[URL]]\" target=\"_self\">Vérifier votre compte</a></h3>"
//                + "Merci, et bienvenue parmis nous!<br>"
//                + "Joylee.";
//
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//
//        helper.setFrom(fromAddress, senderName);
//        helper.setTo(toAddress);
//        helper.setSubject(subject);
//
//        String verifyURL = "test" + "/verify?code=" + user.getVerificationCode();
//
//        content = content.replace("[[URL]]", verifyURL);
//
//        helper.setText(content, true);
//
//        mailSender.send(message);
//    }

    public String generateRandomString() {
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 45;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
