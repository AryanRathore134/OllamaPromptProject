package org.example;

import static spark.Spark.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MainServer {

    public static void main(String[] args) {

        port(14015);

        // health check
        get("/", (req,res) -> "PromptLab Server Running");

        post("/run-prompt", (req,res) -> {

            res.type("application/json");

            JSONParser parser = new JSONParser();

            try {

                JSONObject requestBody = (JSONObject) parser.parse(req.body());

                String model = (String) requestBody.get("model");
                String promptType = (String) requestBody.get("prompt_type");
                String input = (String) requestBody.get("input");

                System.out.println("Model: " + model);
                System.out.println("Prompt Type: " + promptType);
                System.out.println("Input: " + input);

                String finalPrompt = PromptRouter.buildPrompt(promptType, input);

                System.out.println("\nGenerated Prompt:\n");
                System.out.println(finalPrompt);

                JSONObject response = new JSONObject();

                response.put("status", "prompt_generated");
                response.put("model", model);
                response.put("prompt", finalPrompt);

                return response.toJSONString();

            } catch(Exception e) {

                JSONObject error = new JSONObject();
                error.put("status","error");
                error.put("message","Invalid JSON request");

                return error.toJSONString();
            }

        });

    }
}