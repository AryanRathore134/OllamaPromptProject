package org.example;

public class PromptRouter {

    public static String buildPrompt(String promptType, String input) {

        return PromptTemplateLoader.loadTemplate(promptType, input);
    }
}