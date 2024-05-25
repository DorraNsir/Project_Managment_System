package com.dorra.Project.Management.System.Repository;

import com.dorra.Project.Management.System.modal.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByIssueId(Long issueId);
}
