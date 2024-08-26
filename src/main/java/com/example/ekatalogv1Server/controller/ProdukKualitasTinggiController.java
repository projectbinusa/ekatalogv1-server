package com.example.ekatalogv1Server.controller;

import com.example.ekatalogv1Server.dto.ProdukKualitasTinggiDTO;
import com.example.ekatalogv1Server.exception.CommonResponse;
import com.example.ekatalogv1Server.exception.PaginationResponse;
import com.example.ekatalogv1Server.exception.ResponseHelper;
import com.example.ekatalogv1Server.model.ProdukKualitasTinggi;
import com.example.ekatalogv1Server.service.admin.ProdukKualitasTinggiService;
import com.example.ekatalogv1Server.service.admin.excel.ExcelProdukKualitasTinggiAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/kualitas_tinggi")
@CrossOrigin(origins = "*")
public class ProdukKualitasTinggiController {

    @Autowired
    private ProdukKualitasTinggiService produkKualitasTinggiService;

    @Autowired
    private ExcelProdukKualitasTinggiAllService excelProdukKualitasTinggiAllService;

    @GetMapping
    public CommonResponse<List<ProdukKualitasTinggi>> getAll() {
        return ResponseHelper.ok(produkKualitasTinggiService.getAll());
    }

    @GetMapping("/{id}")
    public CommonResponse<ProdukKualitasTinggi> getById(@PathVariable("id") Long id) {
        return ResponseHelper.ok(produkKualitasTinggiService.getById(id));
    }

    @PostMapping("/add")
    public CommonResponse<ProdukKualitasTinggi> add(@RequestBody ProdukKualitasTinggiDTO produkKualitasTinggiDTO) {
        return ResponseHelper.ok(produkKualitasTinggiService.add(produkKualitasTinggiDTO));
    }

    @PutMapping("/{id}")
    public CommonResponse<ProdukKualitasTinggi> put(@PathVariable("id") Long id , @RequestBody ProdukKualitasTinggiDTO produkKualitasTinggiDTO) {
        return ResponseHelper.ok(produkKualitasTinggiService.put(produkKualitasTinggiDTO , id));
    }

    @DeleteMapping("/{id}")
    public CommonResponse<?> delete(@PathVariable("id") Long id) {
        return ResponseHelper.ok(produkKualitasTinggiService.delete(id));
    }

    @GetMapping(path = "/pagination")
    public ResponseEntity<PaginationResponse<Page<ProdukKualitasTinggi>>> getAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ProdukKualitasTinggi> produkKualitasTinggis = produkKualitasTinggiService.getAll(pageable);

        PaginationResponse<Page<ProdukKualitasTinggi>> response = new PaginationResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setData(produkKualitasTinggis);
        response.setMessage("Data fetched successfully");
        response.setPagination(Map.of(
                "totalElements", produkKualitasTinggis.getTotalElements(),
                "totalPages", produkKualitasTinggis.getTotalPages(),
                "currentPage", produkKualitasTinggis.getNumber(),
                "pageSize", produkKualitasTinggis.getSize()
        ));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("export/produkTinggi/All")
    public void exportExcelProdukTinggiAll(
            @RequestParam("tglAwal") @DateTimeFormat(pattern = "yyy-MM-dd") Date tglAwal,
            @RequestParam("tglAkhir") @DateTimeFormat(pattern = "yyyy-MM-dd") Date tglAkhir,
            HttpServletResponse response) throws IOException {

        excelProdukKualitasTinggiAllService.excelLaporanProdukTinggi(tglAwal, tglAkhir, response);
    }
}
