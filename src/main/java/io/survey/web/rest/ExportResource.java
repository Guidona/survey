package io.survey.web.rest;

import io.survey.service.ExportService;
import io.survey.service.FormulaireService;
import io.survey.service.dto.FormulaireDTO;
import io.survey.service.dto.export.QuestionnaireFormulaireDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "api/")
@Slf4j
public class ExportResource {

    private ExportService exportService;
    private FormulaireService formulaireService;

    public ExportResource(ExportService exportService, FormulaireService formulaireService) {
        this.exportService = exportService;
        this.formulaireService = formulaireService;
    }

    @GetMapping("/export/formulaires")
    public ResponseEntity<?> exportFormulaires(@RequestParam(value = "questionnaireId", required = false) Long questionnaireId,
                                               @RequestParam(value = "formulaireId", required = false) Long formulaireId) throws IOException {
        log.debug("REST request to export formulaires");
        List<QuestionnaireFormulaireDTO> responses = new ArrayList<>();
        if(questionnaireId != null)
            responses = exportService.getResponsesByQuestionnaire(questionnaireId);
        else if(formulaireId != null)
            responses = Collections.singletonList(exportService.getResponses(formulaireId));
        else responses = exportService.getResponses();

        File templateFile = ResourceUtils.getFile("classpath:static/templates/formulaires.xlsx");
        InputStreamResource file = new InputStreamResource(exportService.generateFile(responses, templateFile));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename="
                                .concat("FORMULAIRES".concat(".xlsx") + ""))
                .contentType(MediaType.APPLICATION_OCTET_STREAM).body(file);
    }

    @GetMapping("/data/formulaires")
    public ResponseEntity<?> exportData(@RequestParam(value = "questionnaireId", required = false) Long questionnaireId,
                                        @RequestParam(value = "formulaireId", required = false) Long formulaireId) throws IOException {
        if(questionnaireId != null)
            return ResponseEntity.ok(exportService.getResponsesByQuestionnaire(questionnaireId));
        if(formulaireId != null)
            return ResponseEntity.ok(Collections.singletonList(exportService.getResponses(formulaireId)));
        return ResponseEntity.ok(exportService.getResponses());
    }


}
