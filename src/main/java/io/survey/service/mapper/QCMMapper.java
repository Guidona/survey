package io.survey.service.mapper;

import io.survey.model.QCM;
import io.survey.service.dto.QCMDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link QCM} and its DTO {@link QCMDTO}.
 */
@Mapper(componentModel = "spring")
public interface QCMMapper extends EntityMapper<QCMDTO, QCM> {}
