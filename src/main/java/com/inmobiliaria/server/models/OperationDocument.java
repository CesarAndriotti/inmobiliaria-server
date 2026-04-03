package com.inmobiliaria.server.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor  
@NoArgsConstructor 
@Table(name = "operation_document",

    indexes = {
        @Index(name = "fk_operation_document_operation1_idx", columnList = "operation_id"),
        @Index(name = "fk_operation_document_document_type1_idx", columnList = "document_type_id")
    }
)
public class OperationDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false, length = 500)
    private String link;

    @ManyToOne
    @JoinColumn(name = "operation_id", nullable = false, foreignKey = @ForeignKey(name = "fk_operation_document_operation1"))
    private Operation operation;

    @ManyToOne
    @JoinColumn(name = "document_type_id", nullable = false, foreignKey = @ForeignKey(name = "fk_operation_document_document_type1"))
    private DocumentType documentType;
}
