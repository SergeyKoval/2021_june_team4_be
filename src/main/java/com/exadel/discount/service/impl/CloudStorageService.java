package com.exadel.discount.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Setter
@RequiredArgsConstructor
@Slf4j
public class CloudStorageService {
    private final AmazonS3 s3Client;

    @Value("${cloud.aws.bucket[0]}")
    private String bucketName;

    public List<String> uploadImagesAndGetURLs(List<MultipartFile> images) {
        List<String> imageCloudPaths = new ArrayList<>();
        try {
            images.forEach(image -> {
                File file = convertMultipartFileTofFile(image);
                String fileName = image.getOriginalFilename();
                s3Client.putObject(new PutObjectRequest(bucketName, fileName, file));
                file.delete();
                imageCloudPaths.add(getFileURL(fileName));
            });
            return imageCloudPaths;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    private String getFileURL(String fileName) {
        URL s3Object = s3Client.getUrl(bucketName, fileName);
        return s3Object.toString();
    }

    private File convertMultipartFileTofFile(MultipartFile multipartFile) {
        File file = new File(extractFileName(multipartFile));

        try (FileOutputStream out = new FileOutputStream(file)) {
            out.write(multipartFile.getBytes());
        } catch (NullPointerException | IOException exception) {
            log.error("Exception stack trace: ", exception);
        } catch (Exception exception) {
            log.error("Exception stack trace: ", exception);
        }
        return file;
    }

    private String extractFileName(MultipartFile file) throws NullPointerException {
        String filename = file.getOriginalFilename();
        Objects.requireNonNull(filename, "filename must not be null");
        return filename;
    }
}
