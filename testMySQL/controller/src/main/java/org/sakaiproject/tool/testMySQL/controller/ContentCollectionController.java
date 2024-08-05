package org.sakaiproject.tool.testMySQL.controller;

import org.sakaiproject.tool.testMySQL.entity.ContentCollection;
import org.sakaiproject.tool.testMySQL.entity.ResourceFile;
import org.sakaiproject.tool.testMySQL.service.ContentCollectionService;
import org.sakaiproject.tool.testMySQL.service.ResourceFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class ContentCollectionController {

    @Autowired
    private ContentCollectionService contentCollectionService;
    
    @Autowired
    private ResourceFileService resourceFileService; 

    @GetMapping("/collections")
    public String listCollections(Model model) {
        model.addAttribute("collections", contentCollectionService.getAllCollections());
        return "collections";
    }

    @GetMapping("/collections/upload")
    public String showUploadForm() {
        return "upload";
    }

    @PostMapping("/collections/upload")
    public String uploadCollection(@RequestParam("file") MultipartFile file,
                                   @RequestParam("collectionId") String collectionId, Model model) {
        try {
            contentCollectionService.saveCollection(file, collectionId);
            model.addAttribute("message", "File uploaded successfully!");
        } catch (IOException e) {
            model.addAttribute("message", "Failed to upload file!");
            e.printStackTrace();
        }
        return "upload";
    }

    @GetMapping("/collections/{id}")
    public String viewCollection(@PathVariable String id, Model model) {
        ContentCollection collection = contentCollectionService.getCollection(id);
        if (collection != null) {
            model.addAttribute("collectionName", collection.getCollectionId());
            model.addAttribute("collectionId", collection.getCollectionId());
            model.addAttribute("files", contentCollectionService.getResourceFilesForCollection(collection));
        }
        return "collection";
    }

    

}
