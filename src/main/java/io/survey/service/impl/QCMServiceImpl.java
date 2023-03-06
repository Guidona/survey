package io.survey.service.impl;

import io.survey.model.QCM;
import io.survey.repository.QCMRepository;
import io.survey.service.QCMService;
import io.survey.service.dto.QCMDTO;
import io.survey.service.mapper.QCMMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link QCM}.
 */
@Service
@Transactional
public class QCMServiceImpl implements QCMService {

    private final Logger log = LoggerFactory.getLogger(QCMServiceImpl.class);

    private final QCMRepository qCMRepository;

    private final QCMMapper qCMMapper;

    public QCMServiceImpl(QCMRepository qCMRepository, QCMMapper qCMMapper) {
        this.qCMRepository = qCMRepository;
        this.qCMMapper = qCMMapper;
    }

    @Override
    public QCMDTO save(QCMDTO qCMDTO) {
        log.debug("Request to save QCM : {}", qCMDTO);
        QCM qCM = qCMMapper.toEntity(qCMDTO);
        qCM = qCMRepository.save(qCM);
        return qCMMapper.toDto(qCM);
    }

    @Override
    public QCMDTO update(QCMDTO qCMDTO) {
        log.debug("Request to update QCM : {}", qCMDTO);
        QCM qCM = qCMMapper.toEntity(qCMDTO);
        // no save call needed as we have no fields that can be updated
        return qCMMapper.toDto(qCM);
    }

    @Override
    public Optional<QCMDTO> partialUpdate(QCMDTO qCMDTO) {
        log.debug("Request to partially update QCM : {}", qCMDTO);

        return qCMRepository
            .findById(qCMDTO.getId())
            .map(existingQCM -> {
                qCMMapper.partialUpdate(existingQCM, qCMDTO);

                return existingQCM;
            })
            // .map(qCMRepository::save)
            .map(qCMMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<QCMDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QCMS");
        return qCMRepository.findAll(pageable).map(qCMMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<QCMDTO> findOne(Long id) {
        log.debug("Request to get QCM : {}", id);
        return qCMRepository.findById(id).map(qCMMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete QCM : {}", id);
        qCMRepository.deleteById(id);
    }
}
