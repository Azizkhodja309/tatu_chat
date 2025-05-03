package org.example.mychat.service;

import lombok.RequiredArgsConstructor;
import org.example.mychat.model.Chat;
import org.example.mychat.model.Member;
import org.example.mychat.model.Message;
import org.example.mychat.model.dto.MessageDto;
import org.example.mychat.repository.ChatRepository;
import org.example.mychat.repository.MemberRepository;
import org.example.mychat.repository.MessageRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MessageService {

    private final MemberRepository memberRepository;
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;

    public void sendMessage(MessageDto dto){
        Member sender = memberRepository.findById(dto.getFrom()).orElseThrow();
        Chat chat = chatRepository.findById(dto.getChatId()).orElseThrow();
        chatRepository.save(chat);
        dto.setContent(sender.getNickname() + " : " + dto.getContent());
        Message message = new Message();
        message.setContent(dto.getContent());
        message.setSender(sender);
        message.setChat(chat);
        messageRepository.save(message);
    }

    public void typing(MessageDto dto){
        Member sender = memberRepository.findById(dto.getFrom()).orElseThrow();
        dto.setContent(sender.getNickname() + " typing...");
    }
}
