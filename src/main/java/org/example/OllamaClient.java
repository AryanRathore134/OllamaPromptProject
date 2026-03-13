package org.example;

import okhttp3.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.concurrent.TimeUnit;

public class OllamaClient {

    private static final String OLLAMA_URL = "http://localhost:11434/api/generate";

    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build();

    private static final MediaType JSON
            = MediaType.parse("application/json");

    public static String runModel(String model, String prompt) {

        try {

            JSONObject requestJson = new JSONObject();
            requestJson.put("model", model);
            requestJson.put("prompt", prompt);
            requestJson.put("stream", false);

            RequestBody body = RequestBody.create(
                    JSON,
                    requestJson.toJSONString()
            );

            Request request = new Request.Builder()
                    .url(OLLAMA_URL)
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();

            String responseBody = response.body().string();

            JSONParser parser = new JSONParser();
            JSONObject responseJson = (JSONObject) parser.parse(responseBody);

            return (String) responseJson.get("response");

        } catch (Exception e) {
            e.printStackTrace();
            return "Error calling Ollama model";
        }

    }

}