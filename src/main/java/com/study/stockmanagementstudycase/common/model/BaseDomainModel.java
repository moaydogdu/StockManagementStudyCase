package com.study.stockmanagementstudycase.common.model;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class BaseDomainModel {

    public LocalDateTime createdAt;
    public String createdBy;
    public LocalDateTime updatedAt;
    public String updatedBy;

}
