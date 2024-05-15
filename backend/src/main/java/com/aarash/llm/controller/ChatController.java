package com.aarash.llm.controller;

import java.util.Map;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.ai.chat.messages.UserMessage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final OllamaChatClient chatClient;

    public ChatController(OllamaChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/generate")
    public Map<String,String> generate(@RequestParam(value = "message", defaultValue = "Tell me about city London") String message) {
        return Map.of("generation", chatClient.call(message));
    }

    @GetMapping("/generate-stream")
	public Flux<String> generateStream(@RequestParam(value = "message", defaultValue = "Tell me about city London") String message) {

        Prompt prompt = new Prompt(new UserMessage(message));
        return chatClient.stream(prompt).map(m->m.getResult().getOutput().getContent());
    }
}