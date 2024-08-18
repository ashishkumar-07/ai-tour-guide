package com.aarash.llm.controller;

import com.aarash.llm.model.CityInfo;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/citi-info")
public class CityController {
    private final ChatClient llmClient;

    public CityController(ChatClient.Builder chatClientBuilder) {
        String defaultSystemPrompt = """
                You are an helpful AI tour guide. You can provide information about cities to the users who want to travel to those cities. Your response should include following information:
                1. Small description about the city
                2. History of the city
                3. Attractions in the city
                4. Architecture of the city
                5. Cuisines of the city
                6. Location of the city
                7. Weather of the city
                8. Getting around in the city
                9. More information about the city which includes web links for more information
                """;
        this.llmClient = chatClientBuilder.defaultSystem(defaultSystemPrompt).build();
    }

    @GetMapping("/{city}")
    public CityInfo cityInfo(@PathVariable String city) {

        String userMessagePrompt = """
            Tell me about {city}. If You do not know the city then you must not provide any information.
        """;
        CityInfo cityInfo = llmClient.prompt()
                .user(u->u.text(userMessagePrompt).param("city", city))
                .call()
                .entity(CityInfo.class);
        return cityInfo;
    }
}
