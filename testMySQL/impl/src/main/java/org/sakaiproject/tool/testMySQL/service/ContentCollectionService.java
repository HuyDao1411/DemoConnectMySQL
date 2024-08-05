package org.sakaiproject.tool.testMySQL.service;
import org.sakaiproject.tool.testMySQL.entity.ContentCollection;
import org.sakaiproject.tool.testMySQL.entity.ResourceFile;
import org.sakaiproject.tool.testMySQL.repository.ContentCollectionRepository;
import org.sakaiproject.tool.testMySQL.repository.ResourceFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

@Service
public class ContentCollectionService {

    @Autowired
    private ContentCollectionRepository contentCollectionRepository;

    @Autowired
    private ResourceFileRepository resourceFileRepository;

    public List<ContentCollection> getAllCollections() {
        return contentCollectionRepository.findAll();
    }

    public ContentCollection getCollection(String id) {
        return contentCollectionRepository.findById(id).orElse(null);
    }

    public ContentCollection createCollection(ContentCollection contentCollection) {
        return contentCollectionRepository.save(contentCollection);
    }
    
    public void saveCollection(MultipartFile file, String collectionId) throws IOException {
        String[] parts = collectionId.split("/");
        String parentCollectionId = String.join("/", Arrays.copyOfRange(parts, 0, parts.length - 1));
        if(parentCollectionId.length()==0) {
        	parentCollectionId="/";
        }else {
        	parentCollectionId="/"+parentCollectionId+"/";
        }
        // Tạo thư mục cha nếu chưa tồn tại
        if (!parentCollectionId.isEmpty() && !contentCollectionRepository.existsById(parentCollectionId)) {
            ContentCollection parentCollection = new ContentCollection();
            parentCollection.setCollectionId(parentCollectionId);
            contentCollectionRepository.save(parentCollection);
        }

        ContentCollection collection = new ContentCollection();
        collection.setCollectionId("/"+collectionId+"/");
        collection.setInCollection(parentCollectionId);
        collection.setBinaryEntity(file.getBytes());
        contentCollectionRepository.save(collection);

        ResourceFile resourceFile = new ResourceFile();
        resourceFile.setResourceId("/"+collectionId+"/");
        resourceFile.setResourceUuid(UUID.randomUUID().toString());
        resourceFile.setInCollection(parentCollectionId);
        resourceFile.setFilePath(file.getOriginalFilename());
        resourceFile.setFileSize(file.getSize());
        resourceFile.setBinaryEntity(file.getBytes());
        resourceFileRepository.save(resourceFile);
    }

//    @Transactional
//    public void saveCollection(MultipartFile file, String collectionId) throws IOException {
//        // Kiểm tra xem có collection hiện tại tồn tại không
//        ContentCollection currentCollection = contentCollectionRepository.findById(collectionId).orElse(new ContentCollection());
//        
//        // Kiểm tra xem collection có nằm trong một collection khác không
//        String parentCollectionId = currentCollection.getInCollection();
//        if (parentCollectionId != null && !parentCollectionId.isEmpty()) {
//            // Nếu có, cập nhật inCollection và collectionId theo quy định mới
//            currentCollection.setCollectionId(parentCollectionId + "/" + collectionId);
//            currentCollection.setInCollection(parentCollectionId);
//        } else {
//            // Nếu không nằm trong bất kỳ collection nào, collectionId giữ nguyên
//            currentCollection.setCollectionId(collectionId);
//        }
//        
//        currentCollection.setXml(""); // Set XML nếu cần
//        currentCollection.setBinaryEntity(file.getBytes());
//        contentCollectionRepository.save(currentCollection);
//        
//        String RsUUI=UUID.randomUUID().toString();
//
//        // Lưu ResourceFile
//        ResourceFile resourceFile = new ResourceFile();
//        resourceFile.setResourceId("/"+currentCollection.getCollectionId() + "/" + file.getOriginalFilename());
//        resourceFile.setFilePath("/"+currentCollection.getCollectionId() + "/" + file.getOriginalFilename()); // Đường dẫn file
//        resourceFile.setFileSize(file.getSize());
//        resourceFile.setBinaryEntity(file.getBytes());
//        resourceFile.setResourceUuid(RsUUI);
//        resourceFile.setInCollection(currentCollection.getCollectionId());
//        resourceFile.setContentCollection(currentCollection);
//        resourceFileRepository.save(resourceFile);
//    }


    public List<ResourceFile> getResourceFilesForCollection(ContentCollection collection) {
        return resourceFileRepository.findByContentCollection(collection);
    }

    public ResourceFile getFile(String id) {
        return resourceFileRepository.findById(id).orElse(null);
    }
}
