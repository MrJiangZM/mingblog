package com.ming.blog.controller;

import com.ming.blog.service.FileService;
import io.minio.ObjectWriteResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@RequestMapping("/image")
@Slf4j
@CrossOrigin(origins = "*")
public class ImageController {

    @Autowired
    private FileService fileService;

    /*******
     * Get image file, this method return an image type file which can be displayed in browser.
     * @param bucketName, system, each system should belong a special bucket.
     * @param category, a system may contain multiple category
     * @param filename, get filename
     */
//    @GetMapping(value = "/get/{bucketName}/{category}/{objectName}/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
//    public byte[] get(@PathVariable("bucketName") String bucketName, @PathVariable("category") String category,
//                      @PathVariable("objectName") String objectName,
//                      @PathVariable("fileName") String fileName) throws Exception {
//        return fileService.getFile(bucketName, category, objectName);
//    }
//
//    @GetMapping("/download/{bucketName}/{category}/{objectName}/{fileName}")
//    public void download(@PathVariable("bucketName") String bucketName, @PathVariable("category") String category,
//                         @PathVariable("objectName") String objectName,
//                         @PathVariable("fileName") String fileName, HttpServletResponse response) throws Exception {
//        byte[] buffer = fileService.getFile(bucketName, category, objectName);
//        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
//        response.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");
//        response.flushBuffer();
//        response.getOutputStream().close();
//    }

    @PostMapping("/upload/{bucketName}/{category}")
    public ObjectWriteResponse upload(@PathVariable("bucketName") String bucketName, @PathVariable("category") String category,
                                      @RequestParam("file") MultipartFile file) throws Exception {
        String objectName = UUID.randomUUID().toString() + "_" + System.currentTimeMillis();
        System.out.println(String.format("image/get/%s/%s/%s/%s", bucketName, category, objectName, file.getOriginalFilename()));
        return fileService.storeFile(bucketName, category, objectName, file.getInputStream(), file.getSize());
    }
}