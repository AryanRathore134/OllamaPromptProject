package org.example;

import java.nio.file.Files;
import java.nio.file.Paths;

public class PromptTemplateLoader {

    public static String loadTemplate(String promptType, String input) {

        try {

            String filePath = "prompts/" + promptType + ".txt";

            String template = new String(
                    Files.readAllBytes(Paths.get(filePath))
            );

            return template.replace("{input}", input);

        } catch (Exception e) {
            e.printStackTrace();
            return "Error loading prompt template";
        }

    }

}
