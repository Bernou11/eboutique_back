package fr.joylee.mappers;

import fr.joylee.dto.CommandeUpdateDto;
import fr.joylee.entities.CommandeEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CommandeMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCommandeFromDto(CommandeUpdateDto dto, @MappingTarget CommandeEntity entity);
}
