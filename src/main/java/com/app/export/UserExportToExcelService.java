package com.app.export;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Faculty;
import com.app.repo.FacultyRepo;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class UserExportToExcelService extends ReportAbstract {
	@Autowired
	FacultyRepo facultyRepo;

	public void writeTableData(Object data) {
		// data
		List<Faculty> list = facultyRepo.findAll();

		// font style content
		CellStyle style = getFontContentExcel();

		// starting write on row
		int startRow = 2;

		// write content
		for (Faculty faculty : list) {
			Row row = sheet.createRow(startRow++);
			int columnCount = 0;
			createCell(row, columnCount++, faculty.getFacultyName(), style);
			createCell(row, columnCount++, faculty.getFacultyAddress(), style);
			createCell(row, columnCount++, faculty.getFacultyEmail(), style);
			createCell(row, columnCount++, faculty.getFacultyGender(), style);
		}
	}

	public void exportToExcel(HttpServletResponse response, Object data) throws IOException {
		newReportExcel();

		// response writer to excel
		response = initResponseForExportExcel(response, "UserExcel");
		ServletOutputStream outputStream = response.getOutputStream();

		// write sheet, title & header
		String[] headers = new String[] { "Name","Address","Email" ,"Gender"};
		writeTableHeaderExcel("Sheet User", "Report User", headers);

		// write content row
		writeTableData(data);

		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
}
