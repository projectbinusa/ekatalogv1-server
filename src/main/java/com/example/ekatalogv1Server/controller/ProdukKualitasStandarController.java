package com.example.ekatalogv1Server.controller;

import com.example.ekatalogv1Server.dto.ProdukKualitasStandarDTO;
import com.example.ekatalogv1Server.exception.*;
import com.example.ekatalogv1Server.model.ProdukKualitasStandar;
import com.example.ekatalogv1Server.service.admin.ProdukKualitasStandarService;
import com.example.ekatalogv1Server.service.admin.excel.ExcelProdukKualitasStandarAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/kualitas_standar")
@CrossOrigin(origins = "*")
public class ProdukKualitasStandarController {
    private final String uploadDirectory = "uploads/";

    @Autowired
    private ProdukKualitasStandarService produkKualitasStandarService;

    @Autowired
    private ExcelProdukKualitasStandarAllService excelProdukKualitasStandarAllService;

    // Get All data
    @GetMapping
    public CommonResponse<List<ProdukKualitasStandar>> getAll() {
        return ResponseHelper.ok(produkKualitasStandarService.getAll());
    }

    // Get byId data
    @GetMapping("/{id}")
    public CommonResponse<ProdukKualitasStandar> getById(@PathVariable("id") Long id) {
        return ResponseHelper.ok(produkKualitasStandarService.getById(id));
    }

    // Add data
    @PostMapping("/add")
    public CommonResponse<ProdukKualitasStandar> add(@RequestBody ProdukKualitasStandarDTO produkKualitasStandarDTO) {
        return ResponseHelper.ok(produkKualitasStandarService.add(produkKualitasStandarDTO));
    }

    // Updata data
    @PutMapping("/{id}")
    public CommonResponse<ProdukKualitasStandar> put(@PathVariable("id") Long id , @RequestBody ProdukKualitasStandarDTO produkKualitasStandarDTO) {
        return ResponseHelper.ok(produkKualitasStandarService.put(produkKualitasStandarDTO , id));
    }

    // Delete data
    @DeleteMapping("/{id}")
    public CommonResponse<?> delete(@PathVariable("id") Long id) {
        return ResponseHelper.ok(produkKualitasStandarService.delete(id));
    }

    // EndPoint pagination
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

    // EndPoint export All
    @GetMapping("export/produkStandar/All")
    public void exportExcelProdukStandarAll(
            @RequestParam("tglAwal") @DateTimeFormat(pattern = "yyy-MM-dd") Date tglAwal,
            @RequestParam("tglAkhir") @DateTimeFormat(pattern = "yyyy-MM-dd") Date tglAkhir,
            HttpServletResponse response) throws IOException {

        excelProdukKualitasStandarAllService.excelLaporanProdukStandar(tglAwal, tglAkhir, response);
    }

    @PostMapping("upload_image/{id}")
    public CommonResponse<?> uploadImage(@PathVariable("id") Long id, @RequestPart("foto")MultipartFile file) {
        try {
            ProdukKualitasStandar uploadImage = produkKualitasStandarService.uploadImage(id, file);
            return ResponseHelper.ok(uploadImage);
        } catch (NotFoundException e) {
            return ResponseHelper.error("Produk kualitas standar not found", HttpStatus.NOT_FOUND).getBody();
        } catch (IOException e) {
            return ResponseHelper.error("File upload failed", HttpStatus.INTERNAL_SERVER_ERROR).getBody();
        } catch (Exception e) {
            return ResponseHelper.error("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR).getBody();
        }
    }
}
