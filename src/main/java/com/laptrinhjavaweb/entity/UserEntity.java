package com.laptrinhjavaweb.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name="user")
@Entity
public class UserEntity extends  BaseEntity{

    @Column(name = "username")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name="fullname")
    private String fullName;
    @Column(name="status")
    private Integer status;

    @ManyToMany(fetch = FetchType.EAGER)// khai báo thôi
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name ="userid" ),
            inverseJoinColumns =@JoinColumn(name = "roleid"))// tạo bảng trung gian user_rolo 2 cột userid và roleid
    private List<RoleEntity> roles = new ArrayList<>();
//cả cụm trên bỏ bên RoleEntity cũng được nhưng chỉ bỏ 1 bên thôi
    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passWord) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }



}
