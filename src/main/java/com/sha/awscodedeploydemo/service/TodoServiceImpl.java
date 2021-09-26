package com.sha.awscodedeploydemo.service;

import com.sha.awscodedeploydemo.model.Image;
import com.sha.awscodedeploydemo.model.Images;
import com.sha.awscodedeploydemo.model.User;
import com.sha.awscodedeploydemo.repository.ImageRepository;
import com.sha.awscodedeploydemo.repository.TodoRepository;
import com.sha.awscodedeploydemo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    //@Autowired
    //private final FileStore fileStore;

    @Autowired
    private final TodoRepository repository;

    @Autowired
    private final ImageRepository repo;

    @Autowired
    private final UserRepository urepo;

    @Override
    public Images saveTodo(String username, String title, String description, MultipartFile[] files) {
        //check if the file is empty
        if (files.length == 0) {
            throw new IllegalStateException("Cannot upload empty file");
        }

        boolean err = false;

        for(MultipartFile file: files) {
            //Check if the file is an image
            if (!Arrays.asList(IMAGE_PNG.getMimeType(),
                    IMAGE_BMP.getMimeType(),
                    IMAGE_GIF.getMimeType(),
                    IMAGE_JPEG.getMimeType()).contains(file.getContentType())) {
               err = true;
               break;
            }
        }

        if(err) {
            throw new IllegalStateException("FIle uploaded is not an image");
        }



        //get file metadata
        /*Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        //Save Image in S3 and then save Todo in the database
        String path = String.format("%s/%s", BucketName.BUCKET_IMAGE.getBucketName(), UUID.randomUUID());
        String fileName = String.format("%s", file.getOriginalFilename());
        try {
            fileStore.upload(path, fileName, Optional.of(metadata), file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to upload file", e);
        }*/

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

        List<Image> lst = new ArrayList<>();

        if(files.length > 1) {

            for(MultipartFile file: files) {
                timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                Image img = Image.builder()
                        .imageFileName(timestamp)
                        .imagePath("image/"+timestamp)
                        .build();
                repo.save(img);
                lst.add(img);
            }



        } else {
            Image img = Image.builder()
                        .imageFileName(timestamp)
                        .imagePath("image/"+timestamp)
                        .build();
            repo.save(img);
            lst.add(img);
        }

        Images images = Images.builder()
                .description(description)
                .title(title)
                .user(urepo.findByUsername(username).get())
                .images(lst)
                .build();
        repository.save(images);

        return repository.findByTitle(images.getTitle());
    }

    @Override
    public byte[] downloadTodoImage(String id) {
        Images images = repository.findById(id).get();
        return null;//fileStore.download(images.getImagePath(), images.getImageFileName());
    }

    @Override
    public List<Images> getAllTodosByUser(String username) {

        User user = urepo.findByUsername(username).get();

        List<Images> images = new ArrayList<>();
        repository.findByUser(user).forEach(images::add);
        return images;
    }
}
