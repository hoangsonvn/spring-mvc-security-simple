package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
UserEntity findOneByUserNameAndStatus(String name,Integer status);// như câu findOneByNamePasswordStatus trong jdbc mình viết
}
