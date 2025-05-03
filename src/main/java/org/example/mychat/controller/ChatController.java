package org.example.mychat.controller;


import lombok.RequiredArgsConstructor;
import org.example.mychat.model.dto.MessageDto;
import org.example.mychat.service.MessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final MessageService service;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/send")
    public void sendMessage(MessageDto dto) {
        service.sendMessage(dto);
        messagingTemplate.convertAndSend("/topic/chat", dto);
    }

    @MessageMapping("/typing")
    public void typing(MessageDto dto) {
        service.typing(dto);
        messagingTemplate.convertAndSend("/topic/typing", dto);
    }

    @PostMapping("/login")
    public void login(){
    }

}
