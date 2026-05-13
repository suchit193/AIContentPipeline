package com.contentflow.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class GeminiService {

    // YOUR API KEY
    private static final String API_KEY =
            "AIzaSyAx9o2n75dNUGJugKNtJ9L9BmCG-ngPDw0";

    // GENERATE CONTENT
    public String generateContent(

            String platform,
            String brandName,
            String brandDetails,
            String audience,
            String tone,
            String goal) {

        try {

            // BUILD PROMPT
            String prompt =
                    "Generate a high quality " +
                    platform +
                    " marketing post for brand " +
                    brandName +
                    ". Brand Details: " +
                    brandDetails +
                    ". Target Audience: " +
                    audience +
                    ". Tone: " +
                    tone +
                    ". Goal: " +
                    goal +
                    ". Include CTA and hashtags.";

            // UPDATED GEMINI ENDPOINT
            String endpoint =

            		"https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key="

            		+ API_KEY;
            URL url = new URL(endpoint);

            HttpURLConnection conn =
                    (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");

            conn.setRequestProperty(
                    "Content-Type",
                    "application/json");

            conn.setDoOutput(true);

            // JSON BODY
            JSONObject textPart = new JSONObject();
            textPart.put("text", prompt);

            JSONArray partsArray = new JSONArray();
            partsArray.put(textPart);

            JSONObject content = new JSONObject();
            content.put("parts", partsArray);

            JSONArray contentsArray = new JSONArray();
            contentsArray.put(content);

            JSONObject requestBody = new JSONObject();
            requestBody.put("contents", contentsArray);

            // SEND REQUEST
            OutputStream os = conn.getOutputStream();

            os.write(
                    requestBody.toString().getBytes());

            os.flush();
            os.close();

            // RESPONSE CODE
            int responseCode = conn.getResponseCode();

            BufferedReader br;

            // SUCCESS
            if(responseCode >= 200 &&
               responseCode < 300) {

                br = new BufferedReader(
                        new InputStreamReader(
                                conn.getInputStream()
                        )
                );

            } else {

                // ERROR
                br = new BufferedReader(
                        new InputStreamReader(
                                conn.getErrorStream()
                        )
                );
            }

            // READ RESPONSE
            StringBuilder response =
                    new StringBuilder();

            String line;

            while((line = br.readLine()) != null) {
                response.append(line);
            }

            br.close();

            // PRINT RAW RESPONSE
            System.out.println(response.toString());

            // API ERROR
            if(responseCode != 200) {

                return "API Error : " +
                        response.toString();
            }

            // PARSE RESPONSE
            JSONObject jsonResponse =
                    new JSONObject(response.toString());

            JSONArray candidates =
                    jsonResponse.getJSONArray("candidates");

            JSONObject firstCandidate =
                    candidates.getJSONObject(0);

            JSONObject responseContent =
                    firstCandidate.getJSONObject("content");

            JSONArray responseParts =
                    responseContent.getJSONArray("parts");

            JSONObject firstPart =
                    responseParts.getJSONObject(0);

            // FINAL RESPONSE
            return firstPart.getString("text");

        } catch (Exception e) {

            e.printStackTrace();

            return "Exception : " +
                    e.getMessage();
        }
    }
}