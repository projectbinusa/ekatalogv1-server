package com.example.ekatalogv1Server.service.admin.excel;

import com.example.ekatalogv1Server.model.ProdukKualitasTinggi;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class ExcelProdukKualitasTinggiAll {
    public static final String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERsLAPORANPRODUKTINGGI = {"NO", "NAMA PRODUK", "KATEGORI PRODUK", "JENIS PROYEK", "LAYANAN", "STATUS", "TANGGAL", "DESKRIPSI PRODUK"};
    private static final String SHEET_NAME = "LAPORAN PRODUK KUALITAS TINGGI ALL";

    private static final String SHEET = "sheet1";

    public ByteArrayInputStream laporanProdukTinggiToExcel(List<ProdukKualitasTinggi> produkKualitasTinggis) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet(SHEET);

            // Header Style
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 10);
            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < HEADERsLAPORANPRODUKTINGGI.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERsLAPORANPRODUKTINGGI[col]);
                cell.setCellStyle(headerStyle);
                sheet.autoSizeColumn(col);
            }

            // Data Style
            CellStyle dataStyle = workbook.createCellStyle();
            dataStyle.setBorderBottom(BorderStyle.THIN);
            dataStyle.setBorderTop(BorderStyle.THIN);
            dataStyle.setBorderLeft(BorderStyle.THIN);
            dataStyle.setBorderRight(BorderStyle.THIN);
            dataStyle.setAlignment(HorizontalAlignment.LEFT);
            dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            // Date Style
            CellStyle dateStyle = workbook.createCellStyle();
            dateStyle.cloneStyleFrom(dataStyle);
            CreationHelper createHelper = workbook.getCreationHelper();
            dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));

            int rowIdx = 1;
            int no = 1;

            for (ProdukKualitasTinggi produkKualitasTinggi : produkKualitasTinggis) {
                Row row = sheet.createRow(rowIdx++);
                Cell cellNo = row.createCell(0);
                cellNo.setCellValue(no);
                cellNo.setCellStyle(dataStyle);

                Cell cellNamaProduk = row.createCell(1);
                cellNamaProduk.setCellValue(produkKualitasTinggi.getNamaProduk());
                cellNamaProduk.setCellStyle(dataStyle);

                String kategoriProduk = produkKualitasTinggi.getKategoriProduk() != null ? produkKualitasTinggi.getKategoriProduk().getNamaKategori() : "";
                Cell cellNamaKategori = row.createCell(2);
                cellNamaKategori.setCellValue(kategoriProduk);
                cellNamaKategori.setCellStyle(dataStyle);

                Cell cellJenisProyek = row.createCell(3);
                cellJenisProyek.setCellValue(produkKualitasTinggi.getJenisProyek());
                cellJenisProyek.setCellStyle(dataStyle);

                Cell cellLayanan = row.createCell(4);
                cellLayanan.setCellValue(produkKualitasTinggi.getLayanan());
                cellLayanan.setCellStyle(dataStyle);

                Cell cellStatus = row.createCell(5);
                cellStatus.setCellValue(produkKualitasTinggi.getStatus());
                cellStatus.setCellStyle(dataStyle);

                Cell cellTgl = row.createCell(6);
                cellTgl.setCellValue(produkKualitasTinggi.getTanggal());
                cellTgl.setCellStyle(dateStyle);

                String deskripsiProduk = produkKualitasTinggi.getDetailProdukKualitasTinggi() != null ? produkKualitasTinggi.getDetailProdukKualitasTinggi().getDeskripsi() : "";
                Cell cellDeskripsi = row.createCell(7);
                cellDeskripsi.setCellValue(deskripsiProduk);
                cellDeskripsi.setCellStyle(dataStyle);

                no++;
            }

            for (int col = 0; col < HEADERsLAPORANPRODUKTINGGI.length; col++) {
                sheet.autoSizeColumn(col);
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to excel file: " + e.getMessage());
        }
    }
}
