package com.zentha.zentha.repository;

import com.zentha.zentha.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Score repository accessor using Spring's @link{CrudRepository}.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}