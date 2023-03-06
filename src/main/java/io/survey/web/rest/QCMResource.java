package io.survey.web.rest;

import io.survey.repository.QCMRepository;
import io.survey.service.QCMService;
import io.survey.service.dto.QCMDTO;
import io.survey.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * REST controller for managing {@link io.survey.model.QCM}.
 */
@RestController
@RequestMapping("/api")
public class QCMResource {

    private final Logger log = LoggerFactory.getLogger(QCMResource.class);

    private static final String ENTITY_NAME = "qCM";

    private final QCMService qCMService;

    private final QCMRepository qCMRepository;

    public QCMResource(QCMService qCMService, QCMRepository qCMRepository) {
        this.qCMService = qCMService;
        this.qCMRepository = qCMRepository;
    }

    /**
     * {@code POST  /qcms} : Create a new qCM.
     *
     * @param qCMDTO the qCMDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new qCMDTO, or with status {@code 400 (Bad Request)} if the qCM has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/qcms")
    public ResponseEntity<QCMDTO> createQCM(@RequestBody QCMDTO qCMDTO) throws URISyntaxException {
        log.debug("REST request to save QCM : {}", qCMDTO);
        if (qCMDTO.getId() != null) {
            throw new BadRequestAlertException("A new qCM cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QCMDTO result = qCMService.save(qCMDTO);
        return ResponseEntity
            .created(new URI("/api/qcms/" + result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /qcms/:id} : Updates an existing qCM.
     *
     * @param id the id of the qCMDTO to save.
     * @param qCMDTO the qCMDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qCMDTO,
     * or with status {@code 400 (Bad Request)} if the qCMDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the qCMDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/qcms/{id}")
    public ResponseEntity<QCMDTO> updateQCM(@PathVariable(value = "id", required = false) final Long id, @RequestBody QCMDTO qCMDTO)
        throws URISyntaxException {
        log.debug("REST request to update QCM : {}, {}", id, qCMDTO);
        if (qCMDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, qCMDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!qCMRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        QCMDTO result = qCMService.update(qCMDTO);
        return ResponseEntity
            .ok()
            .body(result);
    }

    /**
     * {@code PATCH  /qcms/:id} : Partial updates given fields of an existing qCM, field will ignore if it is null
     *
     * @param id the id of the qCMDTO to save.
     * @param qCMDTO the qCMDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qCMDTO,
     * or with status {@code 400 (Bad Request)} if the qCMDTO is not valid,
     * or with status {@code 404 (Not Found)} if the qCMDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the qCMDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/qcms/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<QCMDTO> partialUpdateQCM(@PathVariable(value = "id", required = false) final Long id, @RequestBody QCMDTO qCMDTO)
        throws URISyntaxException {
        log.debug("REST request to partial update QCM partially : {}, {}", id, qCMDTO);
        if (qCMDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, qCMDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!qCMRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<QCMDTO> result = qCMService.partialUpdate(qCMDTO);

        return ResponseEntity.of(result);
    }

    /**
     * {@code GET  /qcms} : get all the qCMS.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of qCMS in body.
     */
    @GetMapping("/qcms")
    public ResponseEntity<List<QCMDTO>> getAllQCMS(Pageable pageable) {
        log.debug("REST request to get a page of QCMS");
        Page<QCMDTO> page = qCMService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    /**
     * {@code GET  /qcms/:id} : get the "id" qCM.
     *
     * @param id the id of the qCMDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the qCMDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/qcms/{id}")
    public ResponseEntity<QCMDTO> getQCM(@PathVariable Long id) {
        log.debug("REST request to get QCM : {}", id);
        Optional<QCMDTO> qCMDTO = qCMService.findOne(id);
        return ResponseEntity.of(qCMDTO);
    }

    /**
     * {@code DELETE  /qcms/:id} : delete the "id" qCM.
     *
     * @param id the id of the qCMDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/qcms/{id}")
    public ResponseEntity<Void> deleteQCM(@PathVariable Long id) {
        log.debug("REST request to delete QCM : {}", id);
        qCMService.delete(id);
        return ResponseEntity
            .noContent()
            .build();
    }
}
