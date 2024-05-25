package com.dorra.Project.Management.System.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class IssueRequest {
    private String title;
    private String description;
    private String status;
    private Long ProjectId;
    private String priority;
    private LocalDate dueDate;
}
