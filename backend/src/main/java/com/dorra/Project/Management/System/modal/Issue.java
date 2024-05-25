package com.dorra.Project.Management.System.modal;

import com.dorra.Project.Management.System.modal.Comment;
import com.dorra.Project.Management.System.modal.Project;
import com.dorra.Project.Management.System.modal.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    private String status;

    @Column(name = "project_id") // Explicitly map this field to the project_id column
    private Long projectID;

    private String priority;
    private LocalDate dueDate;
    private List<String> tags = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "assignee_id") // Ensure the column name matches the foreign key
    private User assignee;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "project_id", insertable = false, updatable = false) // Map the relationship to the same column
    private Project project;

    @JsonIgnore
    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
}
