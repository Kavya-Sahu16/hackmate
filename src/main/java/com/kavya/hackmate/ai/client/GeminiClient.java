package com.kavya.hackmate.ai.client;

import com.kavya.hackmate.ai.dto.gemini.GeminiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
public class GeminiClient {

    private final WebClient webClient;

    @Value("${gemini.api.key}")
    private String apiKey;

    public GeminiClient() {
        this.webClient = WebClient.builder().build();
    }

    public String extractSkills(String bio) {

        String prompt = """
                Extract technical skills from this bio.
                Return ONLY a comma-separated list.

                Bio:
                %s
                """.formatted(bio);

        Map<String, Object> requestBody = Map.of(
                "contents",
                new Object[] {
                        Map.of(
                                "parts",
                                new Object[] {
                                        Map.of("text", prompt)
                                })
                });

        try {

            GeminiResponse response = webClient.post()
                    .uri("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key="
                            + apiKey)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(GeminiResponse.class)
                    .block();

            if (response == null ||
                    response.getCandidates() == null ||
                    response.getCandidates().isEmpty()) {
                return "";
            }

            return response.getCandidates()
                    .get(0)
                    .getContent()
                    .getParts()
                    .get(0)
                    .getText();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}