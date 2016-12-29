package lianxue.online.service;

import java.util.List;
import lianxue.online.model.PdfInfo;

public interface PdfService {

	List<PdfInfo> selectPdfList(PdfInfo pdfInfo);

	void editPdf(PdfInfo pdfInfo);

	PdfInfo selecPdf(Long id);

	void deletePdf(Long id);

	void addPdf(PdfInfo pdfInfo);

}
