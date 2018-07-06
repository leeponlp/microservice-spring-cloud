package com.leepon.cloud.repository;

import com.leepon.cloud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @task:
 * @discrption:
 * @author: 苏小城
 * @date: 17/12/26
 * @version: 1.0.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
