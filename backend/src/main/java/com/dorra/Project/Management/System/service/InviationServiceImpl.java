package com.dorra.Project.Management.System.service;

import com.dorra.Project.Management.System.Repository.InvitaionRepository;
import com.dorra.Project.Management.System.modal.Invitation;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InviationServiceImpl implements InvitationService {
    @Autowired
     private InvitaionRepository invitaionRepository;

    private EmailService emailService;


    @Override
    public void sendInvitaion(String email, Long projectId) throws MessagingException {
        String invitationToken= UUID.randomUUID().toString();

        Invitation invitation= new Invitation();
        invitation.setEmail(email);
        invitation.setProjectId(projectId);
        invitation.setToken(invitationToken);

        invitaionRepository.save(invitation);

        String invitationLink="http://localhost:5173/accept_invitation?token="+invitationToken;
        emailService.sendEmailWithToken(email,invitationLink);
    }

    @Override
    public Invitation acceptInvitation(String token, Long userId) throws Exception{
        Invitation invitation=invitaionRepository.findByToken(token);
        if(invitation==null){
            throw new Exception("invalid invitation token");
        }
        return invitation;
    }

    @Override
    public String getTokenByUserMail(String userEmail) {
        Invitation invitation=invitaionRepository.findByEmail(userEmail);
        return invitation.getToken();
    }

    @Override
    public void deleteToken(String token) {
        Invitation invitation = invitaionRepository.findByToken(token);
        invitaionRepository.delete(invitation);
    }
}
