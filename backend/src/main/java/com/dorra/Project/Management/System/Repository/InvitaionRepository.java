package com.dorra.Project.Management.System.Repository;

import com.dorra.Project.Management.System.modal.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitaionRepository extends JpaRepository<Invitation,Long> {
    Invitation findByToken(String token);
    Invitation findByEmail(String userEmail);
}
