package com.personalfolio.voyage.store;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.personalfolio.voyage.multiFormat.ImageContentDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service @Slf4j
public class AWSS3Respository {

    @Value("${aws.s3.bucket}")
    private  String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    public String uploadContent(MultipartFile content,String fileName) {
        try{
            String contentName = fileName;
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(content.getContentType());
            metadata.setContentLength(content.getSize());
            s3Client.putObject(bucketName, contentName, content.getInputStream(), metadata);
            return s3Client.getUrl(bucketName, contentName).toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file to S3", e);
        }
    }
    public byte[] getContent(String contentName) {
        try {
            S3Object s3Object = s3Client.getObject(bucketName, contentName);
            S3ObjectInputStream inputStream = s3Object.getObjectContent();
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to download file from S3", e);
        }
    }

    public ArrayList<ImageContentDTO> getAllContent(String bucketName){
        ArrayList<ImageContentDTO> dtoList = new ArrayList<>();
        try {
            List<byte[]> imageKeys = new ArrayList<>();
            ObjectListing s3Object = s3Client.listObjects(bucketName);
            for(S3ObjectSummary objectSummary : s3Object.getObjectSummaries()){
                ImageContentDTO dto = new ImageContentDTO();
                String key = objectSummary.getKey();
                dto.setUniqueIdName(key);
                if (key.endsWith(".png") || key.endsWith(".jpg") || key.endsWith(".jpeg") || key.endsWith(".gif")) {
                    S3Object s3Objectbyte = s3Client.getObject(bucketName, key);
                    dto.setContent(IOUtils.toByteArray(s3Objectbyte.getObjectContent()));
//                  imageKeys.add(IOUtils.toByteArray(s3Objectbyte.getObjectContent()));
                    dtoList.add(dto);
                }
            }
            return  dtoList;
        } catch (IOException e) {
            throw new RuntimeException("Failed to download file from S3", e);
        }
    }

    public void deleteFile(String fileName) {
        s3Client.deleteObject(bucketName, fileName);
    }

    private String generateFileName(MultipartFile file,long id) {
        return id + "_" + file.getOriginalFilename();
    }

    }
