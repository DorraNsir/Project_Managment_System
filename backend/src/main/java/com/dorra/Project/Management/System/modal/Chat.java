package com.dorra.Project.Management.System.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;


    @OneToOne
    private Project project ;

    @JsonIgnore
    @OneToMany(mappedBy = "chat",cascade=CascadeType.ALL,orphanRemoval = true)
    private List<Message>messages;

    @ManyToMany
    private List<User> users=new ArrayList<>();


    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", projectId=" + (project != null ? project.getId() : "null") +
                ", messages=" + (messages != null ? messages.size() : "null") +
                ", users=" + users +
                '}';
    }


}
