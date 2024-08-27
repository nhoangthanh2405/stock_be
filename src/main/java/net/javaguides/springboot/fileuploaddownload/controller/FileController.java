package net.javaguides.springboot.fileuploaddownload.controller;

import jakarta.servlet.http.HttpServletRequest;

import net.javaguides.springboot.fileuploaddownload.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import net.javaguides.springboot.fileuploaddownload.model.DatabaseFile;
import net.javaguides.springboot.fileuploaddownload.payload.Response;
import net.javaguides.springboot.fileuploaddownload.service.Impl.DatabaseFileService;

@RestController
@CrossOrigin(origins = "https://stock-fe-nhoangthanh-tma.vercel.app") // Thay đổi URL với frontend của bạn
@RequestMapping("/api")
public class FileController {

    @Autowired
    private DatabaseFileService fileStorageService;

    @PostMapping("/upload-file")
    public ResponseModel uploadFile(@RequestParam("file") MultipartFile file) {
    	DatabaseFile fileName = fileStorageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/files/")
                .path(fileName.getId())
                .toUriString();
        Response response = new Response(fileName.getId(),fileName.getFile_name(), fileDownloadUri,
            file.getContentType(), file.getSize());
        return new ResponseModel<Object>(true, response);
    }

    @GetMapping("/files/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        DatabaseFile databaseFile = fileStorageService.getFile(fileName);

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(databaseFile.getFile_type()))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + databaseFile.getFile_name() + "\"")
            .body(new ByteArrayResource(databaseFile.getData()));
    }

    //  // Download ảnh
//  @GetMapping("/download/{id}")
//  public ResponseEntity<?> downloadImage(@PathVariable Integer id) {
//    Image image = imageService.getImage(id);
//    return ResponseEntity.ok()
//        .contentType(MediaType.parseMediaType(image.getType()))
//        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getName() + "\"")
//        .body(new ByteArrayResource(image.getData()));
//  }
//
  // Xóa ảnh
  @DeleteMapping("/files/{id}")
  public ResponseEntity<?> deleteImage(@PathVariable String id) {
      fileStorageService.deleteById(id);
    return ResponseEntity.noContent().build();
  }


  @GetMapping("")
  public String getText() {
    return "Hello work";
  }
}
