package com.laptrinhjavaweb.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
@EntityListeners(AuditingEntityListener.class)// để sử dụng được @createdate by, modi dateby ta khai báo cái này và confid thêm nữa
@MappedSuperclass // để định nghĩa là cha các entity con kia
public abstract class BaseEntity {//abstract cho chuẩn nghĩa

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //không có set id đâu
    private  Long id;
    @Column(name = "createddate")
    @CreatedDate
    private Date createdDate;
    @Column(name="modifieddate")
    @LastModifiedDate
    private  Date modifiedDate;
    @Column(name = "createdby")
    @CreatedBy
    private String createdBy;
    @Column(name = "modifiedby")
    @LastModifiedBy
    private String modifiedBy;

    public Long getId() {
        return id;
    }



    public Date getCreatedDate() {
        return createdDate;
    }


    public Date getModifiedDate() {
        return modifiedDate;
    }


    public String getCreatedBy() {
        return createdBy;
    }


    public String getModifiedBy() {
        return modifiedBy;
    }

}
