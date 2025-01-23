package com.personalfolio.voyage.request;

import com.personalfolio.voyage.store.AWSS3Respository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;


@RestController @RequestMapping("api/v1/files")
public class StoreHandler {
    @Autowired
    private AWSS3Respository awss3Respository;

    @PostMapping("/uploadcontent/{fileName}")
    public ResponseEntity<String> storeContent(@RequestParam("file") MultipartFile file, @PathVariable String fileName) throws IOException {
        String url =awss3Respository.uploadContent(file,fileName);
        return ResponseEntity.ok(url);
    }

    @GetMapping("/getcontent/{fileName}")
    public  ResponseEntity<byte[]> getContent(@PathVariable String fileName){
        byte []  content = awss3Respository.getContent(fileName);
        return  ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(content);
    }

    @GetMapping("getAllContent/{bucketName}")
    public ResponseEntity<ArrayList<byte[]>> getAllContent(@PathVariable  String bucketName){
        return  ResponseEntity.ok(awss3Respository.getAllContent(bucketName));
    }
}
