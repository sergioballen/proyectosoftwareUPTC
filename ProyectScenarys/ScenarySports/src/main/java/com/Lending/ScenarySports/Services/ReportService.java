package com.Lending.ScenarySports.Services;

import com.Lending.ScenarySports.Entity.Booking;
import com.sun.source.tree.LambdaExpressionTree;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.filters.ExpiresFilter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ReportService {

    private XSSFWorkbook book;
    private XSSFSheet sheet;
    private List<Booking> listBooking;

    public ReportService(List<Booking> listBooking){
        this.listBooking=listBooking;
        book=new XSSFWorkbook();
        sheet=book.createSheet("Bookings");
    }
    private void heathTable(){
        Row row =sheet.createRow(0);
        CellStyle style= book.createCellStyle();
        XSSFFont font=book.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        Cell cell=row.createCell(0);
        cell.setCellValue("ID");
        cell.setCellStyle(style);

        cell=row.createCell(1);
        cell.setCellValue("Fecha");
        cell.setCellStyle(style);

        cell=row.createCell(3);
        cell.setCellValue("Hora");
        cell.setCellStyle(style);

        cell=row.createCell(4);
        cell.setCellValue("Usuario");
        cell.setCellStyle(style);

        cell=row.createCell(5);
        cell.setCellValue("Escenario");
        cell.setCellStyle(style);
    }
    private void WriteDateTable(){
        int namberRow=1;

        CellStyle style= book.createCellStyle();
        XSSFFont font=book.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Booking booking: listBooking){
            Row row =sheet.createRow(namberRow++);

            Cell cell =row.createCell(0);
            cell.setCellValue(booking.getId());
            sheet.autoSizeColumn(0);
            cell.setCellStyle(style);

            cell =row.createCell(1);
            cell.setCellValue(booking.getDate().toString());
            sheet.autoSizeColumn(1);
            cell.setCellStyle(style);

            cell =row.createCell(2);
            cell.setCellValue(booking.getHora().toString());
            sheet.autoSizeColumn(2);
            cell.setCellStyle(style);

            cell =row.createCell(3);
            cell.setCellValue(booking.getUser().toString());
            sheet.autoSizeColumn(3);
            cell.setCellStyle(style);

            cell =row.createCell(4);
            cell.setCellValue(booking.getScenary().toString());
            sheet.autoSizeColumn(4);
            cell.setCellStyle(style);
        }
    }
    public void Export(HttpServletResponse response) throws IOException{
        heathTable();
        WriteDateTable();
        ServletOutputStream outputStream= response.getOutputStream();
        book.write(outputStream);
        book.close();
        outputStream.close();
    }
}
