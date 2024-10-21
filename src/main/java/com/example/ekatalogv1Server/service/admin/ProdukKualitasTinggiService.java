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
    private final String uploadDirectory = "uploads/";

    @Autowired
    private ProdukKualitasTinggiRepository produkKualitasTinggiRepository;

    @Autowired
    private KategoriProdukRepository kategoriProdukRepository;

    @Autowired
    private DetailProdukKualitasTinggiRepository detailProdukKualitasTinggiRepository;

    static final String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/ekatalogv1-3a13b.appspot.com/o/%s?alt=media";

    public List<ProdukKualitasTinggi> getAll() {
        return produkKualitasTinggiRepository.findAll();
    }

    public ProdukKualitasTinggi getById(Long id) {
        return produkKualitasTinggiRepository.findById(id).orElseThrow(() -> new NotFoundException("Id tidak ditemukan"));
    }

    // fungsi tambah data produk kualitas tinggi
    public ProdukKualitasTinggi add(ProdukKualitasTinggiDTO produkKualitasTinggiDTO) {
        ProdukKualitasTinggi produkKualitasTinggi = new ProdukKualitasTinggi();
        produkKualitasTinggi.setNamaProduk(produkKualitasTinggiDTO.getNamaProduk());
        produkKualitasTinggi.setCashKredit(produkKualitasTinggiDTO.getCashKredit());
        produkKualitasTinggi.setStatus(produkKualitasTinggiDTO.getStatus());
        produkKualitasTinggi.setLayanan(produkKualitasTinggiDTO.getLayanan());
        produkKualitasTinggi.setJenisProyek(produkKualitasTinggiDTO.getJenisProyek());
        produkKualitasTinggi.setTanggal(produkKualitasTinggiDTO.getTanggal());
        produkKualitasTinggi.setDelFlag(1);;
        KategoriProduk kategoriProduk = kategoriProdukRepository.findById(produkKualitasTinggiDTO.getIdKategoriProduk()).orElseThrow(() -> new RuntimeException("Kategori Produk not found"));
        produkKualitasTinggi.setKategoriProduk(kategoriProduk);
        DetailProdukKualitasTinggi detailProdukKualitasTinggi = detailProdukKualitasTinggiRepository.findById(produkKualitasTinggiDTO.getIdDetailProdukTinggi()).orElseThrow(() -> new RuntimeException("Detail produk not found"));
        produkKualitasTinggi.setDetailProdukKualitasTinggi(detailProdukKualitasTinggi);

        return produkKualitasTinggiRepository.save(produkKualitasTinggi);
    }

    // fungsi edit produk kualitas tinggi
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

    // fungsi delete produk kualitas tingi
    public String delete(Long id) {
        ProdukKualitasTinggi produk = produkKualitasTinggiRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Id tidak ditemukan"));

        produkKualitasTinggiRepository.delete(produk);
        return "Produk dengan Id " + id + " berhasil dihapus.";
    }

    // fungsi getAll data produk kualitas tinggi
    public Page<ProdukKualitasTinggi> getAll(Pageable pageable) {
        return produkKualitasTinggiRepository.findAll(pageable);
    }

    public ProdukKualitasTinggi uploadImage(Long id , MultipartFile file) throws NotFoundException, IOException {
        ProdukKualitasTinggi KtinggiOptional = produkKualitasTinggiRepository.findById(id).orElseThrow(() -> new RuntimeException("Id tidak ditemukan"));
        String fileUrl = uploadFoto(file, "Ktinggi_" + id);
        KtinggiOptional.setFoto(fileUrl);

        return produkKualitasTinggiRepository.save(KtinggiOptional);
    }

    private String uploadFoto(MultipartFile multipartFile, String fileName) throws IOException {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String folderPath = "Ktinggi/";
        String fullPath = folderPath + timestamp + "_" + fileName;
        BlobId blobId = BlobId.of("ekatalogv1-3a13b.appspot.com", fullPath);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(multipartFile.getContentType()).build();
        InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("firebaseAccount.json");
        if (serviceAccount == null) {
            throw new FileNotFoundException("Firebase service account file not found");
        }
        Credentials credentials = GoogleCredentials.fromStream(serviceAccount);
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        System.out.println("Menyimpan gambar ke Firebase dengan path: " + fullPath);
        storage.create(blobInfo, multipartFile.getBytes());
        System.out.println("Upload sukses!");
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fullPath, StandardCharsets.UTF_8));
    }
}
