package io.survey.web.rest;

import io.survey.repository.OptionQCMRepository;
import io.survey.service.OptionQCMService;
import io.survey.service.dto.OptionQCMDTO;
import io.survey.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * REST controller for managing {@link io.survey.model.OptionQCM}.
 */
@RestController
@RequestMapping("/api")
public class OptionQCMResource {

    private final Logger log = LoggerFactory.getLogger(OptionQCMResource.class);

    private static final String ENTITY_NAME = "optionQCM";

    private final OptionQCMService optionQCMService;

    private final OptionQCMRepository optionQCMRepository;

    public OptionQCMResource(OptionQCMService optionQCMService, OptionQCMRepository optionQCMRepository) {
        this.optionQCMService = optionQCMService;
        this.optionQCMRepository = optionQCMRepository;
    }

    /**
     * {@code POST  /option-qcms} : Create a new optionQCM.
     *
     * @param optionQCMDTO the optionQCMDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new optionQCMDTO, or with status {@code 400 (Bad Request)} if the optionQCM has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/option-qcms")
    public ResponseEntity<OptionQCMDTO> createOptionQCM(@RequestBody OptionQCMDTO optionQCMDTO) throws URISyntaxException {
        log.debug("REST request to save OptionQCM : {}", optionQCMDTO);
        if (optionQCMDTO.getId() != null) {
            throw new BadRequestAlertException("A new optionQCM cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OptionQCMDTO result = optionQCMService.save(optionQCMDTO);
        return ResponseEntity
            .created(new URI("/api/option-qcms/" + result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /option-qcms/:id} : Updates an existing optionQCM.
     *
     * @param id the id of the optionQCMDTO to save.
     * @param optionQCMDTO the optionQCMDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated optionQCMDTO,
     * or with status {@code 400 (Bad Request)} if the optionQCMDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the optionQCMDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/option-qcms/{id}")
    public ResponseEntity<OptionQCMDTO> updateOptionQCM(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OptionQCMDTO optionQCMDTO
    ) throws URISyntaxException {
        log.debug("REST request to update OptionQCM : {}, {}", id, optionQCMDTO);
        if (optionQCMDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, optionQCMDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!optionQCMRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        OptionQCMDTO result = optionQCMService.update(optionQCMDTO);
        return ResponseEntity
            .ok()
            .body(result);
    }

    /**
     * {@code PATCH  /option-qcms/:id} : Partial updates given fields of an existing optionQCM, field will ignore if it is null
     *
     * @param id the id of the optionQCMDTO to save.
     * @param optionQCMDTO the optionQCMDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated optionQCMDTO,
     * or with status {@code 400 (Bad Request)} if the optionQCMDTO is not valid,
     * or with status {@code 404 (Not Found)} if the optionQCMDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the optionQCMDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/option-qcms/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OptionQCMDTO> partialUpdateOptionQCM(@PathVariable(value = "id", required = false) final Long id,
                                                               @RequestBody OptionQCMDTO optionQCMDTO) throws URISyntaxException {
        log.debug("REST request to partial update OptionQCM partially : {}, {}", id, optionQCMDTO);
        if (optionQCMDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, optionQCMDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!optionQCMRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OptionQCMDTO> result = optionQCMService.partialUpdate(optionQCMDTO);

        return ResponseEntity.of(result);
    }

    /**
     * {@code GET  /option-qcms} : get all the optionQCMS.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of optionQCMS in body.
     */
    @GetMapping("/option-qcms")
    public ResponseEntity<List<OptionQCMDTO>> getAllOptionQCMS(Pageable pageable) {
        log.debug("REST request to get a page of OptionQCMS");
        Page<OptionQCMDTO> page = optionQCMService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    /**
     * {@code GET  /option-qcms/:id} : get the "id" optionQCM.
     *
     * @param id the id of the optionQCMDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the optionQCMDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/option-qcms/{id}")
    public ResponseEntity<OptionQCMDTO> getOptionQCM(@PathVariable Long id) {
        log.debug("REST request to get OptionQCM : {}", id);
        Optional<OptionQCMDTO> optionQCMDTO = optionQCMService.findOne(id);
        return ResponseEntity.of(optionQCMDTO);
    }

    /**
     * {@code DELETE  /option-qcms/:id} : delete the "id" optionQCM.
     *
     * @param id the id of the optionQCMDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/option-qcms/{id}")
    public ResponseEntity<Void> deleteOptionQCM(@PathVariable Long id) {
        log.debug("REST request to delete OptionQCM : {}", id);
        optionQCMService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
