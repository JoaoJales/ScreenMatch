package br.com.alura.screenmatch.service;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;

public class ConsultaGemini {

    public static String obterTraducao(String texto) {

        ChatLanguageModel gemini = GoogleAiGeminiChatModel.builder()
                .apiKey(System.getenv("GEMINI_APIKEY"))
                .modelName("gemini-1.5-flash")
                .build();

        String response = gemini.generate("Traduza para português o texto: " + texto);
        return response;
    }
}