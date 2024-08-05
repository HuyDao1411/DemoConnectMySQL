package org.sakaiproject.tool.testMySQL.repository;

import java.util.List;
import java.util.Optional;

import org.sakaiproject.tool.testMySQL.entity.ContentCollection;
import org.sakaiproject.tool.testMySQL.entity.ResourceFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceFileRepository extends JpaRepository<ResourceFile, String> {
    List<ResourceFile> findByContentCollection(ContentCollection contentCollection);
    List<ResourceFile> findByInCollection(String inCollection);
    
    //load all
    List<ResourceFile> findAll();
    Optional<ResourceFile> findByResourceUuid(String resourceUuid);
}