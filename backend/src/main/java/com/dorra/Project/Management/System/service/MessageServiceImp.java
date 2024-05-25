package com.dorra.Project.Management.System.service;

import com.dorra.Project.Management.System.Repository.MessageRepository;
import com.dorra.Project.Management.System.Repository.UserRepository;
import com.dorra.Project.Management.System.modal.Chat;
import com.dorra.Project.Management.System.modal.Message;
import com.dorra.Project.Management.System.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImp implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectService projectService;


    public Message sendMessage(Long senderId,Long projectId,String content)throws Exception{
        User sender =userRepository.findById(senderId).orElseThrow(()->new Exception("User not found with id"+senderId));

        Chat chat =projectService.getProjectById(projectId).getChat();

        Message message=new Message();
        message.setContent(content);
        message.setSender(sender);
        message.setCreatedAt(LocalDateTime.now());
        message.setChat(chat);
        Message savedMessage = messageRepository.save(message);

        chat.getMessages().add(savedMessage);

        return savedMessage;

    }
    public List<Message>getMessagesByProjectId(Long projectId)throws Exception{
        Chat chat=projectService.getChatByProjectId(projectId);
        return messageRepository.findByChatIdOrderByCreatedAtAsc(chat.getId());
    }

}
