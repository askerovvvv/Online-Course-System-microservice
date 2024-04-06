package com.example.auth.service.impl;

import com.example.auth.exceptions.NotFoundException;
import com.example.auth.models.entity.UserImage;
import com.example.auth.repository.UserImageRepository;
import com.example.auth.service.UserImageService;
import com.example.auth.utils.ImageUtil;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserImageServiceImpl implements UserImageService {

    private final UserImageRepository userImageRepository;
    private final String FOLDER_PATH = "C:/Users/User/Desktop/photosFromSpring/";

    @Override
    public UserImage uploadImage(MultipartFile image, String userEmail){
        try {
            String a = image.getOriginalFilename().substring(0, image.getOriginalFilename().length()-4);
            if (!userEmail.equals(a)) {
                throw new BadRequestException("The image name must be the user's email");
            }

            String filePath = FOLDER_PATH + image.getOriginalFilename();
            UserImage userImage = UserImage.builder()
                    .name(image.getOriginalFilename())
                    .type(image.getContentType())
                    .filePath(filePath)
                    .build();
            image.transferTo(new File(filePath));

            return userImageRepository.save(userImage);
        } catch (IOException e) {
            throw new BadRequestException("An error occurred please contact support!");
        }

    }

    @Override
    public byte[] downloadImage(String filename) {
        try {
            Optional<UserImage> userImage = userImageRepository.findByName(filename);
            if (userImage.isEmpty()) {
                throw new NotFoundException("Image not found!");
            }
            String filePath = userImage.get().getFilePath();

            return Files.readAllBytes(new File(filePath).toPath());
        } catch (IOException e) {
            throw new BadRequestException("An error occurred please contact support!");
        }

    }



}
