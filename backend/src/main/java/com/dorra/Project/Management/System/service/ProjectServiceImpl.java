package com.dorra.Project.Management.System.service;

import com.dorra.Project.Management.System.Repository.ProjectRepository;
import com.dorra.Project.Management.System.modal.Chat;
import com.dorra.Project.Management.System.modal.Project;
import com.dorra.Project.Management.System.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ChatService chatService;
    @Override
    public Project createProject(Project project, User user) throws Exception {
        Project createdProject=new Project();
        createdProject.setCategory(project.getCategory());
        createdProject.setName(project.getName());
        createdProject.setDescription(project.getDescription());
        createdProject.setOwner(user);
        createdProject.getTeam().add(user);
        createdProject.setTags(project.getTags());
        Project savedProject =projectRepository.save(createdProject);
        Chat chat=new Chat();
        chat.setProject(savedProject);
        Chat projectChat=chatService.createdChat(chat);
        savedProject.setChat(projectChat);

        return savedProject;
    }

//    @Override
//    public List<Project> getProjectByTeam(User user, String category, String tag) throws Exception {
//        List<Project>projects=projectRepository.findByNameContainingAndTeamContains(user.getFullName(),user);
//        if(category!=null){
//            projects=projects.stream().filter(project ->project.getCategory().equals(category) ).collect(Collectors.toList());
//        }
//        if(tag!=null){
//            projects=projects.stream().filter(project ->project.getTags().contains(tag) ).collect(Collectors.toList());
//        }
//
//        return projects;
//    }
public List<Project> getProjectByTeam(User user, String category, String tag) throws Exception {
    System.out.println("Fetching projects for user: " + user.getFullName());
    List<Project> projects = projectRepository.findByTeamContains(user);
    System.out.println("Projects fetched from repository: " + projects);

    if (category != null) {
        projects = projects.stream().filter(project -> project.getCategory().equals(category)).collect(Collectors.toList());
    }
    if (tag != null) {
        projects = projects.stream().filter(project -> project.getTags().contains(tag)).collect(Collectors.toList());
    }

    System.out.println("Filtered projects: " + projects);
    return projects;
}


    @Override
    public Project getProjectById(Long projectId) throws Exception {
        Optional<Project> optionalproject=projectRepository.findById(projectId);
        if(optionalproject.isEmpty()){
            throw new Exception("Project Not found");
        }
        return optionalproject.get();
    }

    @Override
    public void deleteProject(Long projectId, Long userId) throws Exception {
        projectRepository.deleteById(projectId);


    }

    @Override
    public Project updateProject(Project updatedProject, Long id) throws Exception {
        Project project=getProjectById(id);

        project.setName(updatedProject.getName());
        project.setDescription(updatedProject.getDescription());
        project.setTags(updatedProject.getTags());

        return projectRepository.save(project);
    }

    @Override
    public void addUserToProject(Long projectId, Long userId) throws Exception {
        User user=userService.findUserById(userId);
        Project project=getProjectById(projectId);
        if(!project.getTeam().contains(user)){
            project.getTeam().add(user);
            project.getChat().getUsers().add(user);
        }
        projectRepository.save(project);
    }

    @Override
    public void deleteUserToProject(Long projectId, Long userId) throws Exception {
        User user=userService.findUserById(userId);
        Project project=getProjectById(projectId);
        if(!project.getTeam().contains(user)){
            project.getTeam().remove(user);
            project.getChat().getUsers().remove(user);
        }
        projectRepository.save(project);

    }

    @Override
    public Chat getChatByProjectId(Long projectId) throws Exception {
        Project project=getProjectById(projectId);
        return project.getChat();
    }

    @Override
    public List<Project> searchProjects(String keyword, User user) throws Exception {
        return projectRepository.findByNameContainingAndTeamContains(keyword,user);
    }
}
