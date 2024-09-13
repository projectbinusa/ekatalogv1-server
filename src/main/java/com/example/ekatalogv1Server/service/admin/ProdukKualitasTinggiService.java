package com.example.ekatalogv1Server.service.admin;

import com.example.ekatalogv1Server.dto.ProdukKualitasTinggiDTO;
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
public class ProdukKualitasTinggiService {

    static final String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/e-katalogv1.appspot.com/o/%s?alt=media";

    @Autowired
    private ProdukKualitasTinggiRepository produkKualitasTinggiRepository;

    @Autowired
    private KategoriProdukRepository kategoriProdukRepository;

    @Autowired
    private DetailProdukKualitasTinggiRepository detailProdukKualitasTinggiRepository;

    public List<ProdukKualitasTinggi> getAll() {
        return produkKualitasTinggiRepository.findAll();
    }

    public ProdukKualitasTinggi getById(Long id) {
        return produkKualitasTinggiRepository.findById(id).orElseThrow(() -> new NotFoundException("Id tidak ditemukan"));
    }

    public ProdukKualitasTinggi add(ProdukKualitasTinggiDTO produkKualitasTinggiDTO) {
        ProdukKualitasTinggi produkKualitasTinggi = new ProdukKualitasTinggi();
        produkKualitasTinggi.setNamaProduk(produkKualitasTinggiDTO.getNamaProduk());
        produkKualitasTinggi.setCashKredit(produkKualitasTinggiDTO.getCashKredit());
        produkKualitasTinggi.setStatus(produkKualitasTinggiDTO.getStatus());
        produkKualitasTinggi.setLayanan(produkKualitasTinggiDTO.getLayanan());
        produkKualitasTinggi.setJenisProyek(produkKualitasTinggiDTO.getJenisProyek());
        produkKualitasTinggi.setTanggal(produkKualitasTinggiDTO.getTanggal());
        produkKualitasTinggi.setDelFlag(1);
        KategoriProduk kategoriProduk = kategoriProdukRepository.findById(produkKualitasTinggiDTO.getIdKategoriProduk())
                .orElseThrow(() -> new RuntimeException("Kategori Produk not found"));
        produkKualitasTinggi.setKategoriProduk(kategoriProduk);
        DetailProdukKualitasTinggi detailProdukKualitasTinggi = detailProdukKualitasTinggiRepository.findById(produkKualitasTinggiDTO.getIdDetailProdukTinggi())
                .orElseThrow(() -> new RuntimeException("Detail produk not found"));
        produkKualitasTinggi.setDetailProdukKualitasTinggi(detailProdukKualitasTinggi);

        return produkKualitasTinggiRepository.save(produkKualitasTinggi);
    }

    @Transactional
    public ProdukKualitasTinggi put(ProdukKualitasTinggiDTO produkKualitasTinggiDTO, Long id) {
        ProdukKualitasTinggi produkKualitasTinggi = produkKualitasTinggiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produk Kualitas Tinggi not found"));
        produkKualitasTinggi.setNamaProduk(produkKualitasTinggiDTO.getNamaProduk());
        produkKualitasTinggi.setCashKredit(produkKualitasTinggiDTO.getCashKredit());
        produkKualitasTinggi.setStatus(produkKualitasTinggiDTO.getStatus());
        produkKualitasTinggi.setLayanan(produkKualitasTinggiDTO.getLayanan());
        produkKualitasTinggi.setJenisProyek(produkKualitasTinggiDTO.getJenisProyek());
        produkKualitasTinggi.setTanggal(produkKualitasTinggiDTO.getTanggal());
        produkKualitasTinggi.setDelFlag(1);

        return produkKualitasTinggiRepository.save(produkKualitasTinggi);
    }

    public String delete(Long id) {
        ProdukKualitasTinggi produk = produkKualitasTinggiRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Id tidak ditemukan"));

        produkKualitasTinggiRepository.delete(produk);
        return "Produk dengan Id " + id + " berhasil dihapus.";
    }

    public Page<ProdukKualitasTinggi> getAll(Pageable pageable) {
        return produkKualitasTinggiRepository.findAll(pageable);
    }

    public ProdukKualitasTinggi uploadImage(Long id, MultipartFile image) throws NotFoundException, IOException {
        ProdukKualitasTinggi produkKualitasTinggiOptional = produkKualitasTinggiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("id tidak ditemukan"));
        String fileUrl = uploadFoto(image, "image_" + id);
        produkKualitasTinggiOptional.setImage(fileUrl);

        return produkKualitasTinggiRepository.save(produkKualitasTinggiOptional);
    }

    private String uploadFoto(MultipartFile multipartFile, String fileName) throws IOException {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String folderPath = "admin1/";
        String fullPath = folderPath + timestamp + "_" + fileName;
        BlobId blobId = BlobId.of("e-katalogv1.appspot.com", fullPath);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(multipartFile.getContentType()).build();
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("./src/main/resources/firebaseAccountKey.json"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, multipartFile.getBytes());
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fullPath, StandardCharsets.UTF_8));
    }
}
