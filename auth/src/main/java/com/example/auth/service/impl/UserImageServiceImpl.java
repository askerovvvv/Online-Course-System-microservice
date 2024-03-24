package com.example.auth.service.impl;

import com.example.auth.models.entity.UserImage;
import com.example.auth.repository.UserImageRepository;
import com.example.auth.service.UserImageService;
import com.example.auth.utils.ImageUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserImageServiceImpl implements UserImageService {

    private final UserImageRepository userImageRepository;

    @Override
    public UserImage uploadImage(MultipartFile file){
        try {
            return userImageRepository.save(UserImage.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .image(ImageUtil.compressImage(file.getBytes())).build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Transactional
    public UserImage getInfoByImageByName(String name) {
        Optional<UserImage> dbImage = userImageRepository.findByName(name);

        return UserImage.builder()
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .image(ImageUtil.decompressImage(dbImage.get().getImage())).build();

    }

    @Transactional
    public byte[] getImage(String name) {
        Optional<UserImage> dbImage = userImageRepository.findByName(name);
        byte[] image = ImageUtil.decompressImage(dbImage.get().getImage());
        return image;
    }


}
