package com.Lending.ScenarySports.Controller;

import com.Lending.ScenarySports.Services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDate;

public class ReportController {
    @Autowired
    private ReportService userExcelReportService;

    @GetMapping(value = "/report", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> generateExcelReport(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                      @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) throws IOException {
        String userId = "usuario1"; // En este ejemplo, supongamos que solo estamos generando informes para un usuario específico
        // byte[] reportBytes = userExcelReportService.generateExcelReportForUser(userId, startDate, endDate).toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=informe.xlsx");
        // return new ResponseEntity<>(reportBytes, headers, HttpStatus.OK);
        return null;
    }

}

