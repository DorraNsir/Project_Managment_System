package com.dorra.Project.Management.System.Repository;

import com.dorra.Project.Management.System.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User  findByEmail(String email);

}
