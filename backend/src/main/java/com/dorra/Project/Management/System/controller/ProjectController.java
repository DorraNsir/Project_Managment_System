package com.dorra.Project.Management.System.controller;

import com.dorra.Project.Management.System.modal.Chat;
import com.dorra.Project.Management.System.modal.Invitation;
import com.dorra.Project.Management.System.modal.Project;
import com.dorra.Project.Management.System.modal.User;
import com.dorra.Project.Management.System.request.InviteRequest;
import com.dorra.Project.Management.System.response.MessageResponse;
import com.dorra.Project.Management.System.service.InvitationService;
import com.dorra.Project.Management.System.service.ProjectService;
import com.dorra.Project.Management.System.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;

    private InvitationService invitationService;

    @GetMapping("")
    public ResponseEntity<List<Project>> getProjects(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String tag,
            @RequestParam(required = false) String jwt) throws Exception {

        User user = userService.findUserProfileByJwt(jwt);

        List<Project> projects = projectService.getProjectByTeam(user, category, tag);

        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectById(
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String jwtHeader)
            throws Exception {
        System.out.println("JWT Header: " + jwtHeader);

        // Extract the token from the header
        String jwt = null;
        if (jwtHeader != null && jwtHeader.startsWith("Bearer ")) {
            jwt = jwtHeader.substring(7);
        }
        User user = userService.findUserProfileByJwt(jwt);
        Project project = projectService.getProjectById(projectId);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Project>createProject(
            @RequestHeader("Authorization")String jwt,
            @RequestBody Project project)
            throws Exception {
        User user =userService.findUserProfileByJwt(jwt);
        Project createdProject=projectService.createProject(project,user);
        return new ResponseEntity<>(createdProject, HttpStatus.OK);
    }


    @PatchMapping ("/{projectId}")
    public ResponseEntity<Project>updateProjects(
            @PathVariable long projectId,
            @RequestHeader("Authorization")String jwt,
            @RequestBody Project project)
            throws Exception {
        User user =userService.findUserProfileByJwt(jwt);
        Project updatedProject =projectService.updateProject(project,projectId);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }

    @DeleteMapping ("/{projectId}")
    public ResponseEntity<MessageResponse>deleteProjects(
            @PathVariable long projectId,
            @RequestHeader("Authorization")String jwt
            )
            throws Exception {
        User user =userService.findUserProfileByJwt(jwt);
        projectService.deleteProject(projectId,user.getId());
        MessageResponse res=new MessageResponse("project deleted successfully");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<List<Project>> searchProjects(
            @RequestParam(required = false) String keyword,
            @RequestHeader("Authorization") String jwtHeader)
            throws Exception {
        System.out.println("JWT Header: " + jwtHeader);

        // Extract the token from the header
        String jwt = null;
        if (jwtHeader != null && jwtHeader.startsWith("Bearer ")) {
            jwt = jwtHeader.substring(7);
        }

        System.out.println("Search JWT: " + jwt);
        User user = userService.findUserProfileByJwt(jwt);
        System.out.println("Search user: " + user);
        List<Project> projects = projectService.searchProjects(keyword, user);
        System.out.println("Search user projects: " + projects);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{projectId}/chat")
    public ResponseEntity<Chat>getCahtByProjectId(
            @PathVariable Long projectId,
            @RequestParam(required = false)String jwt)
            throws Exception {
        User user =userService.findUserProfileByJwt(jwt);
        Chat chat=projectService.getChatByProjectId(projectId);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }
    @PostMapping("/invite")
    public ResponseEntity<MessageResponse>inviteProject(
            @RequestBody InviteRequest req,
            @RequestBody Project project,
            @RequestHeader ("Authorization") String jwt)
            throws Exception {
        User user =userService.findUserProfileByJwt(jwt);
        invitationService.sendInvitaion(req.getEmail(),req.getProjectId());
        MessageResponse res=new MessageResponse("User invitation sent");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/accept_invitation")
    public ResponseEntity<Invitation>acceptInvitationProject(
            @RequestParam String token,
            @RequestBody Project project,
            @RequestHeader ("Authorization") String jwt)
            throws Exception {
        User user =userService.findUserProfileByJwt(jwt);
        Invitation invitation =invitationService.acceptInvitation(token,user.getId());
        projectService.addUserToProject(invitation.getProjectId(),user.getId());
        return new ResponseEntity<>(invitation, HttpStatus.OK);
    }



}
