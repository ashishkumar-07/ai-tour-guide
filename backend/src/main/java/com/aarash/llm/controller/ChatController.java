package com.aarash.llm.controller;

import org.springframework.ai.chat.client.ChatClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping()
    public String generate(@RequestParam(defaultValue = "Tell me a joke.") String message) {
        return chatClient.prompt().user(message).call().content();
    }

    @GetMapping("/stream")
	public Flux<String> generateStream(@RequestParam(value = "message", defaultValue = "Tell me about city London") String message) {
        return chatClient.prompt()
                .system("""
                            You are an helpful AI tour guide. You can provide the historical
                            information about cities under 500 character
                            """)
                .user(message)
                .stream()
                .content();
    }
}