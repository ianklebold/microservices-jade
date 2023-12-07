package com.microjade.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * The type Base entity.
 *
 * @MappedSuperclass  Indica a JPA que esta clase actua como superclass para todas mis entidades que extiendan de esta clase
 * @EntityListeners(AuditingEntityListener.class) Esta clase gestiona el @CreatedDate, @CreatedBy etc... Se debe activar en la clase main
 * -> Con Esta anotacion no es necesario el createdAt y createdBy en el codigo, ya que se completa de manera automatica
 * -> createdBy y updatedBy es gestionado por auditAwareImpl
 *
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter @ToString
public class BaseEntity {

    //Necesitamos escribir la logica apra el createdBy y modifiedBy
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(insertable = false)
    private String updatedBy;
}
