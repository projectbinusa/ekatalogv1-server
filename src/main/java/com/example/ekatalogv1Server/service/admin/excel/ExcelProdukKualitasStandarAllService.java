package com.example.ekatalogv1Server.service.admin.excel;

import com.example.ekatalogv1Server.model.ProdukKualitasStandar;
import com.example.ekatalogv1Server.repository.ProdukKualitasStandarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Service
public class ExcelProdukKualitasStandarAllService {

    @Autowired
    private ProdukKualitasStandarRepository produkKualitasStandarRepository;

    @Autowired
    private ExcelProdukKualitasStandarAll excelProdukKualitasStandarAll;

    public ByteArrayInputStream loadProdukStandar() throws IOException {
        List<ProdukKualitasStandar> produkKualitasStandars = produkKualitasStandarRepository.findAll();
        return excelProdukKualitasStandarAll.laporanProdukStandarToExcel(produkKualitasStandars);
    }

    public void excelLaporanProdukStandar(Date tglAwal, Date tglAkhir, HttpServletResponse response) throws IOException {
        ByteArrayInputStream bais = loadProdukStandar();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=LAPORAN PRODUK KUALITAS STANDAR ALL.xlsx");
        response.getOutputStream().write(bais.readAllBytes());
    }
}






