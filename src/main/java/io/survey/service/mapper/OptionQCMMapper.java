package io.survey.service.mapper;

import io.survey.model.OptionQCM;
import io.survey.model.QCM;
import io.survey.service.dto.OptionQCMDTO;
import io.survey.service.dto.QCMDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link OptionQCM} and its DTO {@link OptionQCMDTO}.
 */
@Mapper(componentModel = "spring")
public interface OptionQCMMapper extends EntityMapper<OptionQCMDTO, OptionQCM> {
    @Mapping(target = "qcm", source = "qcm", qualifiedByName = "qCMId")
    @Mapping(target = "qcm", source = "qcm", qualifiedByName = "qCMId")
    OptionQCMDTO toDto(OptionQCM s);

    @Named("qCMId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QCMDTO toDtoQCMId(QCM qCM);
}
