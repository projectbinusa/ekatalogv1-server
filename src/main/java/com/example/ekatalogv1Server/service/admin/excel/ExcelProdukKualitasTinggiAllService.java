package com.example.ekatalogv1Server.service.admin.excel;

import com.example.ekatalogv1Server.model.ProdukKualitasTinggi;
import com.example.ekatalogv1Server.repository.ProdukKualitasTinggiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Service
public class ExcelProdukKualitasTinggiAllService {

    @Autowired
    private ProdukKualitasTinggiRepository produkKualitasTinggiRepository;

    @Autowired
    private ExcelProdukKualitasTinggiAll excelProdukKualitasTinggiAll;

    public ByteArrayInputStream loadProdukTinggi() throws IOException {
        List<ProdukKualitasTinggi> produkKualitasTinggis = produkKualitasTinggiRepository.findAll();
        return excelProdukKualitasTinggiAll.laporanProdukTinggiToExcel(produkKualitasTinggis);
    }

    public void excelLaporanProdukTinggi(Date tglAwal, Date tglAkhir, HttpServletResponse response) throws IOException {
        ByteArrayInputStream bais = loadProdukTinggi();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=LAPORAN PRODUK KUALITAS TINGGI ALL.xlsx");
        response.getOutputStream().write(bais.readAllBytes());
    }
}
