package org.sakaiproject.tool.testMySQL.service;

import org.sakaiproject.tool.testMySQL.entity.ContentCollection;
import org.sakaiproject.tool.testMySQL.entity.ResourceFile;
import org.sakaiproject.tool.testMySQL.repository.ContentCollectionRepository;
import org.sakaiproject.tool.testMySQL.repository.ResourceFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContentService {

    @Autowired
    private ContentCollectionRepository contentCollectionRepository;

    @Autowired
    private ResourceFileRepository resourceFileRepository;

    public List<ContentCollection> getSubCollections(String inCollection) {
        return contentCollectionRepository.findByInCollection(inCollection);
    }

    public List<ResourceFile> getFilesByCollectionId(String collectionId) {
        return resourceFileRepository.findByInCollection(collectionId);
    }
    // Phương thức lấy tất cả các thư mục
    public List<ContentCollection> getAllCollections() {
        return contentCollectionRepository.findAll();
    }

    // Phương thức lấy tất cả các file
    public List<ResourceFile> getAllFiles() {
        return resourceFileRepository.findAll();
    }
    // Phương thức lấy file chi tiết theo UUID
    public Optional<ResourceFile> getFileByUuid(String resourceUuid) {
        return resourceFileRepository.findByResourceUuid(resourceUuid);
    }

}
