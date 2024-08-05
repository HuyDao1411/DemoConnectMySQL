package org.sakaiproject.tool.testMySQL.repository;

import java.util.List;

import org.sakaiproject.tool.testMySQL.entity.ContentCollection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentCollectionRepository extends JpaRepository<ContentCollection, String> {
    List<ContentCollection> findByInCollection(String inCollection);
    
    //loadall
    List<ContentCollection> findAll();

}