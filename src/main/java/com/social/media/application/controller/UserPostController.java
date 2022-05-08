package com.social.media.application.controller;

import com.social.media.application.model.UserPost;
import com.social.media.application.service.UserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@RestController
public class UserPostController {

    @Autowired
    private UserPostService userPostService;

    // Post mapping for uploading new post.
    @PostMapping("/userpost/{userId}")
    public void addPost(@PathVariable long userId, @RequestParam("image") MultipartFile file) throws IOException {

        // Calling a compress function for compress the image.
        UserPost userPost =new UserPost(userId,compressBytes(file.getBytes()));
        userPostService.saveUserPost(userPost);
    }

    // Get mapping for return all the post of specific user.
    @GetMapping("/getPost/{id}")
    public UserPost getPost(@PathVariable Long id){
        UserPost userPost =userPostService.getPost(id);
        // Calling a decompress function for decompress the image.
        UserPost userPost1=new UserPost(id, decompressBytes(userPost.getImage()));
        return userPost1;
    }

    // Get mapping for return all post of users.
    @GetMapping("/getAllPost/{id}")
    public List<UserPost> getAllPost(@PathVariable long id)
    {
        return userPostService.getAllPost(id);
    }


    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the application
    // Base64 use to uncompress
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }



}
