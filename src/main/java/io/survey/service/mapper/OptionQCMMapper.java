package io.survey.service.mapper;

import io.survey.model.OptionQCM;
import io.survey.model.QCM;
import io.survey.service.dto.OptionQCMDTO;
import io.survey.service.dto.QCMDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link OptionQCM} and its DTO {@link OptionQCMDTO}.
 */
@Mapper(componentModel = "spring", uses = {QCMMapper.class, SectionMapper.class})
public interface OptionQCMMapper extends EntityMapper<OptionQCMDTO, OptionQCM> {
    //@Mapping(target = "qcm", source = "qcm", qualifiedByName = "qCMId")
    @Mapping(target = "qcm.section", ignore = true)
    @Mapping(source = "isDefault", target = "defaultOption")
    @Mapping(source = "section.id", target = "sectionId")
    OptionQCMDTO toDto(OptionQCM s);

    @Mapping(source = "defaultOption", target = "isDefault")
    @Mapping(source = "sectionId", target = "section.id")
    OptionQCM toEntity(OptionQCMDTO s);

    @Named("qCMId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QCMDTO toDtoQCMId(QCM qCM);
}
