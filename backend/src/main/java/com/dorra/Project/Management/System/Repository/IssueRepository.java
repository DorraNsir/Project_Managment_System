package com.dorra.Project.Management.System.Repository;

import com.dorra.Project.Management.System.modal.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByProjectID(Long projectID);
}
