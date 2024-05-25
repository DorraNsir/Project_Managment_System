package com.dorra.Project.Management.System.service;

import com.dorra.Project.Management.System.Repository.ChatRepository;
import com.dorra.Project.Management.System.modal.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatRepository chatRepository;
    @Override
    public Chat createdChat(Chat chat) {

        return chatRepository.save(chat);
    }
}
