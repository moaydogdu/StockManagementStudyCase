package com.study.stockmanagementstudycase.log.entity;

import com.study.stockmanagementstudycase.common.model.entity.BaseEntity;
import com.study.stockmanagementstudycase.user.model.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "LOG")
public class LogEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;

    @Column(name = "HTTP_METHOD")
    private String httpMethod;

    @Column(name = "PATH")
    private String path;

    @Column(name = "REQUEST_BODY")
    private Integer status;

    @Column(name = "IP")
    private String ip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "USER_ID",
            referencedColumnName = "ID"
    )
    private UserEntity user;
}

