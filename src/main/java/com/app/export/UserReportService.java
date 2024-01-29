package com.app.export;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Faculty;
import com.app.entity.User;
import com.app.repo.FacultyRepo;

import jakarta.servlet.http.HttpServletResponse;


@Service
public class UserReportService {

    @Autowired
    UserExportToPdfService userExportToPdfService;

    @Autowired
    UserExportToExcelService userExportToExcelService;

    @Autowired
    FacultyRepo facultyRepo;
    


    public void exportToPdf(HttpServletResponse response) throws IOException {
        // get all user
        List<Faculty> data = facultyRepo.findAll();

        // export to pdf
        userExportToPdfService.exportToPDF(response, data);
    }


    public void exportToExcel(HttpServletResponse response) throws IOException {
        // get all user
    	List<Faculty> data = facultyRepo.findAll();

        // export to excel
        userExportToExcelService.exportToExcel(response, data);

    }


}