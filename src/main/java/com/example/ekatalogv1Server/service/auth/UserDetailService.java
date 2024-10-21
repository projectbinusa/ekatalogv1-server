
package com.example.ekatalogv1Server.service.auth;

import com.example.ekatalogv1Server.dto.*;
import com.example.ekatalogv1Server.exception.NotFoundException;
import com.example.ekatalogv1Server.model.Pengguna;
import com.example.ekatalogv1Server.repository.PenggunaRepository;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private PenggunaRepository userDao;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private PasswordEncoder encoder;

    static final String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/ekatalogv1-3a13b.appspot.com/o/%s?alt=media";

    // login atau authentication pengguna
    public Pengguna save(PenggunaDTO user) {
        try {
            System.out.println("Checking username: " + user.getUsernamePengguna());
            if (user.getUsernamePengguna() != null && user.getPasswordPengguna() != null && user.getPasswordPengguna().length() >= 8) {
                Optional<Pengguna> existingUser = userDao.findByUsername(user.getUsernamePengguna());
                System.out.println("Existing user: " + existingUser.isPresent());
                if (existingUser.isEmpty()) {
                    Pengguna newUser = new Pengguna();
                    newUser.setUsernamePengguna(user.getUsernamePengguna());
                    newUser.setPasswordPengguna(bcryptEncoder.encode(user.getPasswordPengguna()));
                    newUser.setRolePengguna(user.getRolePengguna());
                    newUser.setNamaPengguna(user.getNamaPengguna());
                    newUser.setTanggal(new Date());
                    newUser.setDelFlag(user.getDelFlag());
                    return userDao.save(newUser);
                } else {
                    throw new IllegalArgumentException("username has been used");
                }
            } else {
                throw new IllegalArgumentException("Password must be more than 8 characters");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Registration Failed: " + e.getMessage());
        }
    }


    @Override
    public UserDetails loadUserByUsername(String usernamePengguna) throws UsernameNotFoundException {
        Optional<Pengguna> optionalUser = userDao.findByUsername(usernamePengguna);
        if (optionalUser.isPresent()) {
            Pengguna user = optionalUser.get();
            List<SimpleGrantedAuthority> roles = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRolePengguna().toUpperCase()));
            return new User(usernamePengguna, user.getPasswordPengguna(), roles);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + usernamePengguna);
        }
    }

    // ubah pengguna
    public Pengguna put(PenggunaUbahDTO penggunaUbahDTO, Long id) {
        Pengguna pengguna = userDao.findById(id).orElseThrow(() -> new RuntimeException("pengguna not found"));
        pengguna.setNamaPengguna(penggunaUbahDTO.getNamaPengguna());
        pengguna.setUsernamePengguna(penggunaUbahDTO.getUsernamePengguna());

        return userDao.save(pengguna);
    }

    // delete pengguna
    public boolean delete(Long id) {
        if (userDao.existsById(id)) {
            userDao.deleteById(id);
            return true;
        } else {
            throw new RuntimeException("Pungguna dengan Id " + id + " not found");
        }
    }

    public List<Pengguna> getAll() {
        return userDao.findAll();
    }

    // getById pengguna
    public Pengguna getById(Long id) {
        return userDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Pengguna dengan ID " + id + " tidak ditemukan"));
    }

    public String updatePassword(Long id, PasswordChangeRequestDTO passwordChangeRequestDTO) {
        Pengguna pengguna = userDao.findById(id).orElseThrow(() -> new RuntimeException("Penggguna tidak ditemukan"));

        if (!bcryptEncoder.matches(passwordChangeRequestDTO.getPasswordLama(), pengguna.getPasswordPengguna())) {
            return "Password lama tidak sesuai";
        }
        if (!passwordChangeRequestDTO.getPasswordBaru().equals(passwordChangeRequestDTO.getKonfirmasiPassword())) {
            return "Konfirmasi password tidak cocok";
        }
        pengguna.setPasswordPengguna(bcryptEncoder.encode(passwordChangeRequestDTO.getPasswordBaru()));
        userDao.save(pengguna);

        return "Password berhasil diubah";
    }

    public Pengguna uploadImage(Long id , MultipartFile file) throws NotFoundException, IOException {
        Pengguna penggunaOptional = userDao.findById(id).orElseThrow(() -> new RuntimeException("Id tidak ditemukan"));
        String fileUrl = uploadFoto(file , "Pengguna_" + id);
        penggunaOptional.setFoto(fileUrl);

        return userDao.save(penggunaOptional);
    }

    private String uploadFoto(MultipartFile multipartFile, String fileName) throws IOException {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String folderPath = "pengguna/";
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