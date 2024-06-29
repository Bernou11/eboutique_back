package fr.joylee.mappers;

import fr.joylee.dto.ArticleDto;
import fr.joylee.entities.ArticleEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateArticleFromDto(ArticleDto dto, @MappingTarget ArticleEntity entity);
}
