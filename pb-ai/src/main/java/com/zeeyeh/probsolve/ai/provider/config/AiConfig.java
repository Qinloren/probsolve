package com.zeeyeh.probsolve.ai.provider.config;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    @Bean("qwen")
    public ChatModel chatLanguageModel() {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("APP_AI_DASHSCOPE_API_KEY"))
                .modelName(System.getenv("APP_AI_DASHSCOPE_MODEL_NAME"))
                .baseUrl(System.getenv("APP_AI_DASHSCOPE_BASE_URL"))
                .build();
    }
}
