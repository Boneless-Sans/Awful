package com.boneless.projects.utils;

import com.boneless.code.neighborhood.Painter;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class DataReceiver {

    private static final StringBuilder accumulatedData = new StringBuilder();

    public static void main(String[] args) throws IOException {
        if (args.length > 0 && args[0].equalsIgnoreCase("help")) {
            printHelp();
            return;
        }

        // Start the HTTP server on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress("192.168.1.149",8080), 0);
        server.createContext("/receive", new MyHandler());
        server.setExecutor(null);
        server.start();

        System.out.println("Server is listening on port 8080. Type 'help' for a list of commands.");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("help")) {
                printHelp();
            } else if (input.equalsIgnoreCase("stop")) {
                server.stop(0);
                writeDataToFile();
                System.out.println("Server stopped. Data written to file.");
                System.exit(0);
                break;
            } else if(input.equalsIgnoreCase("clear") || input.equalsIgnoreCase("cls")){
                System.out.print("\033[H\033[2J");
                System.out.flush();
            } else if(input.equalsIgnoreCase("ip")){
                System.out.println("IP: " + server.getAddress());
            }
            else {
                System.out.println("Unknown command. Type 'help' for a list of commands.");
            }
        }
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Read the data from the request body
            Scanner scanner = new Scanner(exchange.getRequestBody()).useDelimiter("\\A");
            String requestData = scanner.hasNext() ? scanner.next() : "";

            // Do something with the data (e.g., write it to the console and accumulate)
            System.out.println("Received data: " + requestData);
            accumulatedData.append("Received data: ").append(requestData).append("\n");

            // Send a response to the client
            String response = "Data received successfully.";
            exchange.sendResponseHeaders(200, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }

    private static void writeDataToFile() {
        // Replace this with your logic to specify the file path
        Path filePath = Path.of("server.log");

        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(filePath))) {
            // Write accumulated data to the file
            writer.println(accumulatedData.toString());
            System.out.println("Data written to file: " + filePath.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printHelp() {
        System.out.println("List of Commands:");
        System.out.println("  help - Display this help message");
        System.out.println("  stop - Stop the server and write data to file");
        System.out.println("  clear - Clears the terminal");
        System.out.println("  ip - Displays server IP Address");
    }
}
