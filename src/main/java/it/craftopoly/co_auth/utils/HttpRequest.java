package it.craftopoly.co_auth.utils;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

class HttpRequest
{
    private static final String BASE_URL = "http://localhost:5000/api/v1";

    public static Response get(String endpoint)
    {
        try {
            URL url = new URL(HttpRequest.BASE_URL + endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if(responseCode != 200)
                return new Response(null, responseCode, new Date().toString(), false);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            connection.disconnect();
            return new Gson().fromJson(response.toString(), (Type) Response.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static Response post(String endpoint, Object request) {
        try {
            URL url = new URL(HttpRequest.BASE_URL + endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true); // Enable output for writing the request body

            // Set content type and any other headers as needed
            connection.setRequestProperty("Content-Type", "application/json"); // Example content type

            // Convert the request object to JSON format
            String requestBody = Utils.convertObjectToJson(request);

            // Write the request body to the connection
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = requestBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();

            if(responseCode != 200)
                return new Response(null, responseCode, new Date().toString(), false);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            connection.disconnect();

            // Process the response as needed
            return new Gson().fromJson(response.toString(), (Type) Response.class);
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception for debugging purposes
            return null;
        }
    }

    public static Response put(String endpoint, Object request) {
        try {
            URL url = new URL(HttpRequest.BASE_URL + endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true); // Enable output for writing the request body

            // Set content type and any other headers as needed
            connection.setRequestProperty("Content-Type", "application/json"); // Example content type

            // Convert the request object to JSON format
            String requestBody = Utils.convertObjectToJson(request);

            // Write the request body to the connection
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = requestBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();

            if(responseCode != 200)
                return new Response(null, responseCode, new Date().toString(), false);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            connection.disconnect();

            // Process the response as needed
            return new Gson().fromJson(response.toString(), (Type) Response.class);
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception for debugging purposes
            return null;
        }
    }
}
