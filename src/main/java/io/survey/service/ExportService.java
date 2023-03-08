package io.survey.service;

import io.survey.service.dto.FormulaireDTO;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class ExportService {

    private FormulaireService formulaireService;

    private SectionService sectionService;

    public ExportService(FormulaireService formulaireService, SectionService sectionService) {
        this.formulaireService = formulaireService;
        this.sectionService = sectionService;
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
