package org.sakaiproject.tool.testMySQL.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@Entity
@Table(name = "content_resource")
public class ResourceFile {
    @Id
    @Column(name = "RESOURCE_ID", length = 255)
    private String resourceId;

    @Column(name = "RESOURCE_UUID", length = 36)
    private String resourceUuid;

    @Column(name = "IN_COLLECTION", length = 255)
    private String inCollection;

    @Column(name = "CONTEXT", length = 99)
    private String context;

    @Column(name = "FILE_PATH", length = 128)
    private String filePath;

    @Column(name = "FILE_SIZE")
    private Long fileSize;

    @Column(name = "RESOURCE_TYPE_ID", length = 255)
    private String resourceTypeId;

    @Lob
    @Column(name = "XML")
    private String xml;

    @Lob
    @Column(name = "BINARY_ENTITY")
    private byte[] binaryEntity;

    @ManyToOne
    @JoinColumn(name = "IN_COLLECTION", referencedColumnName = "COLLECTION_ID", insertable = false, updatable = false)
    private ContentCollection contentCollection;
}
