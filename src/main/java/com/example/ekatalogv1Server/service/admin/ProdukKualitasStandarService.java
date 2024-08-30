package com.example.ekatalogv1Server.service.admin;

import com.example.ekatalogv1Server.dto.ProdukKualitasStandarDTO;
import com.example.ekatalogv1Server.exception.NotFoundException;
import com.example.ekatalogv1Server.model.*;
import com.example.ekatalogv1Server.repository.*;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class ProdukKualitasStandarService {

    static final String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/my-upload-image-9a0d5.appspot.com/o/%s?alt=media";

    @Autowired
    private ProdukKualitasStandarRepository produkKualitasStandarRepository;

    @Autowired
    private KategoriProdukRepository kategoriProdukRepository;

    @Autowired
    private DetailProdukKualitasStandarRepository detailProdukKualitasStandarRepository;

    public List<ProdukKualitasStandar> getAll() {
        return produkKualitasStandarRepository.findAll();
    }

    public ProdukKualitasStandar getById(Long id) {
        return produkKualitasStandarRepository.findById(id).orElseThrow(() -> new NotFoundException("Id tidak ditemukan"));
    }

    public ProdukKualitasStandar add(ProdukKualitasStandarDTO produkKualitasStandarDTO) {
        ProdukKualitasStandar produkKualitasStandar = new ProdukKualitasStandar();
        produkKualitasStandar.setNamaProduk(produkKualitasStandarDTO.getNamaProduk());
        produkKualitasStandar.setCashKredit(produkKualitasStandarDTO.getCashKredit());
        produkKualitasStandar.setStatus(produkKualitasStandarDTO.getStatus());
        produkKualitasStandar.setLayanan(produkKualitasStandarDTO.getLayanan());
        produkKualitasStandar.setTanggal(produkKualitasStandarDTO.getTanggal());
        produkKualitasStandar.setJenisProyek(produkKualitasStandarDTO.getJenisProyek());
        produkKualitasStandar.setDelFlag(1);
        KategoriProduk kategoriProduk = kategoriProdukRepository.findById(produkKualitasStandarDTO.getIdKategoriProduk())
                .orElseThrow(() -> new RuntimeException("Kategori produk not found"));
        produkKualitasStandar.setKategoriProduk(kategoriProduk);
        DetailProdukKualitasStandar detailProdukKualitasStandar = detailProdukKualitasStandarRepository.findById(produkKualitasStandarDTO.getIdDetailProdukStandar())
                .orElseThrow(() -> new RuntimeException("Detail produk not found"));
        produkKualitasStandar.setDetailProdukKualitasStandar(detailProdukKualitasStandar);

        return produkKualitasStandarRepository.save(produkKualitasStandar);
    }

    @Transactional
    public ProdukKualitasStandar put(ProdukKualitasStandarDTO produkKualitasStandarDTO, Long id) {
        ProdukKualitasStandar produkKualitasStandar = produkKualitasStandarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produk Kualitas Standar not found"));
        produkKualitasStandar.setNamaProduk(produkKualitasStandarDTO.getNamaProduk());
        produkKualitasStandar.setCashKredit(produkKualitasStandarDTO.getCashKredit());
        produkKualitasStandar.setStatus(produkKualitasStandarDTO.getStatus());
        produkKualitasStandar.setLayanan(produkKualitasStandarDTO.getLayanan());
        produkKualitasStandar.setJenisProyek(produkKualitasStandarDTO.getJenisProyek());
        produkKualitasStandar.setTanggal(produkKualitasStandarDTO.getTanggal());
        produkKualitasStandar.setDelFlag(1);

        return produkKualitasStandarRepository.save(produkKualitasStandar);
    }

    public String delete(Long id) {
        ProdukKualitasStandar produk = produkKualitasStandarRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Id tidak ditemukan"));

        produkKualitasStandarRepository.delete(produk);
        return "Produk dengan Id " + id + " berhasil dihapus.";
    }

    public Page<ProdukKualitasStandar> getAll(Pageable pageable) {
        return produkKualitasStandarRepository.findAll(pageable);
    }

    public ProdukKualitasStandar uploadImage(Long id, MultipartFile image) throws NotFoundException, IOException {
        ProdukKualitasStandar produkKualitasStandarOptional = produkKualitasStandarRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("id tidak ditemukan"));
        String fileUrl = uploadFoto(image, "image_" + id);
        produkKualitasStandarOptional.setImage(fileUrl);

        return produkKualitasStandarRepository.save(produkKualitasStandarOptional);
    }

    private String uploadFoto(MultipartFile multipartFile, String fileName) throws IOException {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String folderPath = "/";
        String fullPath = folderPath + timestamp + "_" + fileName;
        BlobId blobId = BlobId.of("my-upload-image-9a0d5.appspot.com", fullPath);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("./src/main/resources/serviceAccountKey.json"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, multipartFile.getBytes());
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fullPath, StandardCharsets.UTF_8));
    }
}
