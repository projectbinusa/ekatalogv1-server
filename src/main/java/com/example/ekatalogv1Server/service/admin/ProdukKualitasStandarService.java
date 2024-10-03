package com.example.ekatalogv1Server.service.admin;

import com.example.ekatalogv1Server.dto.ProdukKualitasStandarDTO;
import com.example.ekatalogv1Server.exception.NotFoundException;
import com.example.ekatalogv1Server.model.*;
import com.example.ekatalogv1Server.repository.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Service
public class ProdukKualitasStandarService {

//    static final String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/ekatalogv1-server-774da.appspot.com/o/%s?alt=media";
//    private static final String BASE_URL = "https://s3.lynk2.co/pos/admin";
//    private static final long MAX_FILE_SIZE = 2 * 1024 * 1024;

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

    // fungsi tambah data produk kualitas standar
    public ProdukKualitasStandar add(ProdukKualitasStandarDTO produkKualitasStandarDTO) {
        ProdukKualitasStandar produkKualitasStandar = new ProdukKualitasStandar();
        produkKualitasStandar.setNamaProduk(produkKualitasStandarDTO.getNamaProduk());
        produkKualitasStandar.setCashKredit(produkKualitasStandarDTO.getCashKredit());
        produkKualitasStandar.setStatus(produkKualitasStandarDTO.getStatus());
        produkKualitasStandar.setLayanan(produkKualitasStandarDTO.getLayanan());
        produkKualitasStandar.setTanggal(produkKualitasStandarDTO.getTanggal());
        produkKualitasStandar.setJenisProyek(produkKualitasStandarDTO.getJenisProyek());
        produkKualitasStandar.setDelFlag(1);
        KategoriProduk kategoriProduk = kategoriProdukRepository.findById(produkKualitasStandarDTO.getIdKategoriProduk()).orElseThrow(() -> new RuntimeException("Kategori produk not found"));
        produkKualitasStandar.setKategoriProduk(kategoriProduk);
        DetailProdukKualitasStandar detailProdukKualitasStandar = detailProdukKualitasStandarRepository.findById(produkKualitasStandarDTO.getIdDetailProdukStandar()).orElseThrow(() -> new RuntimeException("Detail produk not found"));
        produkKualitasStandar.setDetailProdukKualitasStandar(detailProdukKualitasStandar);

        return produkKualitasStandarRepository.save(produkKualitasStandar);
    }

    // fungsi edit produk kualitas standar
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

    // fungsi delete produk kualitas standar
    public String delete(Long id) {
        ProdukKualitasStandar produk = produkKualitasStandarRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Id tidak ditemukan"));

        produkKualitasStandarRepository.delete(produk);
        return "Produk dengan Id " + id + " berhasil dihapus.";
    }

    // fungsi getAll data produk kualitas standar
    public Page<ProdukKualitasStandar> getAll(Pageable pageable) {
        return produkKualitasStandarRepository.findAll(pageable);
    }

//    private String uploadFoto(MultipartFile multipartFile) throws IOException {
//        if (multipartFile.getSize() > MAX_FILE_SIZE) {
//            throw new IOException("File sixe exceeds the limit of 2MB");
//        }
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
//        body.add("file", multipartFile.getResource());
//
//        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
//        ResponseEntity<String> response = restTemplate.exchange(BASE_URL, HttpMethod.POST, requestEntity, String.class);
//        String fileUrl = extractFileUrlFromResponse(response.getBody());
//        return fileUrl;
//    }

//    private String extractFileUrlFromResponse(String responseBody) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode jsonResponse = mapper.readTree(responseBody);
//        JsonNode dataNode = jsonResponse.path("data");
//        String urlFile = dataNode.path("url_file").asText();
//
//        return urlFile;
//    }

//    public ProdukKualitasStandar uploadImage(Long id, MultipartFile image) throws NotFoundException, IOException {
//        ProdukKualitasStandar produkKualitasStandarOptional = produkKualitasStandarRepository.findById(id)
//                .orElseThrow(() -> new NotFoundException("id tidak ditemukan"));
//        String fileUrl = uploadFoto(image, "image_" + id);
//        produkKualitasStandarOptional.setImage(fileUrl);
//
//        return produkKualitasStandarRepository.save(produkKualitasStandarOptional);
//    }
//    private String uploadFoto(MultipartFile multipartFile, String fileName) throws IOException {
//        String timestamp = String.valueOf(System.currentTimeMillis());
//        String folderPath = "KStandar/";
//        String fullPath = folderPath + timestamp + "_" + fileName;
//        BlobId blobId = BlobId.of("ekatalogv1-server-774da.appspot.com", fullPath);
//        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(multipartFile.getContentType()).build();
//        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("./src/main/resources/firebaseEkatalog.json"));
//        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
//        storage.create(blobInfo, multipartFile.getBytes());
//        return String.format(DOWNLOAD_URL, URLEncoder.encode(fullPath, StandardCharsets.UTF_8));
//    }

}
