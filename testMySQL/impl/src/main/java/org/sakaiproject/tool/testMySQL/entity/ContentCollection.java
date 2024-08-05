package org.sakaiproject.tool.testMySQL.entity;
import java.util.List;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@Entity
@Table(name = "content_collection")
public class ContentCollection {
    @Id
    @Column(name = "COLLECTION_ID", length = 255)
    private String collectionId;

    @Column(name = "IN_COLLECTION", length = 255)
    private String inCollection;
    
    @Lob
    @Column(name = "XML")
    private String xml;

    @Lob
    @Column(name = "BINARY_ENTITY")
    private byte[] binaryEntity;

    @OneToMany(mappedBy = "inCollection")
    private List<ContentCollection> subCollections;
    
    @OneToMany(mappedBy = "contentCollection")
    private List<ResourceFile> resourceFiles;
}
