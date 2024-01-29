package com.app.export;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Faculty;
import com.app.repo.FacultyRepo;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class UserExportToPdfService extends ReportAbstract {
	@Autowired
	FacultyRepo facultyRepo;

	public void writeTableData(PdfPTable table, Object data) {
		List<Faculty> list = facultyRepo.findAll();

		// for auto wide by paper size
		table.setWidthPercentage(100);
		// cell
		PdfPCell cell = new PdfPCell();
		for (Faculty item : list) {

			cell.setPhrase(new Phrase(item.getFacultyName(), getFontContent()));
			table.addCell(cell);

			cell.setPhrase(new Phrase(item.getFacultyAddress(), getFontContent()));
			table.addCell(cell);

			cell.setPhrase(new Phrase(item.getFacultyEmail(), getFontContent()));
			table.addCell(cell);

			cell.setPhrase(new Phrase(item.getFacultyGender(), getFontContent()));
			table.addCell(cell);

		}

	}

	public void exportToPDF(HttpServletResponse response, Object data) throws IOException {

		// init respose
		response = initResponseForExportPdf(response, "USER");

		// define paper size
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		// start document
		document.open();

		// title
		Paragraph title = new Paragraph("Report User", getFontTitle());
		title.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(title);

		// subtitel
		Paragraph subtitel = new Paragraph("Report Date : 09/12/2022", getFontSubtitle());
		subtitel.setAlignment(Paragraph.ALIGN_LEFT);
		document.add(subtitel);

		enterSpace(document);

		// table header
		String[] headers = new String[] { "Name", "Address", "Email", "Gender" };
		PdfPTable tableHeader = new PdfPTable(4);
		writeTableHeaderPdf(tableHeader, headers);
		document.add(tableHeader);

		// table content
		PdfPTable tableData = new PdfPTable(4);
		writeTableData(tableData, data);
		document.add(tableData);

		document.close();
	}

}