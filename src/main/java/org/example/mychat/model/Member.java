package org.example.mychat.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity{
    private String fullName;
    private String nickname;
    private String password;
    private String phoneNumber;

    @OneToMany(mappedBy = "sender")
    private List<Message> messages;

    @ManyToMany(mappedBy = "members")
    private List<Chat> chat;
}