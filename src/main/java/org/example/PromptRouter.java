package org.example;

public class PromptRouter {

    public static String buildPrompt(String promptType, String input) {

        switch(promptType) {

            case "explain_code":
                return explainCodePrompt(input);

            case "generate_tests":
                return generateTestsPrompt(input);

            case "code_review":
                return codeReviewPrompt(input);

            case "debug_code":
                return debugCodePrompt(input);

            case "summarize":
                return summarizePrompt(input);

            default:
                return "Unknown prompt type";
        }
    }

    private static String explainCodePrompt(String code) {

        return "You are a senior software engineer.\n\n" +
                "Explain the following code clearly and simply.\n\n" +
                "Code:\n" + code;
    }

    private static String generateTestsPrompt(String code) {

        return "You are a professional software tester.\n\n" +
                "Generate unit test cases for the following code.\n" +
                "Include normal cases, edge cases and error cases.\n\n" +
                "Code:\n" + code;
    }

    private static String codeReviewPrompt(String code) {

        return "You are a senior code reviewer.\n\n" +
                "Review the following code and identify:\n" +
                "- bugs\n" +
                "- performance issues\n" +
                "- improvements\n\n" +
                "Code:\n" + code;
    }

    private static String debugCodePrompt(String code) {

        return "You are a debugging expert.\n\n" +
                "Find potential bugs in the following code and explain them.\n\n" +
                "Code:\n" + code;
    }

    private static String summarizePrompt(String text) {

        return "Summarize the following text in a concise way.\n\n" +
                "Text:\n" + text;
    }
}