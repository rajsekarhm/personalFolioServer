package com.personalfolio.voyage.request;

import com.personalfolio.voyage.multiFormat.MultiFormat;
import com.personalfolio.voyage.multiFormat.MultiFormatDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@RestController
public class Handlers {

    @PostMapping("/upload/{id}")
    public ResponseEntity<String> storeContent(@RequestParam("file")MultipartFile file, @PathVariable Long id, @RequestBody String description) throws IOException {
        MultiFormat format = new MultiFormat();
        Date date = new Date();
        format.setData(file.getBytes());
        format.setId(id);
        format.setDescription(description);
        format.setTime(date.toString());
        return ResponseEntity.ok("File uploaded successfully.");
    }

    @GetMapping("getContent/{id}")
    public  ResponseEntity<MultiFormatDTO> getContent(@PathVariable Long id){
        return ResponseEntity.ok(new MultiFormatDTO());
    }
}
