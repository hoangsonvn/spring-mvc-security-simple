package com.laptrinhjavaweb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name="role")
@Entity
public class RoleEntity extends BaseEntity {

    @Column(name = "code")
    private  String code;
    @Column(name = "name")
    private String name;
@ManyToMany(mappedBy = "roles")
private  List<UserEntity> users = new ArrayList<>();


    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
