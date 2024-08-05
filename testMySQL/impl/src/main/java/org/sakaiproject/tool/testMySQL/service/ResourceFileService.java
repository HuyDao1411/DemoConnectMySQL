package org.sakaiproject.tool.testMySQL.service;

import java.util.List;

import org.sakaiproject.tool.testMySQL.entity.ResourceFile;
import org.sakaiproject.tool.testMySQL.repository.ResourceFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceFileService {

    @Autowired
    private ResourceFileRepository resourceFileRepository;

    public ResourceFile getResourceFile(String resourceId) {
        return resourceFileRepository.findById(resourceId).orElse(null);
    }
//    public ResourceFile getResourceFileByUuid(String resourceUuid) { // Thêm phương thức này
//        return resourceFileRepository.findByResourceUuid(resourceUuid);
//    }
}
