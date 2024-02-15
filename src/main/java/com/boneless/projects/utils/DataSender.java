package com.boneless.projects.utils;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class DataSender {
    private static String serverUrl = "192.168.1.149:8080/receive";

    public DataSender(){
        //do things
    }
    public DataSender(String URL){
        serverUrl = URL;
    }
    public void send(String data) throws Exception {
        URL serverURL = new URL(serverUrl);
        HttpURLConnection connection = (HttpURLConnection) serverURL.openConnection();

        // Set up the connection for a POST request
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        // Write the data to the request body
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = data.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        // Get the response from the server (optional)
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        // Close the connection
        connection.disconnect();
    }
}
