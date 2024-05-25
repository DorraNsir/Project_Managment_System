package com.dorra.Project.Management.System.service;

import com.dorra.Project.Management.System.modal.Message;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MessageService {
    Message sendMessage(Long senderId,Long chatId,String content)throws  Exception;
    List<Message>getMessagesByProjectId(Long projectId)throws Exception;
}
