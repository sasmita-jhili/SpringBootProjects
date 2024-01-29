package com.app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.export.UserReportService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletResponse;;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "Bearer Authentication")
public class UserReportController {

    @Autowired
    UserReportService userReportService;

    @GetMapping("/pdf/all")
    public void exportToPdf(HttpServletResponse response) throws IOException {
        this.userReportService.exportToPdf(response);
    }


    @GetMapping("/excel/all")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        this.userReportService.exportToExcel(response);
    }
}