package io.survey.service;

import io.survey.model.Formulaire;
import io.survey.model.LigneFormulaire;
import io.survey.model.Section;
import io.survey.repository.FormulaireRepository;
import io.survey.repository.LigneFormulaireRepository;
import io.survey.repository.SectionRepository;
import io.survey.service.dto.FormulaireDTO;
import io.survey.service.dto.export.QuestionnaireFormulaireDTO;
import io.survey.service.dto.export.QuestionnaireFormulaireSectionDTO;
import io.survey.service.dto.export.QuestionnaireLigneFormulaireDTO;
import io.survey.service.mapper.export.QuestionnaireFormulaireMapper;
import io.survey.service.mapper.export.QuestionnaireFormulaireSectionMapper;
import io.survey.service.mapper.export.QuestionnaireLigneFormulaireMapper;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExportService {

    private FormulaireRepository formulaireRepository;

    private SectionRepository sectionRepository;

    private LigneFormulaireRepository ligneFormulaireRepository;

    private QuestionnaireFormulaireMapper questionnaireFormulaireMapper;

    private QuestionnaireFormulaireSectionMapper questionnaireFormulaireSectionMapper;

    private QuestionnaireLigneFormulaireMapper questionnaireLigneFormulaireMapper;

    public ExportService(FormulaireRepository formulaireRepository,
                         SectionRepository sectionRepository,
                         LigneFormulaireRepository ligneFormulaireRepository,
                         QuestionnaireFormulaireMapper questionnaireFormulaireMapper,
                         QuestionnaireFormulaireSectionMapper questionnaireFormulaireSectionMapper,
                         QuestionnaireLigneFormulaireMapper questionnaireLigneFormulaireMapper) {

        this.formulaireRepository = formulaireRepository;
        this.sectionRepository = sectionRepository;
        this.ligneFormulaireRepository = ligneFormulaireRepository;
        this.questionnaireFormulaireMapper = questionnaireFormulaireMapper;
        this.questionnaireFormulaireSectionMapper = questionnaireFormulaireSectionMapper;
        this.questionnaireLigneFormulaireMapper = questionnaireLigneFormulaireMapper;
    }

    public List<QuestionnaireFormulaireDTO> getResponses() {
        return formulaireRepository.findAll()
                .stream().map(this::getResponses)
                .collect(Collectors.toList());
    }

    public QuestionnaireFormulaireDTO getResponses(Formulaire formulaire) {
        return getResponses(formulaire.getId());
    }

    public QuestionnaireFormulaireDTO getResponses(Long formulaireId) {
        Optional<QuestionnaireFormulaireDTO> formulaire = formulaireRepository.findById(formulaireId)
                .map(questionnaireFormulaireMapper::toDto)
                .map(this::enrichSections);

        return formulaire.orElse(new QuestionnaireFormulaireDTO());
    }

    public QuestionnaireFormulaireDTO enrichSections(QuestionnaireFormulaireDTO formulaire) {
        List<QuestionnaireFormulaireSectionDTO> questionnaireFormulaireSectionsDTO = questionnaireFormulaireSectionMapper
                .toDto(sectionRepository.findByQuestionnaire_Id(formulaire.getQuestionnaireId()));
        questionnaireFormulaireSectionsDTO.forEach(section -> {
            section.setLignesFormulaire(enrichLigneFormulaire(formulaire, section));
        });
        formulaire.setSections(questionnaireFormulaireSectionsDTO);
        return formulaire;
    }

    public List<QuestionnaireLigneFormulaireDTO> enrichLigneFormulaire(QuestionnaireFormulaireDTO formulaire,
                                                                       QuestionnaireFormulaireSectionDTO section) {
        List<LigneFormulaire> lignesFormulaire = ligneFormulaireRepository
                .findByFormulaire_IdAndQuestion_Section_Id(formulaire.getFormulaireId(), section.getId());

        return questionnaireLigneFormulaireMapper.toDto(lignesFormulaire);
    }

    public ByteArrayInputStream generateFile(List<FormulaireDTO> responses, File filelocator) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //File file = ResourceUtils.getFile("classpath:static/templates/listing.xlsx");
        try (InputStream is = new FileInputStream(filelocator)) {
            Context context = new Context();
            context.putVar("formulaires", responses);
            JxlsHelper.getInstance().processTemplate(is, outputStream, context);
        }
        return new ByteArrayInputStream(outputStream.toByteArray());
    }
}
