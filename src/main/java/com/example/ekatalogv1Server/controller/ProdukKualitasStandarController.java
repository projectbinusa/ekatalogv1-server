package com.example.ekatalogv1Server.controller;

import com.example.ekatalogv1Server.dto.ProdukKualitasStandarDTO;
import com.example.ekatalogv1Server.exception.CommonResponse;
import com.example.ekatalogv1Server.exception.PaginationResponse;
import com.example.ekatalogv1Server.exception.ResponseHelper;
import com.example.ekatalogv1Server.model.ProdukKualitasStandar;
import com.example.ekatalogv1Server.service.admin.ProdukKualitasStandarService;
import com.example.ekatalogv1Server.service.admin.excel.ExcelProdukKualitasStandarAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/kualitas_standar")
@CrossOrigin(origins = "*")
public class ProdukKualitasStandarController {

    @Autowired
    private ProdukKualitasStandarService produkKualitasStandarService;

    @Autowired
    private ExcelProdukKualitasStandarAllService excelProdukKualitasStandarAllService;

    @GetMapping
    public CommonResponse<List<ProdukKualitasStandar>> getAll() {
        return ResponseHelper.ok(produkKualitasStandarService.getAll());
    }

    @GetMapping("/{id}")
    public CommonResponse<ProdukKualitasStandar> getById(@PathVariable("id") Long id) {
        return ResponseHelper.ok(produkKualitasStandarService.getById(id));
    }

    @PostMapping("/add")
    public CommonResponse<ProdukKualitasStandar> add(@RequestBody ProdukKualitasStandarDTO produkKualitasStandarDTO) {
        return ResponseHelper.ok(produkKualitasStandarService.add(produkKualitasStandarDTO));
    }

    @PutMapping("/{id}")
    public CommonResponse<ProdukKualitasStandar> put(@PathVariable("id") Long id , @RequestBody ProdukKualitasStandarDTO produkKualitasStandarDTO) {
        return ResponseHelper.ok(produkKualitasStandarService.put(produkKualitasStandarDTO , id));
    }

    @DeleteMapping("/{id}")
    public CommonResponse<?> delete(@PathVariable("id") Long id) {
        return ResponseHelper.ok(produkKualitasStandarService.delete(id));
    }

    @GetMapping(path = "/pagination")
    public ResponseEntity<PaginationResponse<Page<ProdukKualitasStandar>>> getAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ProdukKualitasStandar> produkKualitasStandars = produkKualitasStandarService.getAll(pageable);

        PaginationResponse<Page<ProdukKualitasStandar>> response = new PaginationResponse<>();
        response.setStatus("success");
        response.setCode(HttpStatus.OK.value());
        response.setData(produkKualitasStandars);
        response.setMessage("Data fetched successfully");
        response.setPagination(Map.of(
                "totalElements", produkKualitasStandars.getTotalElements(),
                "totalPages", produkKualitasStandars.getTotalPages(),
                "currentPage", produkKualitasStandars.getNumber(),
                "pageSize", produkKualitasStandars.getSize()
        ));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("export/produkStandar/All")
    public void exportExcelProdukStandarAll(
            @RequestParam("tglAwal") @DateTimeFormat(pattern = "yyy-MM-dd") Date tglAwal,
            @RequestParam("tglAkhir") @DateTimeFormat(pattern = "yyyy-MM-dd") Date tglAkhir,
            HttpServletResponse response) throws IOException {

        excelProdukKualitasStandarAllService.excelLaporanProdukStandar(tglAwal, tglAkhir, response);
    }
}
