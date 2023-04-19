package io.survey.service;

import io.survey.model.Formulaire;
import io.survey.model.LigneFormulaire;
import io.survey.repository.FormulaireRepository;
import io.survey.repository.LigneFormulaireRepository;
import io.survey.repository.SectionRepository;
import io.survey.service.dto.export.FlatQuestionnaireFormulaireDTO;
import io.survey.service.dto.export.QuestionnaireFormulaireDTO;
import io.survey.service.dto.export.QuestionnaireFormulaireSectionDTO;
import io.survey.service.dto.export.QuestionnaireLigneFormulaireDTO;
import io.survey.service.mapper.export.FlatQuestionnaireFormulaireMapper;
import io.survey.service.mapper.export.QuestionnaireFormulaireMapper;
import io.survey.service.mapper.export.QuestionnaireFormulaireSectionMapper;
import io.survey.service.mapper.export.QuestionnaireLigneFormulaireMapper;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExportService {

    private FormulaireRepository formulaireRepository;

    private SectionRepository sectionRepository;

    private LigneFormulaireRepository ligneFormulaireRepository;

    private QuestionnaireFormulaireMapper questionnaireFormulaireMapper;

    private QuestionnaireFormulaireSectionMapper questionnaireFormulaireSectionMapper;

    private QuestionnaireLigneFormulaireMapper questionnaireLigneFormulaireMapper;

    private FlatQuestionnaireFormulaireMapper flatQuestionnaireFormulaireMapper;

    public ExportService(FormulaireRepository formulaireRepository,
                         SectionRepository sectionRepository,
                         LigneFormulaireRepository ligneFormulaireRepository,
                         QuestionnaireFormulaireMapper questionnaireFormulaireMapper,
                         QuestionnaireFormulaireSectionMapper questionnaireFormulaireSectionMapper,
                         QuestionnaireLigneFormulaireMapper questionnaireLigneFormulaireMapper,
                         FlatQuestionnaireFormulaireMapper flatQuestionnaireFormulaireMapper) {

        this.formulaireRepository = formulaireRepository;
        this.sectionRepository = sectionRepository;
        this.ligneFormulaireRepository = ligneFormulaireRepository;
        this.questionnaireFormulaireMapper = questionnaireFormulaireMapper;
        this.questionnaireFormulaireSectionMapper = questionnaireFormulaireSectionMapper;
        this.questionnaireLigneFormulaireMapper = questionnaireLigneFormulaireMapper;
        this.flatQuestionnaireFormulaireMapper = flatQuestionnaireFormulaireMapper;
    }

    public List<QuestionnaireFormulaireDTO> getResponses() {
        return formulaireRepository.findAllByOrderByNumeroAsc()
                .stream().map(this::getResponses)
                .collect(Collectors.toList());
    }

    public List<QuestionnaireFormulaireDTO> getResponsesByQuestionnaire(Long questionnaireId) {
        return formulaireRepository.findByQuestionnaire_IdOrderByNumeroAsc(questionnaireId)
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

    public List<FlatQuestionnaireFormulaireDTO> getFlatResponses() {
        return formulaireRepository.findAllByOrderByNumeroAsc()
                .stream().map(this::getFlatResponses)
                .collect(Collectors.toList());
    }

    public List<FlatQuestionnaireFormulaireDTO> getFlatResponsesByQuestionnaire(Long questionnaireId) {
        return formulaireRepository.findByQuestionnaire_IdOrderByNumeroAsc(questionnaireId)
                .stream().map(this::getFlatResponses)
                .collect(Collectors.toList());
    }

    public FlatQuestionnaireFormulaireDTO getFlatResponses(Formulaire formulaire) {
        return getFlatResponses(formulaire.getId());
    }

    public FlatQuestionnaireFormulaireDTO getFlatResponses(Long formulaireId) {
        Optional<FlatQuestionnaireFormulaireDTO> formulaire = formulaireRepository.findById(formulaireId)
                .map(flatQuestionnaireFormulaireMapper::toDto)
                .map(this::enrichLigneFormulaire);

        return formulaire.orElse(new FlatQuestionnaireFormulaireDTO());
    }

    public QuestionnaireFormulaireDTO enrichSections(QuestionnaireFormulaireDTO formulaire) {
        List<QuestionnaireFormulaireSectionDTO> questionnaireFormulaireSectionsDTO = questionnaireFormulaireSectionMapper
                .toDto(sectionRepository.findByQuestionnaire_IdOrderByOrdreAsc(formulaire.getQuestionnaireId()));
        questionnaireFormulaireSectionsDTO.forEach(section -> section.setLignesFormulaire(enrichLigneFormulaire(formulaire, section)));
        formulaire.setSections(questionnaireFormulaireSectionsDTO);
        return formulaire;
    }

    public Set<QuestionnaireLigneFormulaireDTO> enrichLigneFormulaire(QuestionnaireFormulaireDTO formulaire,
                                                                       QuestionnaireFormulaireSectionDTO section) {
        List<LigneFormulaire> lignesFormulaire = ligneFormulaireRepository
                .findByFormulaire_IdAndQuestion_Section_IdOrderByQuestion_OrdreAsc(formulaire.getFormulaireId(), section.getId());

        return new HashSet<>(questionnaireLigneFormulaireMapper.toDto(lignesFormulaire));
    }

    public FlatQuestionnaireFormulaireDTO enrichLigneFormulaire(FlatQuestionnaireFormulaireDTO formulaire) {
        List<LigneFormulaire> lignesFormulaire = ligneFormulaireRepository
                .findByFormulaire_IdOrderByQuestion_IdAsc(formulaire.getFormulaireId());

        formulaire.setLignesFormulaire(new LinkedHashSet<>(questionnaireLigneFormulaireMapper.toDto(lignesFormulaire)));
        return formulaire;
    }

    public ByteArrayInputStream generateFile(List<QuestionnaireFormulaireDTO> responses, File filelocator) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //File file = ResourceUtils.getFile("classpath:static/templates/listing.xlsx");
        try (InputStream is = new FileInputStream(filelocator)) {
            Context context = new Context();
            context.putVar("formulaires", responses);
            context.putVar("sheetNames", responses.stream()
                    .map(QuestionnaireFormulaireDTO::getNumero)
                    .map(Object::toString)
                    .collect(Collectors.toList()));
            JxlsHelper.getInstance().processTemplate(is, outputStream, context);
        }
        return new ByteArrayInputStream(outputStream.toByteArray());
    }
}
