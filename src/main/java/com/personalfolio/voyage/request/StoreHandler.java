package com.personalfolio.voyage.request;

import com.personalfolio.voyage.multiFormat.ImageContentDTO;
import com.personalfolio.voyage.store.AWSS3Respository;
import com.personalfolio.voyage.utils.ResponseSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;

@RestController @RequestMapping("api/v1/files")
public class StoreHandler {

    @Value("secret.key")
    private String authKey;

    @Autowired
    private AWSS3Respository awss3Respository;

    @PostMapping("/uploadcontent/{fileName}/{makeMeSmile}")
    public ResponseSchema<String> storeContent(@RequestParam("file") MultipartFile file, @PathVariable String fileName, @PathVariable String makeMeSmile) throws IOException {
        if(makeMeSmile == authKey){
            return ResponseSchema.of("not authorized",HttpStatus.BAD_REQUEST,"");
        }
        String url = awss3Respository.uploadContent(file,fileName);

        return ResponseSchema.of(url,HttpStatus.OK,"");
    }

    @GetMapping("/getcontent/{fileName}/{makeMeSmile}")
    public ResponseEntity<String> getContent(@PathVariable String fileName, @PathVariable String makeMeSmile){
        if(makeMeSmile == authKey){
            return ResponseSchema.ofByDefault("not authorized",HttpStatus.BAD_REQUEST);
        }
        byte []  content = awss3Respository.getContent(fileName);
        return  ResponseSchema.ofWithBody(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"",content);
    }

    @GetMapping("getAllContent/{bucketName}")
    public ResponseEntity<ArrayList<ImageContentDTO>> getAllContent(@PathVariable  String bucketName){
        return  ResponseEntity.ok(awss3Respository.getAllContent(bucketName));
    }
}
