package com.example.auth.service;

import com.example.auth.models.entity.UserImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserImageService {
    UserImage uploadImage(MultipartFile image, String userEmail);
    byte[] downloadImage(String filename);
}
