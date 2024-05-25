package com.dorra.Project.Management.System.controller;

import com.dorra.Project.Management.System.modal.Issue;
import com.dorra.Project.Management.System.modal.IssueDTO;
import com.dorra.Project.Management.System.modal.User;
import com.dorra.Project.Management.System.response.AuthResponse;
import com.dorra.Project.Management.System.response.IssueRequest;
import com.dorra.Project.Management.System.response.MessageResponse;
import com.dorra.Project.Management.System.service.IssueService;
import com.dorra.Project.Management.System.service.UserService;
import com.zaxxer.hikari.metrics.PoolStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {
    @Autowired
    private IssueService issueService;
    @Autowired
    private UserService userService;

    @GetMapping("/{issueId}")
    public ResponseEntity<Issue>getIssueById(@PathVariable Long issueId)throws Exception{
        return ResponseEntity.ok(issueService.getIssueById(issueId));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Issue>> getIssueByProjectId(@PathVariable Long projectId) throws Exception {
        return ResponseEntity.ok(issueService.getIssueByProjectId(projectId));
    }

    @PostMapping
    public ResponseEntity<IssueDTO>createIssue(@RequestBody IssueRequest issue,@RequestHeader("Authorization")String token) throws Exception {

        User tokenUser=userService.findUserProfileByJwt(token);
        User user=userService.findUserById(tokenUser.getId());

        Issue createdIssue = issueService.createIssue(issue,tokenUser);
        IssueDTO issueDTO=new IssueDTO();
        issueDTO.setDescription(createdIssue.getDescription());
        issueDTO.setDueDate(createdIssue.getDueDate());
        issueDTO.setId(createdIssue.getId());
        issueDTO.setPriority(createdIssue.getPriority());
        issueDTO.setAssignee(createdIssue.getAssignee());
        issueDTO.setTags(createdIssue.getProject().getTags());
        issueDTO.setTitle(createdIssue.getTitle());
        issueDTO.setStatus(createdIssue.getStatus());
        issueDTO.setProject(createdIssue.getProject());
        issueDTO.setProjectID(issueDTO.getProjectID());

        return ResponseEntity.ok(issueDTO);
    }

    @DeleteMapping("/{issueId}")
    public ResponseEntity<MessageResponse>deleteIssue(@PathVariable Long issueId,@RequestHeader("Authoriation")String token)throws Exception{
        User user =userService.findUserProfileByJwt(token);
        issueService.deleteIssue(issueId, user.getId());
        MessageResponse res=new MessageResponse();
        res.setMessage("Issue deleted");

        return ResponseEntity.ok(res);
    }
    @GetMapping("/{issueId}/assignee/{userId}")
    public ResponseEntity<Issue>addUserToIssue(@PathVariable Long issueId,@PathVariable Long userId)throws Exception{
        Issue issue=issueService.addUserToIssue(issueId,userId);
        return ResponseEntity.ok(issue);
    }
    @PutMapping("/{issueId}/status/{status}")
    public ResponseEntity<Issue>updateIssueStatus(@PathVariable String status,@PathVariable Long issueId) throws Exception{
        Issue issue=issueService.updateStatus(issueId,status);
        return ResponseEntity.ok(issue);

    }




}
