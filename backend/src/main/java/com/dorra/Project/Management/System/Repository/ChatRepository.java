package com.dorra.Project.Management.System.Repository;

import com.dorra.Project.Management.System.modal.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat,Long> {
}
