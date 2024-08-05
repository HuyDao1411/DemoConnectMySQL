package org.sakaiproject.tool.testMySQL.controller;

import org.sakaiproject.tool.testMySQL.entity.ContentCollection;
import org.sakaiproject.tool.testMySQL.entity.ResourceFile;
import org.sakaiproject.tool.testMySQL.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
public class ContentController {

    @Autowired
    private ContentService contentService;
    
    @GetMapping("/")
    public String home(Model model) {
        List<ContentCollection> collections = contentService.getAllCollections();
        List<ResourceFile> files = contentService.getAllFiles();
        model.addAttribute("collections", collections);
        model.addAttribute("files", files);
        return "home";
    }

//    @GetMapping("/resources")
//    public String getResources(@RequestParam(required = false, defaultValue = "") String collectionId, Model model) {
//        List<ContentCollection> subCollections = contentService.getSubCollections(collectionId);
//        List<ResourceFile> resourceFiles = contentService.getFilesByCollectionId(collectionId);
//        
//        model.addAttribute("subCollections", subCollections);
//        model.addAttribute("resourceFiles", resourceFiles);
//        model.addAttribute("currentPath", collectionId);
//        
//        return "resourcesList";
//    }
    @GetMapping("/file/view")
    public String viewFile(@RequestParam String uuid, Model model) {
        Optional<ResourceFile> fileOptional = contentService.getFileByUuid(uuid);
        if (fileOptional.isPresent()) {
            ResourceFile file = fileOptional.get();
            // Chuyển đổi binary thành base64 nếu binaryEntity không null
            String base64Content = (file.getBinaryEntity() != null) ? 
                Base64.getEncoder().encodeToString(file.getBinaryEntity()) : null;
            
            model.addAttribute("file", file);
            model.addAttribute("base64Content", base64Content);
            return "fileView";
        } else {
            return "error"; // Trang lỗi nếu file không tồn tại
        }
    }
    
}
