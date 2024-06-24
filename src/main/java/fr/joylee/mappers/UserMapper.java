package fr.joylee.mappers;

import fr.joylee.dto.UtilisateurDto;
import fr.joylee.entities.UtilisateurEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UtilisateurDto toUserDTO(Optional<UtilisateurEntity> user);
    UtilisateurEntity toUser(UtilisateurDto userDTO);
}
