package fr.joylee.services;

import fr.joylee.dto.UtilisateurDto;
import fr.joylee.entities.UtilisateurEntity;
import fr.joylee.enums.RoleEnum;
import fr.joylee.enums.SexeEnum;
import fr.joylee.mappers.UserMapper;
import fr.joylee.repositories.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {

    private static final Logger log = LogManager.getLogger(UserService.class);

    private final UserRepository repo;

    private final PasswordEncoder passwordEncoder;

    private final JavaMailSender mailSender;

    @Autowired
    public UserService(UserRepository repo, PasswordEncoder passwordEncoder, JavaMailSender mailSender, UserMapper userMapper) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
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
     * @param id : id de l'utilisateur à modifier
     * @param user : Anciennes informations de l'utilisateur
     *
     * @implNote : Modifie les informations d'un utilisateur
     */
    @Transactional
    public void updateUser(int id, UtilisateurDto user) {
        Optional<UtilisateurEntity> oldUser = findById(id);

        UtilisateurEntity oldUserEntity = oldUser.orElseThrow(() ->
                new RuntimeException("Utilisateur not found with id " + id));;
        oldUserEntity.setEmail(user.getEmail());
        oldUserEntity.setNom(user.getNom());
        oldUserEntity.setPrenom(user.getPrenom());
        oldUserEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getPseudo() != null) {
            oldUserEntity.setPseudo(user.getPseudo());
        }

        repo.save(oldUserEntity);

        log.info("L'utilisateur {} a été modifié avec succès", user.getId());
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
        newUser.setSexe(user.getSexe());
        newUser.setStatus((byte) 0);
        newUser.setCreationDate(LocalDateTime.now());
        newUser.setVerificationCode(generateRandomString());

        repo.save(newUser);

        // TODO : Ajouter l'url de l'application
//        sendVerificationEmail(newUser);

        log.info("L'utilisateur {} a été créé avec succès", newUser.getUtilisateur_id());
    }

    /**
     *
     * @param user : Informaytions de l'utilisateur
     * @implNote : Permet d'envoyer un email pour la vérification du compte
     */
    private void sendVerificationEmail(UtilisateurEntity user)
            throws UnsupportedEncodingException, MessagingException {
        String toAddress = user.getEmail();
        String fromAddress = "joylee@gmail.com";
        String senderName = "Joylee";
        String subject = "Veuiller vérifier votre compte";
        String sexe;
        if (user.getSexe() == SexeEnum.F) {
            sexe = "Madame";
        } else {
            sexe = "Monsieur";
        }
        String content = "Bonjour, " + sexe + " " + user.getPrenom() + " " + user.getNom() + "<br>"
                + "Veuiller cliquer sur le lien ci-dessous pour valider et activer votre compte:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">Vérifier votre compte</a></h3>"
                + "Merci, et bienvenue parmis nous!<br>"
                + "Joylee.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        String verifyURL = "test" + "/verify?code=" + user.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);
    }

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
