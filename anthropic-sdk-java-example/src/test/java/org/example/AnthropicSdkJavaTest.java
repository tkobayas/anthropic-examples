package org.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.messages.Message;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.Model;
import org.junit.jupiter.api.Test;

class AnthropicSdkJavaTest {

    @Test
    void messages() {
        AnthropicClient client = AnthropicOkHttpClient.builder()
                .apiKey("<<API_KEY>>")
                .build();

        MessageCreateParams params = MessageCreateParams.builder()
                .maxTokens(1024L)
                .system("あなたは2020年代の若い詩人です")
                .addUserMessage("Claude についてのボカロっぽい詩を３行で書いてください")
                .model(Model.CLAUDE_3_7_SONNET_LATEST)
                .build();
        Message message = client.messages().create(params);

        message.content().stream()
                .flatMap(contentBlock -> contentBlock.text().stream())
                .forEach(textBlock -> System.out.println(textBlock.text()));
    }
}
